package com.spring.springbootdemo.thread;

import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.mapper.DataContentMapper;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.WinBisInfo;
import com.spring.springbootdemo.utils.HtmlUtils;
import com.spring.springbootdemo.utils.ReflectionUtils;
import com.spring.springbootdemo.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanBeiJingTask implements Runnable {

    private static final String KEY_WORD = "区域坐标";
	private static final String INSERT_TABLE_NAME = "spider_2_ggzy_shanxi_content_clean_temp";


	private static final Set<String> FLAG = new HashSet<>();
    // 1


    private static final int INSERT_MAX = 1000;
    private static final int KEY_NUM = 6;

    private int beginIndex;
    private int querySize;
    private String stageShow;
    private CountDownLatch latch;


    private static final Logger logger = LoggerFactory.getLogger(CleanBeiJingTask.class);


    public CleanBeiJingTask(int beginIndex, int querySize, String stageShow, CountDownLatch latch) {
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
            List<GovData> dataContent = mapper.selectAllBJ(beginIndex, querySize);
            if (dataContent == null || dataContent.size() < 1) {
                logger.warn(Thread.currentThread().getName() + "end====query db is null====beginIndex=" + beginIndex);
                return;
            }
            LinkedBlockingQueue<GovData> queues = new LinkedBlockingQueue();
            for (GovData data : dataContent) {
                if (stageShow.equals(data.getCategory())) {
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
                    // GovData dcb = cleanMethod(data,parse);
                    GovData dcb = cleanMethod_2(data);
                    if (dcb == null) {
                        continue;
                    }
                    list.add(dcb);
                } catch (Exception e) {
                    logger.error("ParaseErr{urlId:" + data.getUrlId() + "====url:" + data.getUrl() + "}", e);
                    continue;
                }

                if (list.size() == INSERT_MAX || queues.size() == 0) {
                    //   System.err.println(Thread.currentThread().getName());
                    if (list.size() < 1) {
                        continue;
                    }
                    row += mapper.insertList_BJ(list,INSERT_TABLE_NAME);
                    list.clear();
                    continue;
                }

            }
            logger.info(Thread.currentThread().getName() + "==url_id over" + dataContent.get(dataContent.size() - 1).getUrlId() + "=====insert\t" + row + "行");
        } finally {
            latch.countDown();
        }
    }

    //政府采购
    private static GovData cleanMethod_1(GovData data) throws InvocationTargetException, IllegalAccessException {

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

        data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
        data.setOther(JSON.toJSONString(map));
        data.setClasses(memu);
        data.setContent(null);

        // System.out.println(JSON.toJSONString(map));
        return data;
    }

    //政府采购>成交结果公告 清洗
    private static GovData cleanMethod_2(GovData data) throws InvocationTargetException, IllegalAccessException {
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
        Elements tables = parse.getElementsByTag("p");

        if ("政府采购>成交结果公告".equals(data.getCategory())) {
            //TODO 判断表格类型
            Elements newsCon = parse.getElementsByClass("newsCon");
            List<String> tableList = new ArrayList<>();
            if (newsCon != null && newsCon.size() > 0) {
                Element element = newsCon.get(0);
                Elements ts = element.getElementsByTag("table");
                if (ts != null && ts.size() > 0) {
                    Pattern pt = Pattern.compile(REG_TABLE);
                    Matcher m = pt.matcher(element.html());
                    //   List<String> tableList = new ArrayList<>();
                    while (m.find()) {
                        tableList.add(m.group());
                    }
                }
            }
            logger.info(data.getUrl());
           /* if(data.getUrlId() == 51219){
                logger.info("");
            }*/
            List<WinBisInfo> winBisInfos = HtmlUtils.parseRowTable(tableList);

            data.setWinBisInfoStr(winBisInfos.size() > 0 ? JSON.toJSONString(winBisInfos) : null);
        }

        List<String> list = new LinkedList<>();
        boolean flag = false;
        String names = "";
        for (Element element : tables) {
            String text = element.text();
            if (flag) {
                names = names + "专家名单:" + text;
                list.add(names);
                flag = false;
                names = "";
                continue;
            }
            if (StringUtils.isNotBlank(text) && text.contains(":")) {

                if (text.split(":").length < 2 || text.split(":")[0].length() > 15) {
                    continue;
                } else if (text.contains("名单") && text.split(":").length < 2) {
                    flag = true;
                }
                list.add(text);
            }
        }

        for (String p : list) {
            p = p.replaceFirst(":", "#");
            String[] split = p.split("#");
            if (split.length == 2) {
                map.put(split[0], split[1]);
            }
        }
        //  Set set = Contant.filedValueSet();
        data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());// mapToField
        data.setOther(JSON.toJSONString(map));
        data.setClasses(memu.length() > 200 ? null : memu);
        data.setContent(null);
        data.setClassifyShow("成交结果公告");//政府采购  更正公告
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
        if (data.getWinBisInfoStr() == null && data.getWinBidBisName() != null && StringUtils.isNotBlank(data.getWinBidBisName())) {
            List<WinBisInfo> winBisInfos = new LinkedList<>();
            WinBisInfo info = new WinBisInfo();
            info.setWinBidBisName(data.getWinBidBisName());
            info.setWinBidBisAddr(data.getWinBidBisAddr());
            info.setCreditNo(null);
            info.setWinBidAmount(data.getWinBidTotalAmount());
            winBisInfos.add(info);
            data.setWinBisInfoStr(JSON.toJSONString(winBisInfos));
        }

        return data;
    }


    /*
     * @description  交易公告 / 中标公告类型清洗
     * @author tengchao.li
     * @date 2020/2/26
     * @param data
     * @param parse
     * @return com.spring.springbootdemo.model.GovData
     */
    private static final String REG_TABLE = "<table.*?>[\\s\\S]*?<\\/table>";

    private GovData cleanMethodBisReport(GovData data) throws Exception {
        String content = data.getContent();
        if (StringUtils.isBlank(content)) {
            return null;
        }
        Document parse = Jsoup.parse(content);
        Elements detailContent = parse.getElementsByClass("detail_content");
        String html = "<div>" + detailContent.html() + "</div>";
        // Element ps = parse.select(".detail_content").get(0);
        String proName = parse.select(".h4_o").get(0).text();
        //获取集合类中自己需要的某个table
        String htmlStr = html.replaceAll("\"", "'").replaceAll("&nbsp", "").replaceAll("：", ":").replaceAll("、", ",").replaceAll("，", ",");
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_ts = "(\\d{1,2},)|(十一,)|(二,)|(三,)|(四,)|(五,)|(六,)|(七,)|(八,)|(九,)|(十,)|(一,)|([（一）（二）（三）（四）（五）（六）（七）（八）（九）（十）],{0,1})"; // 清除数字编号
        //   String regEx_az ="[\\s\\@\\#\\$\\%\\^\\&\\*\\{\\}\\[\\]\\、\\;\\'\\“\\”\\.\\。\\,\\，\\+\\/\\<\\>\\?\\《\\》\\=]+";
        String regEx_az = "[\\s\\@\\#\\$\\%\\^\\&\\*\\{\\}\\[\\]\\;\\'\\“\\”\\。\\，\\+\\/\\<\\>\\?\\《\\》\\=]+";

        //    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_ts = Pattern.compile(regEx_ts, Pattern.CASE_INSENSITIVE);
        Matcher m_ts = p_ts.matcher(htmlStr);
        htmlStr = m_ts.replaceAll("");

        Pattern p_table = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_table = p_table.matcher(htmlStr);
        String noTable = m_table.replaceAll("");
        Document parseNoTable = Jsoup.parse(noTable);
        /*
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签  */
        Pattern pt = Pattern.compile(REG_TABLE);
        Matcher m = pt.matcher(htmlStr);
        List<String> tableList = new ArrayList<>();
        while (m.find()) {
            tableList.add(m.group());
        }
       /* if (noTable.contains("<table") || noTable.contains("table>")) {
            logger.error("包含 table 标签 \n" + noTable);
            return null;
        } */
        StringBuilder sbd = new StringBuilder();
        Elements children = parseNoTable.getAllElements();
        for (Element child : children) {
            String text = child.text();
            if (StringUtils.isNotBlank(text)) {
                //  System.out.println(child.tagName());
                //  if("p".equalsIgnoreCase(child.tagName()) || "li".equalsIgnoreCase(child.tagName())){
                if ("#root html body tbody div table tr th td ul".contains(child.tagName())) {
                    continue;
                }
                if (text.contains(":") || text.contains("：")) {
                    Pattern pattern = Pattern.compile(regEx_az, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(text);
                    text = matcher.replaceAll("");
                    sbd.append(text);
                    sbd.append("|");
                }
            }
        }
        String noTableHtmlStr = sbd.toString().trim();
        Map<String, String> colMap = new HashMap();
        String[] split1 = noTableHtmlStr.split("\\|");
        List<String> strings = Arrays.asList(split1);
        for (String s : strings) {
            String[] split = s.split(":");
            if (StringUtils.isBlank(s) || split.length != 2) {
                continue;
            }
            if (colMap.containsKey(split[0])) {
                continue;
            }
            colMap.put(split[0], split[1]);
        }
        if (parseNoTable == null) {
            //记录urlid 重新解析
            logger.error("detail_content is null url_id = " + data.getUrlId());
            return null;
        }
        logger.info(data.getUrl());
/*
        采购项目名称
        品目
        采购单位
        公告时间
        行政区域
        中标日期
        本项目招标公告日期
        评审专家名单
        总中标金额
        项目联系人
        项目联系电话
        采购单位
        采购单位地址
        采购单位联系方式
        代理机构名称
        代理机构地址
        代理机构联系方式
*/
        //TODO 暫不支持多表格
        Map<String, Map> resMap = new HashMap();
        if (tableList.size() > 1) {
            logger.error("tableSize > 1 urlId = " + data.getUrlId());
            return null;
        }

        Map<String, String> tableMap = parseRowTable(tableList);
        GovData dcb = new GovData();
        BeanUtils.copyProperties(data, dcb);
        dcb.setProName(proName);
      /*  dcb.setProno();
        dcb.setTenderingnoticetime();//招标公告日期
        dcb.setProxyorgphone();
        dcb.setProxyorgaddr();
        dcb.setBuyingunit();
        dcb.setBuyingaddr();
        dcb.setBuyingphone();
        dcb.setExpertname();
        dcb.setWinbidtime();
        dcb.setWinbidtotalamount();
        dcb.setWin
        dcb.setProphone();
*/
        resMap.put("table", tableMap);
        resMap.put("text", colMap);

        String mapStr = JSON.toJSONString(resMap);
        logger.info(mapStr);
        dcb.setOther(mapStr);
        dcb.setContent(null);
        return dcb;
    }

    /**
     * 单 table标签 解析纵向表结构
     *
     * @param
     */
    public static Map<String, String> parseRowTable(List<String> tables) {
    /*    Elements ps = parse.select(".detail_content").get(0).getElementsByTag("p");
        Elements table1 = ps.get(0).getElementsByTag("table");
        */
        //   html(tables.get(0));

        Map<String, String> map = new HashMap();
        StringBuffer sb = new StringBuffer();
        StringBuffer colSpanStr = new StringBuffer();
        for (String table : tables) {
            Document tab = Jsoup.parse(table);

            //   getTableColumnData(tab,"0,9");

            Elements trs = tab.getElementsByTag("tr");
            Elements first = trs.first().children();
            for (int i = 1; i < trs.size(); i++) {
                Elements element = trs.get(i).getElementsByTag("td");
                Map<Integer, String> m = null;
                for (int j = 0; j < element.size(); j++) {
                    String name = first.get(j).text() + ":" + element.get(j).text();
                    if (element.get(j).hasAttr("colspan")) {
                        colSpanStr.append(element.get(j).text() + "|");
                        continue;
                        //  logger.info("has colspan = " + element.get(j).attr("colspan") + element.get(j).text());
                    }
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
            //  System.err.println(sb.toString());
        }

        String contentStr = sb.append(colSpanStr.toString()).toString();
        if (contentStr.endsWith("|")) {
            contentStr = contentStr.substring(0, contentStr.length() - 1);
        }

        Map<String, String> tableMap = new HashMap();
        String[] split1 = contentStr.split("|");
        List<String> strings = Arrays.asList(split1);
        for (String str : strings) {
            String[] split = str.split(":");
            if (StringUtils.isBlank(contentStr) || split.length != 2) {
                continue;
            }
            if (tableMap.containsKey(split[0])) {
                continue;
            }
            tableMap.put(split[0], split[1]);
        }


        return tableMap;
    }


}
