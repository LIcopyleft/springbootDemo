package com.spring.springbootdemo.thread;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.ConfigParam;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
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
import java.util.concurrent.LinkedBlockingQueue;

public class GOVDataCleanTask implements Runnable {

    private int beginIndex;
    private ConfigParam config;

    private static final Logger logger = LoggerFactory.getLogger(GOVDataCleanTask.class);


    public GOVDataCleanTask(int beginIndex, ConfigParam config) {
        this.beginIndex = beginIndex;
        this.config = config;
    }

    @Override
    public void run() {
        try {
            DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
            List<GovData> list = new LinkedList<>();
            List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, config.getQuerySize(), config.getCleanTableName());
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
                if (config.getStage().equals(data.getStageShow()) && config.getTableSize() == Integer.valueOf(data.getLocation() == null ? "0" : data.getLocation())) {
                    queues.add(data);
                }
            }
            if (queues.size() < 1) {
                // logger.info(Thread.currentThread().getName() + "end====no " + stageShow + "=====beginIndex = " + beginIndex);
                return;
            }
            int row = 0;
            while (queues.iterator().hasNext()) {
                GovData data = queues.poll();
                try {
                    String content = data.getContent();
                    if (StringUtils.isBlank(content)) {
                        continue;
                    }
                    //    Document parse = Jsoup.parse(content);
                    // DataContentWithBLOBs dcb = cleanMethod(data,parse);
                /*    DataContentWithBLOBs dcb = cleanMethod_2(data);
                    if (dcb == null) {
                        continue;
                    } */
                    //  data = cleanMethod_3(data);
                    //    onlyOneRowAndOneCol(data);
                    //    data = clean_cggg(data);
                    data = clean_zbgg(data);
                    if (data == null) {
                        continue;
                    }
                    // data.setContent(null);
                    list.add(data);
                } catch (Exception e) {
                    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}", e);
                    continue;
                }

                if (list.size() == config.getInsertMax() || queues.size() == 0) {
                    if (list.size() < 1) {
                        continue;
                    }
                    row += mapper.insertList_BJ(list, config.getInsertTableName());
                    list.clear();
                    continue;
                }

            }
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            config.getLatch().countDown();
        }
    }

    //


    /**
     * 中标公告,处理和cggg 完全相同
     *
     * @param data
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static GovData clean_zbgg(GovData data) throws InvocationTargetException, IllegalAccessException {
        if (data.getUrlId() == 19865 || data.getUrlId() == 197) {
            data.getUrlId();
        }

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
        List<String> cellInfoList = new LinkedList<>();

        if (p.size() < 4) {
            System.err.println("p标签数量少");
        }

/*
        if(tableList.size() != 0){
            return null;
        }
        if(tableList.size() == 0){
            tableList.size();
        }
        if (tableList.size() > 1) {
         //   TableDeal.tableSizeOverOne(data, tableList, cellInfoList);
            return null;
        }

 */
        if (Integer.valueOf(data.getLocation()) > 0) {
            logger.debug("表格内容解析开始*******************************");//表格解析开始
            cellInfoList = TableDeal.tableSizeOverOne(tableList);
            logger.debug("打印解析内容\n" + Arrays.toString(cellInfoList.toArray()));
            logger.debug("表格内容解析结束");

        }

        logger.debug(data.getUrl());
        logger.debug("tableSize: " + tableList.size());
        Map map1 = HtmlUtils.plistToMap(cellInfoList);
        map = HtmlUtils.prasePToMap(p);
        map.putAll(map1);
        data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());

        if (data.getWinBidTotalAmount() == null || data.getWinBidBisAddr() == null || data.getWinBidBisName() == null) {
            //		FileUtils.writeAppendFile("中标相关信息.txt",map1.keySet().toString());
        }
        data.setOther(map.size() == 0 ? null : JSON.toJSONString(map));
        data.setClasses(memu.length() > 200 ? null : memu);
        data.setPubTime(pubTime);
        //    data.setContent(null);
        //    data.setClassifyShow(data.getStageShow());//政府采购  更正公告
        // System.out.println(JSON.toJSONString(map));
        // TODO 时间 金额 等字段在这里处理
        String proName = data.getProName();
        if (StringUtils.isNotBlank(proName) && proName.length() > 200) {
            data.setProName(proName.substring(0, 200));
        }
        return data;
    }

}
