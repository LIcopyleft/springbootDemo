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

public class ThreadTask implements Runnable {
    private LinkedBlockingQueue<DataContentWithBLOBs> queues;
    private static final Logger logger = LoggerFactory.getLogger(ThreadTask.class);

    public ThreadTask(LinkedBlockingQueue<DataContentWithBLOBs> queue) {
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
                Document parse = Jsoup.parse(content);
                String proName = parse.select(".h4_o").get(0).text();
            //    Elements select = parse.select(".p_o");
            //    Element first = select.first();
            //    String proNo = parse.select(".p_o").get(0).getElementsByTag("span").text();


                Element table = parse.getElementsByTag("table").get(0);
                // Elements children = table.children();

                Elements td = table.getElementsByTag("td");
                //    Elements tr = table.getElementsByTag("tr");
                String data1 = td.get(0).text();//采购人名称
                String data2 = td.get(1).text();//供应商名称
                String data3 = td.get(2).text();//合同金额
                String data4 = td.get(3).text();//he tong nianxian
                String data5 = td.get(4).text();//qian shu shi jian
                BeanUtils.copyProperties(data, dcb);
                dcb.setBuyingunit(data1);
                dcb.setBudgetamount(data3);
                dcb.setWinbidbisamount(data3);
                dcb.setWinbidbisname(data2);
                dcb.setWinbidtime(data5);
                dcb.setProname(proName);
                dcb.setContent(null);
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
        if (list.size() == 100 || queues.size() == 0) {
            System.err.println(Thread.currentThread().getName());
            mapper.insertList(list);
            logger.warn("url_id"+list.get(list.size()-1)+"insert");
            list.clear();
        }
        System.out.println("===");
        //   latch.countDown();

    }
}
