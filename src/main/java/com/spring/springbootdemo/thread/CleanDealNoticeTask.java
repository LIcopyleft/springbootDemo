package com.spring.springbootdemo.thread;

import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanDealNoticeTask implements Runnable {

    public static final String STAGE_SHOW = "";
    private static final String KEY_WORD = "区域坐标";
    private static final int KEY_NUM = 6;
    private LinkedBlockingQueue<DataContentWithBLOBs> queues;
    private static final Logger logger = LoggerFactory.getLogger(CleanDealNoticeTask.class);

    public CleanDealNoticeTask(LinkedBlockingQueue<DataContentWithBLOBs> queue) {
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
                e.printStackTrace();
                continue;
            }


        }
        if (list.size() > 0 && queues.size() == 0) {
            System.err.println(Thread.currentThread().getName());
            int row = mapper.insertList(list);
            logger.warn("url_id over" + list.get(list.size() - 1).getUrlId() + "=====insert\t"+row+"行");
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
        String subTime = parse.select(".p_o").get(0).text();
String time = null;
        Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}|[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}");
        Matcher matcher = pattern.matcher(subTime);
        while(matcher.find()){
            time = matcher.group();
        }



        Elements tds = parse.select("td");
        Elements ths = parse.select("th");
        Element detail = parse.select(".detail_content").get(0);
        Elements childs = detail.children();


     //   Elements spans = detail.getElementsByTag("span");
        StringBuffer sb = new StringBuffer();
        for(Element child : childs){
            String text = child.text();
         //   String html = child.html();
            String replace = text.replace("。", ";").replace("交易标的名称","|交易标的名称").substring(1);


            sb.append(replace);
          //  sb.append();

        }
     //   System.out.println(sb.toString());

     /*   if(!KEY_WORD.contains(ths.get(KEY_NUM).text())){
            logger.error("========type diff==" + data.getUrlId() + "=========="+ths.get(4).text());
            return null;
        } */



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
        dcb.setOther(sb.toString());
       dcb.setNoticetime(time);
      //  dcb.setWinbidtime(d6);
        dcb.setContent(null);

        return dcb;
    }





}
