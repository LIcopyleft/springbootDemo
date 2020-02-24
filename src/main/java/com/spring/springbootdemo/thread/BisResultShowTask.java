package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class BisResultShowTask implements Runnable {
    private LinkedBlockingQueue<DataContentWithBLOBs> queues;
    private static final Logger logger = LoggerFactory.getLogger(BisResultShowTask.class);

    public BisResultShowTask(LinkedBlockingQueue<DataContentWithBLOBs> queue, CountDownLatch latch) {
        this.queues = queue;
    }

    @Override
    public void run() {
        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
        List<DataContentWithBLOBs> list = new LinkedList<>();

        while (queues.iterator().hasNext()) {
            DataContentWithBLOBs dcb = new DataContentWithBLOBs();
            DataContentWithBLOBs data = queues.poll();
            String content = data.getContent();
            if (StringUtils.isBlank(content)) {
                continue;
            }
            try {
                boolean flag = step2(dcb, data, content);
                if(!flag){
                    continue;
                }
//dcb.set

             /*   String proNum = null;
                if (StringUtils.isNotBlank(proNo) && proNo.contains(":")) {
                    String[] split = proNo.split(":");
                    if (split.length > 1) {
                        proNum = split[1];
                    }

                }
                dcb.setProno(proNum);*/
                list.add(dcb);
            } catch (Exception e) {
                logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}");
                continue;
            }


        }
        if (list.size() > 0 && queues.size() == 0) {
            System.err.println(Thread.currentThread().getName());
            int row = mapper.insertList(list);
            logger.warn("url_id over" + list.get(list.size() - 1).getUrlId() + "=====insert"+row+"行");
            list.clear();
        }
        //   latch.countDown();

    }
/*
 * @description   stageshow 两个结构中一种解析
 * @author tengchao.li
 * @date 2020/2/23
 * @param dcb
 * @param data
 * @param content
 * @return void
 */
    private void step1(DataContentWithBLOBs dcb, DataContentWithBLOBs data, String content) {
        Document parse = Jsoup.parse(content);
        String proName = parse.select(".h4_o").get(0).text();
        //    String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");

        String fileNo = tds.get(0).text();
        String fileDeadTime = tds.get(2).text();
        String bzAmount = tds.get(6).text(); //bao zhengjin
        String time = tds.get(9).text(); //kaibiao shijian
        String addr = tds.get(10).text();//kai biao didian
        //    String subTime = tds.get(18).text();// tijiaoshijian
        String bugget = tds.get(7).text(); //yusuan
        BeanUtils.copyProperties(data, dcb);
        dcb.setOpentendertime(time);
        dcb.setOpentenderaddr(addr);
        dcb.setSubmitfiledeadtime(fileDeadTime);
        dcb.setProno(fileNo);
        dcb.setProname(proName);
        dcb.setBudgetamount(bugget);//预算/控制价
        dcb.setContent(null);
    }
/*
 * @description   结构2
 * @author tengchao.li
 * @date 2020/2/23
 * @param dcb
 * @param data
 * @param content
 * @return void
 */
    private boolean step2(DataContentWithBLOBs dcb, DataContentWithBLOBs data, String content) throws Exception {
        Document parse = Jsoup.parse(content);
        String proName = parse.select(".h4_o").get(0).text();
        //    String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");
        Elements ths = parse.select("th");

        if(!"评审办法".equals(ths.get(5).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(5).text());
            return false;
        }

        String fileNo = tds.get(0).text();
        String zg = tds.get(1).text(); //zi ge
        String applyDeadLine = tds.get(2).text(); // apply dead line
        String fileOpenTime = tds.get(3).text(); //kaibiao shijian
        String fileOpenWay = tds.get(4).text();//
        //    String subTime = tds.get(18).text();// tijiaoshijian
        String method = tds.get(5).text(); //评审方法
        String contents = tds.get(6).text(); //修改 澄清内容
        BeanUtils.copyProperties(data, dcb);
        //dcb.setOpentendertime(time);
        dcb.setSubmitfiledeadtime(applyDeadLine);
        dcb.setOther(contents);
        dcb.setProno(fileNo);
      //  dcb.set
        dcb.setProname(proName);
        dcb.setFileOpenWay(fileOpenWay);
        dcb.setJudgeMethod(method);
        dcb.setOpentendertime(fileOpenTime);
        dcb.setContent(null);

        return true;
    }



}
