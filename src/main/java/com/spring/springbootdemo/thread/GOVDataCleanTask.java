package com.spring.springbootdemo.thread;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.TableCell;
import com.spring.springbootdemo.model.WinBisInfo;
import com.spring.springbootdemo.utils.HtmlUtils;
import com.spring.springbootdemo.utils.ReflectionUtils;
import com.spring.springbootdemo.utils.SpringContextHolder;
import com.spring.springbootdemo.utils.TableConvert;
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

public class GOVDataCleanTask implements Runnable {

    private static final String KEY_WORD = "区域坐标";
    private static final String INSERT_TABLE_NAME = "spider_2_ggzy_content_clean_temp";


    private static final Set<String> FLAG = new HashSet<>();
    // 1


    private static final int INSERT_MAX = 1000;
    private static final int KEY_NUM = 6;

    private int beginIndex;
    private int querySize;
    private String stageShow;
    private CountDownLatch latch;


    private static final Logger logger = LoggerFactory.getLogger(GOVDataCleanTask.class);


    public GOVDataCleanTask(int beginIndex, int querySize, String stageShow, CountDownLatch latch) {
        this.beginIndex = beginIndex;
        this.querySize = querySize;
        this.stageShow = stageShow;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            DataContentMapper mapper = SpringContextHolder.getBean("dataContentMapper");
            List<GovData> list = new LinkedList<>();
            List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, querySize, "spider_2_ggzy_content_clean_temp");
            if (dataContent == null || dataContent.size() < 1) {
                logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            List<GovData> datas = new LinkedList<>();
            for (DataContentWithBLOBs data : dataContent) {
                GovData gov = new GovData();
                gov.setProName(data.getProname());
                gov.setTitle(data.getTitle());
                gov.setClassifyShow(data.getClassifyshow());
                gov.setStageShow(data.getStageshow());
                gov.setStageName(data.getStagename());
                gov.setContent(data.getContent());
                gov.setUrl(data.getUrl());
                gov.setUrlId(data.getUrlId());
                gov.setClassify(data.getClassify());
                gov.setPubTime(data.getTimeshow());
                gov.setTradeShow(data.getTradeshow());
                gov.setDistrictShow(data.getDistrictshow());
                gov.setPlatformName(data.getPlatformname());
                gov.setLocation(data.getLocation());
                datas.add(gov);

            }


            LinkedBlockingQueue<GovData> queues = new LinkedBlockingQueue();
            for (GovData data : datas) {
                if (stageShow.equals(data.getStageShow())) {
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
                    data.setContent(null);
                    list.add(data);
                } catch (Exception e) {
                    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}", e);
                    continue;
                }

                if (list.size() == INSERT_MAX || queues.size() == 0) {
                    //   System.err.println(Thread.currentThread().getName());
                    if (list.size() < 1) {
                        continue;
                    }
                    //       row += mapper.insertList_BJ(list, "spider_2_ggzy_content_clean_result_zbgg");
                    list.clear();
                    continue;
                }

            }
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            latch.countDown();
        }
    }

    //

    /**
     * 获取只有一table 一row 一col ，按p标签处理
     *
     * @param data
     * @return
     */

    public static DataContentWithBLOBs onlyOneRowAndOneCol(DataContentWithBLOBs data) {

        //只有一个table标签
        if (!data.getLocation().equals("1") || "采购合同".equals(data.getStageshow())) {
            return null;
        }
        String content = data.getContent();
        Document document = Jsoup.parse(content);

        List<Element> tableList = HtmlUtils.getHtmlTableList(document);
        Elements tables = document.getElementsByTag("table");
        if (tableList.size() < 1) {
            return null;
        }
        for (Element table : tables) {
            Elements trs = table.getElementsByTag("tr");
            int trSize = trs.size();
            int tdSize = table.getElementsByTag("td").size();
            Elements p = table.getElementsByTag("p");
            int pSize = p.size();

            if (trSize == tdSize && pSize == trSize) {
                //logger.info(data.getUrl());
                // HtmlUtils.
                data.setCoordinate("1");
            }


        }

        return data;
    }

    /**
     * 资审/采购公告
     *
     * @param data
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    private static GovData clean_cggg(GovData data) throws InvocationTargetException, IllegalAccessException {
        {
            //    logger.info(data.getUrl());
            // logger.info(data.getUrl());
            //   String regEx_az = "[\\s\\@\\#\\$\\%\\^\\&\\*\\{\\}\\[\\]\\;\\'\\“\\”\\。\\，\\+\\/\\<\\>\\?\\《\\》\\=]+";
            //    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String content = data.getContent();
            Map map = new HashMap();
            content = HtmlUtils.removeCNStr(content);
            Document parse = Jsoup.parse(content);
            Elements sitemap = parse.getElementsByClass("location");
            //获取导航目录信息
            String memu = "";
            if (sitemap != null && sitemap.size() > 0) {
                memu = StrUtil.cleanBlank(sitemap.get(0).text().replace("您的当前位置:", ""));

            }

            Elements p = parse.getElementsByTag("p");
            List<Element> tableList = HtmlUtils.getHtmlTableList(parse);
            map = HtmlUtils.prasePToMap(p);
            data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());
            if (tableList.size() < 1) {

                return data;
            } else if (tableList.size() == 1) {
                Element table = tableList.get(0);
            //    Document table = Jsoup.parse(tab);
                Elements thead = table.getElementsByTag("thead");
                Elements tbody = table.getElementsByTag("tbody");
                Elements ths = table.getElementsByTag("th");
                Elements trs = table.getElementsByTag("tr");

                //1.判断是否包含 thread 表头标识标签,
                if (thead.size() > 0 && tbody.size() > 0) {
                    Elements td = thead.get(0).getElementsByTag("td");
                    Elements tbodyTd = tbody.get(0).getElementsByTag("td");

                    for (Element tbtd : tbodyTd) {
                        if (tbtd.hasAttr("colSpan")) {

                        }
                    }
                    for (int i = 0; i < td.size(); i++) {
                        Element element = td.get(i);


                    }

                }

                //2.判断是否有th标签
                if (ths.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    Elements td1 = table.getElementsByTag("td");

                    for (int i = 0; i < ths.size(); i++) {
                        Element element = ths.get(i);
                        sb.append(element.text() + ":" + (td1.size() > i ? td1.get(i).text() : ""));
                        sb.append("|");
                    }
                    /*
                    String s = sb.toString();
                    String s_1 = s;
                    StringBuilder str = new StringBuilder();
                    for (int j = 0; j < trs.size(); j++) {
                        Element element = trs.get(j);
                        if (element.getElementsByTag("th").size() > 0) {
                            continue;
                        }
                        Elements td = element.getElementsByTag("td");
                        if (td.size() == ths.size()) {
                            int u = 0;
                            for (Element tdd : td) {
                                s = s.replace("##" + u, tdd.text());
                                u++;
                            }
                            str.append(s);
                            str.append("|");
                            s = s_1;

                        }
                    } */
                    String str = sb.toString();
                    if (str.equals("|")) {
                        return data;
                    }
                    str = str.lastIndexOf("|") == str.length() - 1 ? str.substring(0, str.length() - 1) : str.toString();

                    List<String> list = Arrays.asList(str.split("\\|"));
                    map = HtmlUtils.plistToMap(list);
                    data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());
                    return data;
                } else {
                    //3.判断是否为左右结构.一行tr 内 两个td
                    boolean flag = true;
                    for (int j = 0; j < trs.size(); j++) {
                        Element element = trs.get(j);
                        Elements td = element.getElementsByTag("td");
                        if (td.size() != 2) {
                            flag = false;
                            break;
                        }


                    }
                    if (flag) {
                        List<String> list = new ArrayList<>();
                        for (Element tr : trs) {
                            Elements tds = tr.getElementsByTag("td");

                            boolean contains = tds.get(1).text().contains(":");
                            if (contains) {
                                //正则判断是否为时间格式

                                Elements p1 = tds.get(1).getElementsByTag("p");
                                if (p1.size() < 1) {
                                    continue;
                                }
                                for (Element p2 : p1) {
                                    list.add(p2.text());
                                }
                            } else {
                                String key = tds.get(0).text();
                                if (!key.contains(":")) {
                                    key = key + ":";
                                }

                                list.add(key + tds.get(1).text());
                            }


                        }

                        map = HtmlUtils.plistToMap(list);
                        data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());
                        return data;

                    }


                }

                //4.上下结构表头在上
                List<WinBisInfo> winBisInfos = HtmlUtils.parseRowTable(tableList);
                if (winBisInfos.size() > 1) {
                    data.setWinBisInfoStr(JSON.toJSONString(winBisInfos));
                }
                return data;

            } else {
                //如果td 内包含 table,获取table内所有td , 替换 当前td   if td hasColSpan  获取table内所有td

            }


            //  Set set = Contant.filedValueSet();
            data.setOther(map.size() == 0 ? null : JSON.toJSONString(map));
            data.setClasses(memu.length() > 200 ? null : memu);
            data.setContent(null);
            //    data.setClassifyShow(data.getStageShow());//政府采购  更正公告
            // System.out.println(JSON.toJSONString(map));
            // TODO 时间 金额 等字段在这里处理
            String proName = data.getProName();
            if (StringUtils.isNotBlank(proName) && proName.length() > 200) {
                data.setProName(proName.substring(0, 200));
            }
            //对金额 日期等字段处理
            //   String budgetAmount = data.getBudgetAmount();
            //   logger.info("预算金额： "+ budgetAmount);
            //   System.err.println(data.getTenderingFilePrice() + "/" + data.getBudgetAmount() + "/" + data.getWinBidTotalAmount());
            //
        /*    if(data.getWinBisInfoStr() == null && data.getWinBidBisName() != null){
                List<WinBisInfo> winBisInfos = new LinkedList<>();
                WinBisInfo info = new WinBisInfo();
                info.setWinBidBisName(data.getWinBidBisName());
                info.setWinBidBisAddr(data.getWinBidBisAddr());
                info.setCreditNo(null);

                winBisInfos.add(info);
                data.setWinBisInfoStr(JSON.toJSONString(winBisInfos));
            }
*/
            return data;
        }


    }

    /**
     * 中标公告,处理和cggg 完全相同
     *
     * @param data
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static GovData clean_zbgg(GovData data) throws InvocationTargetException, IllegalAccessException {
        {

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
            if (tableList.size() > 1) {
                logger.debug(data.getUrl());
                for (Element table : tableList) {
                 //   Document table = Jsoup.parse(tableStr);
                //    Elements table1 = table.getElementsByTag("table");
                    List<TableCell> tableCells = TableConvert.toCellList(table);
                    //分析单元格是否为表头

                //    System.err.println(JSON.toJSONString(tableCells));
                }

            }


            map = HtmlUtils.prasePToMap(p);
            data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());

            data.setOther(map.size() == 0 ? null : JSON.toJSONString(map));
            data.setClasses(memu.length() > 200 ? null : memu);
            data.setPubTime(pubTime);
            data.setContent(null);
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
