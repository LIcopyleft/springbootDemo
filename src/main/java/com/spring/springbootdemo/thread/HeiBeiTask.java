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

public class HeiBeiTask implements Runnable {

    private int beginIndex;
    private ConfigParam config;
    static DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
    private static final Logger logger = LoggerFactory.getLogger(HeiBeiTask.class);


    public HeiBeiTask(int beginIndex, ConfigParam config) {
        this.beginIndex = beginIndex;
        this.config = config;
    }

    @Override
    public void run() {
        try {

            List<GovData> list = new Vector<>();
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

                if (config.isOpen()) {
                    if (config.getTableNum() != Integer.valueOf(data.getLocation() == null ? "0" : data.getLocation())) {
                        continue;
                    }
                }
                data.setStageShow(data.getCategory());
            //    if (config.getStage().contains(data.getStageShow())) {
                if (data.getStageShow().contains(config.getStage())) {
                    data.setLocation(data.getLocation() == null ? "2" : data.getLocation());
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
        /*if (data.getUrlId() == 19865 || data.getUrlId() == 197) {
            data.getUrlId();
        }*/
        String content = data.getContent();
        Map map = new HashMap();
        content = HtmlUtils.removeCNStr(content);
        Document parse = Jsoup.parse(content);
        String allText = parse.text();
        Elements po = parse.select(".p_o");
        Element span = po.select("span").first();
        String pubTime = null;

        if (po != null && po.size() > 0 && span != null && StringUtils.isNotBlank(span.text()) && span.text().length() > 5) {
            pubTime = span.text().substring(5);
        }

        //  RegExpUtil.regCheck()
        if (pubTime == null) {
            String par = "发布时间:([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";

            pubTime = RegExpUtil.regGet(allText, par).replaceAll("发布时间:","");
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
        List<String> cellInfoList2 = new LinkedList<>();
        if (p.size() < 4) {
            System.err.println("p标签数量少");
            //获取包含: 的 字符串

            String p1 = " :";
            String p2 = ": ";
            allText = allText.replace((char) 160, (char) 32).replaceAll(p1, ":").replaceAll(p2, ":");


            String[] split = allText.split(" ");
            for (String s : split) {
                if (HtmlUtils.countString(s, ":") == 1) {
                    cellInfoList2.add(s);
                }
            }

            //  String[] s1 = str.split(" ");


        }


        if (Integer.valueOf(data.getLocation()) > 0) {
            logger.debug("表格内容解析开始*******************************");//表格解析开始
            cellInfoList = TableDeal.tableSizeOverOne(tableList);
            logger.debug("打印解析内容\n" + Arrays.toString(cellInfoList.toArray()));
            logger.debug("表格内容解析结束");

        }

        logger.debug(data.getUrl());
        logger.debug("tableSize: " + tableList.size());
        cellInfoList.addAll(cellInfoList2);
        Map map1 = HtmlUtils.plistToMap(cellInfoList);
        map = HtmlUtils.prasePToMap(p);
        map.putAll(map1);
        data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());

        if (data.getWinBidTotalAmount() == null || data.getWinBidBisAddr() == null || data.getWinBidBisName() == null) {
            //		FileUtils.writeAppendFile("中标相关信息.txt",map1.keySet().toString());
        }





        data.setPubTime(pubTime);
        DataContentWithBLOBs datadb = mapper.selectById("spider_4_ggzy_hebei_url", data.getUrlId());
    //    data.setPubTime(datadb.getPubTime());
        data.setStageShow(datadb.getStageshow());
        data.setClassifyShow(datadb.getCategory());

        data.setTitle(datadb.getTitle());
        data.setRegion(datadb.getRegion());
        if (data.getProName() == null || data.getProName().length() > 30) {
            data.setProName(data.getTitle());
        }

        //TODO 每次新增加省份, 下面需要微调

        //  data.setClasses(data.getCategory());
        data.setClasses(data.getCategory());
        //   data.setCategory(mem);
        //   data.setPubTime(pubTime);
        //    DataContentWithBLOBs datadb = mapper.selectById("spider_8_ggzy_jiangshu_url", data.getUrlId());
        //    data.setPubTime(datadb.getPubTime());
        //信息类型
        // 业务类型
        data.setClassifyShow("政府采购");
        //    data.setTitle(datadb.getTitle());
        //   data.setRegion(datadb.getRegion());
        data.setDistrictShow(data.getRegion());
        if (data.getProName() == null || data.getProName().length() > 30) {
            data.setProName(data.getTitle());
        }

        data.setOther(map.size() == 0 ? null : JSON.toJSONString(map));

        FieldUtils.formatDateAndTime(data);
        FieldUtils.formatContact(data);

        if (data.getNoticeTime() == null) {
            List<String> dates = RegExpUtil.getMatchers(allText, FieldUtils.p_date);
            if (dates != null && dates.size() > 1) {
                data.setNoticeTime(FieldUtils.formatDate(dates.get(1)));
            }


        }
        return data;
    }

}
