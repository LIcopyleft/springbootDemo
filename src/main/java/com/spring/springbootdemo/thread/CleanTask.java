package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jaxen.xom.XOMXPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class CleanTask implements Runnable {

    private static final String KEY_WORD = "区域坐标";
    private static final int INSERT_MAX = 1000;
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
                logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            LinkedBlockingQueue<DataContentWithBLOBs> queues = new LinkedBlockingQueue();
            for (DataContentWithBLOBs data : dataContent) {
                if (stageShow.equals(data.getStageshow()) && "政府采购".equals(data.getClassifyshow())) {
                    queues.add(data);
                }
            }
            if (queues.size() < 1) {
                logger.info(Thread.currentThread().getName() + "end====no " + stageShow + "=====beginIndex = " + beginIndex);
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
                    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}");
                    continue;
                }

                if (list.size() == INSERT_MAX || queues.size() == 0) {
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
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
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
     * @description  交易公告 / 中标公告类型清洗
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
        Elements ps = parse.select(".detail_content");
        Elements pp = ps.get(0).getElementsByTag("p");
        Elements table1 = ps.get(0).getElementsByTag("table");
        Elements children = ps.get(0).children();
        System.out.println(data.getUrl());

        if (table1 == null || table1.size() > 2) {
            return null;
        }
        parseRowTable(parse);
        String html = ps.html();
        Element body = parse.body();
        boolean table = body.hasClass("table");

        Node style = parse.removeAttr("style");
        String text = ps.text().trim();
        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
        BeanUtils.copyProperties(data, dcb);
        dcb.setProname(proName);
        dcb.setOther(text);
        dcb.setContent(null);
        return dcb;
    }


    /**
     * 单 table标签 解析纵向表结构
     *
     * @param parse
     */

    public static String parseRowTable(Document parse) {
        Elements ps = parse.select(".detail_content").get(0).getElementsByTag("p");
        Elements table1 = ps.get(0).getElementsByTag("table");
        Map<String, String> map = new HashMap();
        if (table1 != null) {
            Elements table = parse.getElementsByTag("table");
            StringBuffer sb = new StringBuffer();
            for (Element tab : table) {
                Elements trs = tab.getElementsByTag("tr");
                Elements first = trs.first().children();
                for (int i = 1; i < trs.size(); i++) {
                    Elements element = trs.get(i).getElementsByTag("td");
                    Map<Integer, String> m = null;
                    for (int j = 0; j < element.size(); j++) {
                        String name = first.get(j).text() + ":" + element.get(j).text();
                        if (element.get(j).hasAttr("rowspan")) {
                            String num = (element.get(j)).attr("rowspan");
                            m = new HashMap();
                            m.put(j, name + "#" + num);
                            //下次循环到该index 直接拼接本次str
                        }
                        if (m != null && m.containsKey(j)) {
                            String[] split = map.get(j).split("#");
                            if (Integer.valueOf(split[1]) < 1) {
                                m.remove(j);
                            } else {
                                name = first.get(j).text() + ":" + split[0];
                                m.put(j, name + "#" + (Integer.valueOf(split[1]) - 1));
                            }
                        }
                        sb.append(name);
                        sb.append("|");
                    }
                }
                System.err.println(sb.toString());
            }

        }
        return null;
    }
}
