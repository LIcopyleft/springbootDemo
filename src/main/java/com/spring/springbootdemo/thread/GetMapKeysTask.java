package com.spring.springbootdemo.thread;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.WinBisInfo;
import com.spring.springbootdemo.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMapKeysTask implements Runnable {


    // 1


    private static final int INSERT_MAX = 1000;
    private static final int KEY_NUM = 6;

    private int beginIndex;
    private int querySize;
    private String stageShow;
    private CountDownLatch latch;
    private Set<String> keys;


    private static final Logger logger = LoggerFactory.getLogger(GetMapKeysTask.class);


    public GetMapKeysTask(int beginIndex, int querySize, String stageShow, CountDownLatch latch, Set keysSet) {
        this.beginIndex = beginIndex;
        this.querySize = querySize;
        this.stageShow = stageShow;
        this.latch = latch;
        this.keys = keysSet;
    }

    @Override
    public void run() {
        //查询当前表格中某一个字段为null , 修改后重新插入
        String tableName = "spider_2_ggzy_content_clean_temp";
        //null 的字段 , 修补此字段
        String filed = "proxy_org_addr";
        //   String filedVal = null;
        // TODO  注意修改下方 get方法

        try {
            DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
            List<GovData> list = new LinkedList<>();
            //   List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, querySize, "spider_2_ggzy_content_clean_temp");
            List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, querySize, tableName);

            if (dataContent == null || dataContent.size() < 1) {
                logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            List<GovData> datas = new LinkedList<>();
            for (DataContentWithBLOBs data : dataContent) {
                GovData govData = DataConvert.toGovData(data);
                datas.add(govData);
            }


            LinkedBlockingQueue<GovData> queues = new LinkedBlockingQueue();
            for (GovData data : datas) {
                // if (stageShow.equals(data.getStageShow()) && data.getLocation().equals("0")) {
                queues.add(data);
                //}
            }
            if (queues.size() < 1) {
                // logger.info(Thread.currentThread().getName() + "end====no " + stageShow + "=====beginIndex = " + beginIndex);
                return;
            }
            //  int row = 0;
            while (queues.iterator().hasNext()) {
                GovData data = queues.poll();
                try {
                    String content = data.getContent();
                    if (StringUtils.isBlank(content)) {
                        continue;
                    }

                    //针对一个字段
                    data = writeKeys(data, keys);
                    if (data == null) {
                        continue;
                    }


                } catch (Exception e) {
                    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}", e);
                    continue;
                }

            }
            //   logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            if (keys.size() > 1000) {
                FileUtils.writeAppendFile("金额.txt", keys);
                keys.clear();
            }
            latch.countDown();
        }
    }

    /**
     * 只针对一个字段处理
     *
     * @param data
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static GovData writeKeys(GovData data, Set keys) throws InvocationTargetException, IllegalAccessException {
      /*  if (data.getUrlId() == 887) {
            data.getUrlId();
        }

*/
        String content = data.getContent();
        Map map = new HashMap();
        content = HtmlUtils.removeCNStr(content);
        Document parse = Jsoup.parse(content);
        Elements po = parse.select(".p_o");
        Element span = po.select("span").first();
        String pubTime = null;

        if (po != null && po.size() > 0 && span != null && StringUtils.isNotBlank(span.text()) && span.text().length() > 5) {
            pubTime = span.text().substring(5);
        }

        Elements sitemap = parse.getElementsByClass("location");
        //获取导航目录信息
        String memu = "";
        if (sitemap != null && sitemap.size() > 0) {
            memu = StrUtil.cleanBlank(sitemap.get(0).text().replace("您的当前位置:", ""));

        }

        Elements p = parse.getElementsByTag("p");
        List<Element> tableList = HtmlUtils.getHtmlTableList(parse);

        map = HtmlUtils.prasePToMap(p);

        if (map.size() < 3) {
            return null;
        }
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            if (key.contains("预算")||key.contains("金额")||key.contains("万元")
                    ||key.contains("元")
                    ||key.contains("人民币")
                    ||key.contains("资金")
            ) {
                keys.add(key);
                //  FileUtils.writeAppendFile("代理.txt",key);
                //   System.err.println(key +":" +entry.getValue().toString() );
            }
        }
        logger.debug(data.getUrl());
        logger.debug("tableSize: " + tableList.size());
        return data;


    }


    //政府采购
    private static DataContentWithBLOBs cleanMethod_1(DataContentWithBLOBs data) throws InvocationTargetException, IllegalAccessException {

        Document parse = Jsoup.parse(data.getContent());
        Elements sitemap = parse.getElementsByClass("sitemap");

        String memu = "";
        if (sitemap != null && sitemap.size() > 0) {
            memu = sitemap.get(0).text();
        }

        Map map = new HashMap();
        //    Elements tables = parse.getElementsByTag("table");
        Elements tables = parse.getElementsByClass("lc-table");
        //     Element element = table;
        if (tables == null || tables.size() < 2) {
            return null;
        }
        Element table = tables.get(1);
        Elements trs = table.getElementsByTag("tr");
        for (Element tr : trs) {
            Elements ttr = tr.getElementsByTag("th");
            Elements td = tr.getElementsByTag("td");

            for (int i = 0; i < ttr.size(); i++) {
                map.put(ttr.get(i).text().replaceAll(":", ""), td.get(i).text());
            }

        }
        //   Set set = Contant.filedValueSet();

        data = (DataContentWithBLOBs) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
        data.setOther(JSON.toJSONString(map));
        data.setClasses(memu);
        data.setContent(null);

        // System.out.println(JSON.toJSONString(map));
        return data;
    }

    private static DataContentWithBLOBs cleanMethod_3(DataContentWithBLOBs data) {
        // logger.info(data.getUrl());
        //   String regEx_az = "[\\s\\@\\#\\$\\%\\^\\&\\*\\{\\}\\[\\]\\;\\'\\“\\”\\。\\，\\+\\/\\<\\>\\?\\《\\》\\=]+";
        //    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        String content = data.getContent();
        Pattern p_script = Pattern.compile("：", Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(content);
        content = m_script.replaceAll(":");
        Document parse = Jsoup.parse(content);
        Elements sitemap = parse.getElementsByClass("sitemap");

        String memu = "";
        if (sitemap != null && sitemap.size() > 0) {
            memu = sitemap.get(0).text();
        }

        Map map = new HashMap();
        // Elements tables = parse.getElementsByTag("table");
        //  Elements tables = parse.getElementsByTag("p");

        Elements tables = parse.getElementsByTag("table");

        //无表格
        if (tables == null || tables.size() < 1) {
            //无表格
            // 即只有 P型 ， 字段配置解析

            data.setLocation("0");
            return data;
        } else if (tables.size() == 1) {
            data.setLocation("1");
            // 只有一列：tr 下 td 只有一个

            //按P型处理

            //只有合并行没有合并列

        } else {
            data.setLocation("2");
            //多表格
            //1.包含只有一列
            //2.包含合并行不包含合并列
        }


        return data;

    }


}
