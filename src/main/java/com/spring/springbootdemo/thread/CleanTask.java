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
import java.util.concurrent.LinkedBlockingQueue;

public class CleanTask implements Runnable {

    private static final String KEY_WORD = "评价方法";
    private LinkedBlockingQueue<DataContentWithBLOBs> queues;
    private static final Logger logger = LoggerFactory.getLogger(CleanTask.class);

    public CleanTask(LinkedBlockingQueue<DataContentWithBLOBs> queue) {
        this.queues = queue;
    }

    @Override
    public void run() {
        DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
        List<DataContentWithBLOBs> list = new LinkedList<>();

        while (queues.iterator().hasNext()) {
            DataContentWithBLOBs data = queues.poll();

            try {
                String content = data.getContent();
                if (StringUtils.isBlank(content)) {
                    continue;
                }
                Document parse = Jsoup.parse(content);

                DataContentWithBLOBs dcb = cleanMethod(data,parse);
                if(dcb == null){
                    continue;
                }
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

    }

/*
 * @description
 * @author tengchao.li
 * @date 2020/2/23
 * @param dcb
 * @param data
 * @param content
 * @return void
 */
    private DataContentWithBLOBs cleanMethod(DataContentWithBLOBs data,Document parse) throws Exception {

        String proName = parse.select(".h4_o").get(0).text();
        // String subTime = parse.select(".p_o").get(0).children().get(0).text();
        Elements tds = parse.select("td");
        Elements ths = parse.select("th");

        Elements table = parse.select("table");
        Elements p = parse.select("p");
        Elements tr = parse.select("tr");
        Elements td = parse.select("td");


       /* if(!KEY_WORD.equals(ths.get(5).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(5).text());
            return null;
        }*/

        String fileNo = tds.get(0).text();
        String zg = tds.get(1).text(); //zi ge
        String applyDeadLine = tds.get(2).text(); // apply dead line
        String fileOpenTime = tds.get(3).text(); //kaibiao shijian
        String fileOpenWay = tds.get(4).text();//
        // String subTime = tds.get(18).text();// tijiaoshijian
        String method = tds.get(5).text(); //评审方法
        String contents = tds.get(6).text(); //修改 澄清内容

        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
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

        return dcb;
    }





}
