package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class CleanTask implements Runnable {

    private static final String KEY_WORD = "区域坐标";
    private static final int INSET_MAX = 1000;
    private static final int KEY_NUM = 6;

    private long beginIndex;
    private long querySize;
    private String stageShow;
    private CountDownLatch latch;


    private static final Logger logger = LoggerFactory.getLogger(CleanTask.class);


    public CleanTask(long beginIndex, long querySize, String stageShow, CountDownLatch latch) {
        this.beginIndex = beginIndex;
        this.querySize = querySize;
        this.stageShow = stageShow;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
            List<DataContentWithBLOBs> list = new LinkedList<>();
            List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, querySize);
            if (dataContent == null || dataContent.size() < 1) {
                logger.warn(Thread.currentThread().getName()+"end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            LinkedBlockingQueue<DataContentWithBLOBs> queues = new LinkedBlockingQueue();
            for (DataContentWithBLOBs data : dataContent) {
                if (stageShow.equals(data.getStageshow())) {
                    queues.add(data);
                }
            }
            if (queues.size() < 1) {
                logger.info(Thread.currentThread().getName()+"end====no " + stageShow + "=====beginIndex = " + beginIndex);
                return;
            }
            int row = 0;
            while (queues.iterator().hasNext()) {
                DataContentWithBLOBs data = queues.poll();
                try {
                    String content = data.getContent();
                    if (StringUtils.isBlank(content)) {
                        continue;
                    }
                    Document parse = Jsoup.parse(content);
                    // DataContentWithBLOBs dcb = cleanMethod(data,parse);
                    DataContentWithBLOBs dcb = cleanMethodBisReport(data, parse);
                    if (dcb == null) {
                        continue;
                    }
                    list.add(dcb);
                } catch (Exception e) {
                    //    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}");
                    continue;
                }

                if (list.size() == INSET_MAX || queues.size() == 0) {
                    //   System.err.println(Thread.currentThread().getName());
                    if (list.size() < 1) {
                        continue;
                    }
                    row += mapper.insertList(list);
                    // logger.info(Thread.currentThread().getName()+"==url_id over" + list.get(list.size() - 1).getUrlId() + "=====insert\t" + row + "行");
                    list.clear();
                    continue;
                }

            }
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() -1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            latch.countDown();
        }
    }

    /*
     * @description    表格类型清洗, 可复用
     * @author tengchao.li
     * @date 2020/2/23
     * @param dcb
     * @param data
     * @param content
     * @return void
     */
    private DataContentWithBLOBs cleanMethod(DataContentWithBLOBs data, Document parse) throws Exception {

        String proName = parse.select(".h4_o").get(0).text();
        // String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");
        Elements ths = parse.select("th");

        if (!KEY_WORD.contains(ths.get(KEY_NUM).text())) {
            logger.error("========type diff==" + data.getUrlId() + "==========" + ths.get(4).text());
            return null;
        }
/*
        Elements select = parse.select(".detail");
        Elements select1 = parse.select(".p_o");
        Elements select2 = parse.select(".h4_o");
        Elements table = parse.select("table");
        Elements p = parse.select("p");
        Elements tr = parse.select("tr");
        Elements td = parse.select("td");

*/
       /* if(!KEY_WORD.equals(ths.get(5).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(5).text());
            return null;
        }*/

        String d0 = tds.get(0).text();
        String d1 = tds.get(1).text(); //zi ge
        String d2 = tds.get(2).text(); // apply dead line
        String d3 = tds.get(3).text(); //kaibiao shijian
        String d4 = tds.get(4).text(); //kaibiao shijian
        String d5 = tds.get(5).text(); //kaibiao shijian
        String d6 = tds.get(6).text(); //kaibiao shijian
  /*      String fileOpenWay = tds.get(4).text();//
        // String subTime = tds.get(18).text();// tijiaoshijian
        String method = tds.get(5).text(); //评审方法
        String contents = tds.get(6).text(); //修改 澄清内容 */

        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
        BeanUtils.copyProperties(data, dcb);
        //dcb.setOpentendertime(time);
        // dcb.setBuyingunit(d0);
        //   dcb.setOpentenderaddr(d1);
        //  dcb.set
        dcb.setProname(d0);
        //    dcb.setOpentendertime(d2);
        dcb.setOther(ths.get(1).text() + ":" + d1 + "|" + ths.get(2).text() + ":" + d2 + "|" + ths.get(3).text() + ":" + d3 + "|" + ths.get(4).text() + ":" + d4);
        dcb.setLocation(d5);
        if (d6.length() < 200) {
            dcb.setCoordinate(d6);
        }
        //  dcb.setWinbidtime(d6);
        dcb.setContent(null);

        return dcb;
    }
    /*
     * @description  中标公告 清洗
     * @author tengchao.li
     * @date 2020/2/25
     * @param data
     * @param parse
     * @return com.spring.springbootdemo.model.DataContentWithBLOBs
     */
    private DataContentWithBLOBs cleanMethodWinBidRes(DataContentWithBLOBs data, Document parse) throws Exception {
        String proName = parse.select(".h4_o").get(0).text();
        String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");
        Elements ths = parse.select("th");
        Elements ps = parse.select(".detail_content").get(0).getElementsByTag("p");
        String text = ps.text();
        if (!KEY_WORD.contains(ths.get(KEY_NUM).text())) {
            logger.error("========type diff==" + data.getUrlId() + "==========" + ths.get(4).text());
            return null;
        }
/*
        Elements select = parse.select(".detail");

        Elements select1 = parse.select(".p_o");
        Elements select2 = parse.select(".h4_o");
        Elements table = parse.select("table");
        Elements p = parse.select("p");
        Elements tr = parse.select("tr");
        Elements td = parse.select("td");
*/
       /* if(!KEY_WORD.equals(ths.get(5).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(5).text());
            return null;
        }*/

        String d0 = tds.get(0).text();
        String d1 = tds.get(1).text(); //zi ge
        String d2 = tds.get(2).text(); // apply dead line
        String d3 = tds.get(3).text(); //kaibiao shijian
        String d4 = tds.get(4).text(); //kaibiao shijian
        String d5 = tds.get(5).text(); //kaibiao shijian
        String d6 = tds.get(6).text(); //kaibiao shijian
  /*      String fileOpenWay = tds.get(4).text();//
        // String subTime = tds.get(18).text();// tijiaoshijian
        String method = tds.get(5).text(); //评审方法
        String contents = tds.get(6).text(); //修改 澄清内容 */

        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
        BeanUtils.copyProperties(data, dcb);
        //dcb.setOpentendertime(time);
        // dcb.setBuyingunit(d0);
        //   dcb.setOpentenderaddr(d1);
        //  dcb.set
        dcb.setProname(d0);
        //    dcb.setOpentendertime(d2);
        dcb.setOther(ths.get(1).text() + ":" + d1 + "|" + ths.get(2).text() + ":" + d2 + "|" + ths.get(3).text() + ":" + d3 + "|" + ths.get(4).text() + ":" + d4);
        dcb.setLocation(d5);
        if (d6.length() < 200) {
            dcb.setCoordinate(d6);
        }
        //  dcb.setWinbidtime(d6);
        dcb.setContent(null);

        return dcb;
    }

    /*
     * @description  交易公告类型清洗
     * @author tengchao.li
     * @date 2020/2/26
     * @param data
     * @param parse
     * @return com.spring.springbootdemo.model.DataContentWithBLOBs
     */
    private DataContentWithBLOBs cleanMethodBisReport(DataContentWithBLOBs data, Document parse) throws Exception {

        String proName = parse.select(".h4_o").get(0).text();
        String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");
        Elements ths = parse.select("th");
        Elements ps = parse.select(".detail_content").get(0).getElementsByTag("p");


        String text = ps.text();

        System.out.println(text.trim());
        System.out.println("=============================");

        if (!KEY_WORD.contains(ths.get(KEY_NUM).text())) {
            //   logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(4).text());
            return null;
        }
/*
        Elements select = parse.select(".detail");

        Elements select1 = parse.select(".p_o");
        Elements select2 = parse.select(".h4_o");



        Elements table = parse.select("table");
        Elements p = parse.select("p");
        Elements tr = parse.select("tr");
        Elements td = parse.select("td");

*/
       /* if(!KEY_WORD.equals(ths.get(5).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(5).text());
            return null;
        }*/
/*
        String d0 = tds.get(0).text();
        String d1 = tds.get(1).text(); //zi ge
        String d2 = tds.get(2).text(); // apply dead line
        String d3 = tds.get(3).text(); //kaibiao shijian
        String d4 = tds.get(4).text(); //kaibiao shijian
        String d5 = tds.get(5).text(); //kaibiao shijian
        String d6 = tds.get(6).text(); //kaibiao shijian
        String fileOpenWay = tds.get(4).text();//
        // String subTime = tds.get(18).text();// tijiaoshijian
        String method = tds.get(5).text(); //评审方法
        String contents = tds.get(6).text(); //修改 澄清内容 */

        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
        BeanUtils.copyProperties(data, dcb);
        //dcb.setOpentendertime(time);
        // dcb.setBuyingunit(d0);
        //   dcb.setOpentenderaddr(d1);
        //  dcb.set
        dcb.setProname(proName);
        //    dcb.setOpentendertime(d2);
     //   dcb.setOther(ths.get(1).text() + ":" + d1 + "|" + ths.get(2).text() + ":" + d2 + "|" + ths.get(3).text() + ":" + d3 + "|" + ths.get(4).text() + ":" + d4);
   //     dcb.setLocation(d5);

        //  dcb.setWinbidtime(d6);
        dcb.setContent(null);

        return dcb;
    }


}
