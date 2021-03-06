package com.spring.springbootdemo.thread;

import com.alibaba.fastjson.JSON;
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

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanTask implements Runnable {

    private static final String KEY_WORD = "区域坐标";

    private static final Set<String> FLAG =  new HashSet<>();
    // 1


    private static final int INSERT_MAX = 1000;
    private static final int KEY_NUM = 6;
    private static final String TABLE_NAME = "spider_2_ggzy_content";
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
            List<DataContentWithBLOBs> dataContent = mapper.selectAll(beginIndex, querySize,TABLE_NAME);
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
                    //    Document parse = Jsoup.parse(content);
                    // DataContentWithBLOBs dcb = cleanMethod(data,parse);
                    DataContentWithBLOBs dcb = cleanMethodBisReport(data);
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
                 //   row += mapper.insertList(list);
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
    private static final String REG_TABLE = "<table.*?>[\\s\\S]*?<\\/table>";




    private DataContentWithBLOBs cleanMethodBisReport(DataContentWithBLOBs data) throws Exception {
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
        DataContentWithBLOBs dcb = new DataContentWithBLOBs();
        BeanUtils.copyProperties(data, dcb);
        dcb.setProname(proName);
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
                        colSpanStr.append(element.get(j).text()+"|");
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


    public static Map<String, String> getTableColumnData(Element table, String rowSelectRange) {
        Map<String, String> map = new LinkedHashMap<>();
        Elements trs = table.select("tr");
        if (!StringUtils.isEmpty(rowSelectRange)) {
            String[] deleteRows = rowSelectRange.split(",");
            int offsetIndex = 0;
            for (int i = deleteRows.length - 1; i >= 0; i--) {
                int index = Integer.parseInt(deleteRows[i]);
                if (index < 0) {
                    index = Math.abs(index);
                    index = trs.size() - (index - offsetIndex);
                    trs.remove(index);
                    offsetIndex++;
                } else {
                    trs.remove(index - 1);
                }
            }
        }
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            // th 和 td 混合的情况下，取子元素
            if (tr.select("th").size() > 0) {
                tds = tr.children();
            }
            int index = 0;
            String name = "";
            for (Element td : tds) {
                index++;
                if (index % 2 == 0) {
                    if (StringUtils.isEmpty(name)) {
                        continue;
                    }
                    map.put(name, td.text().trim());
                } else {
                    name = td.text();
                    if (name.endsWith(":") || name.endsWith("：")) {
                        name = name.substring(0, name.length() - 1);
                    }
                    name = name.trim();
                }
            }
        }
        return map;
    }

}
