package com.spring.springbootdemo.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.WinBisInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/10
 */
public class HtmlUtils {

    private static final String REG_TABLE = "<table.*?>[\\s\\S]*?<\\/table>";


    /**
     * 横向表头,解析
     *
     * @param tables
     * @return
     */
    public static List<WinBisInfo> parseRowTable(List<Element> tables) {

        List<StringBuffer> list = new ArrayList<>();
        //   Map<String, String> map = new HashMap();
        //      StringBuffer colSpanStr = new StringBuffer();
        for (Element tab : tables) {
            tableToListStr(list, tab);
            //  System.err.println(sb.toString());
        }
        //  int count = (st.length()-st.replace(M, "").length())/M.length();
        List<String> listObj = new LinkedList();
        LinkedList<Map> queue = new LinkedList();
        //一个sb为一个表的字符串拼接
        for (StringBuffer sb : list) {
            //  String contentStr = sb.append(colSpanStr.toString()).toString();
            String contentStr = sb.toString();
            if (contentStr.endsWith("|")) {
                contentStr = contentStr.substring(0, contentStr.length() - 1);
            }
            //判断同一字段出现次数，即有几条供应商信息
            String m = contentStr.split("\\:")[0];
            if (m.length() < 1) {
                continue;
            }
            int count = (contentStr.length() - contentStr.replace(m, "").length()) / m.length();
            if (count > 1) {
                contentStr = contentStr.replace(m, "#@" + m);
                String[] split = contentStr.split("\\#\\@");
                List<String> listTemp = Arrays.asList(split);
                for (String s : listTemp) {
                    if (StringUtils.isBlank(s)) {
                        continue;
                    }
                    listObj.add(s);
                }
            } else {
                listObj.add(contentStr);
            }
        }
        for (String string : listObj) {
            Map<String, String> tableMap = new HashMap();
            String[] split1 = string.split("\\|");
            List<String> strings = Arrays.asList(split1);
            for (String str : strings) {
                String[] split2 = str.split(":");
                if (StringUtils.isBlank(str) || split2.length != 2) {
                    continue;
                }
                if (tableMap.containsKey(split2[0])) {
                    continue;
                }
                tableMap.put(split2[0], split2[1]);
            }
            queue.add(tableMap);//一个tableMap一条供应商信息对象
        }
        //     Map tempMap = new HashMap();
        List resList = new LinkedList();
        for (int i = 0; i < queue.size(); i++) {
            Map map1 = queue.get(i);
            String s = JSON.toJSONString(map1);
            //判断跳过详细信息表格内容
            if (s.contains("规格型号") || s.contains("数量") || s.contains("服务要求")) {
                continue;
            }
            WinBisInfo winbis = new WinBisInfo();
            Iterator iterator = map1.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                if ((key.contains("供应商名称"))) {
                    winbis.setWinBidBisName(value);
                    continue;
                }
                if ((key.contains("地址"))) {
                    winbis.setWinBidBisAddr(value);
                    continue;
                }
                if (key.contains("金额") || key.contains("价格") || key.contains("成交")) {

                    if (value.contains("元") || value.contains("万")) {
                        winbis.setWinBidAmount(value);
                    } else if (key.contains("元") || key.contains("万")) {
                        if (key.contains("（")) {
                            key = key.replace("（", "(").replace("）", ")");

                            Pattern pattern = Pattern.compile("\\(([^)]*)\\)");
                            Matcher matcher = pattern.matcher(key);
                            String unit = "";
                            if (matcher.find()) {
                                unit = matcher.group();
                            }
                            winbis.setWinBidAmount(value + unit);
                        }

                    } else {
                        winbis.setWinBidAmount(value);
                    }
                    continue;
                }
                if (key.contains("信用")) {
                    winbis.setCreditNo(value);
                    continue;
                }

            }
            if (winbis.getWinBidBisName() != null) {

                resList.add(winbis);
            }

            //   System.err.println(winbis);
        }

        //   return null;
        return resList;
        //    return map;
    }

    private static void tableToListStr(List<StringBuffer> list, Element tab) {
        StringBuffer sb = new StringBuffer();
        //   Document tab = Jsoup.parse(table);
        Elements trs = tab.getElementsByTag("tr");
        Elements first = trs.first().children();
        for (int i = 1; i < trs.size(); i++) {
            Elements element = trs.get(i).getElementsByTag("td");
            Map<Integer, String> m = null;
            int size;
            if (element.size() == first.size()) {
                size = element.size();

            } else {
                size = element.size() > first.size() ? first.size() : element.size();
            }
            for (int j = 0; j < size; j++) {
                String name = first.get(j).text() + ":" + element.get(j).text();
             /*   if (element.get(j).hasAttr("colspan")) {
                    colSpanStr.append(element.get(j).text() + "|");
                    continue;
                    //  logger.info("has colspan = " + element.get(j).attr("colspan") + element.get(j).text());
                } */
                if (element.get(j).hasAttr("rowspan")) {
                    String num = (element.get(j)).attr("rowspan");
                    m = new HashMap();
                    //直接使用# 号可能会有问题
                    m.put(j, name + "#!$" + num);
                    //下次循环到该index 直接拼接本次str
                }
                if (m != null && m.containsKey(j)) {
                    String[] split = m.get(j).split("\\#\\!\\$");
                    if (Integer.valueOf(split[1]) < 1) {
                        m.remove(j);
                    } else {
                        name = first.get(j).text() + ":" + split[0];
                        m.put(j, name + "#!$" + (Integer.valueOf(split[1]) - 1));
                    }
                }
                sb.append(name);
                sb.append("|");
            }
        }
        list.add(sb);
    }


    private static final String UNCLEAR_FILED = "地址|联系地址|详细地址|电话|联系方式|联系人|联系电话|电话|单位名称";

    public static boolean keyIsUnclear(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        String[] split = UNCLEAR_FILED.split("\\|");

        for (String str : split) {
            if (str.equals(StrUtil.cleanBlank(key))) {
                return true;
            }

        }
        return false;
    }


    public static boolean keyIsContains(String key, String fieldStr) {

        if (StringUtils.isBlank(fieldStr) || fieldStr.split(":").length < 2) {
            return false;
        }
        String s = fieldStr.split(":")[1];
        if (!s.endsWith("/")) {
            s = s + "/";
        }

        String[] split = s.split("/");
        for (String str : split) {

            if (key.equals(str)) {
                return true;
            } else if (str.equals("中标价")) {
                if (key.contains("成交价")
                        || key.contains("中标价")
                        || key.contains("中标金额")
                        || key.contains("成交金额")
                        || key.contains("总成交金额")
                        || key.contains("总成交额")
                        || key.contains("总成交")
                        || key.contains("总金额")
                        || key.contains("总中标额")
                        || key.contains("总报价")

                ) {
                    return true;
                }


            } else if (str.equals("供应商地址")) {
                if (key.contains("供应商")
                        || key.contains("中标人")
                        || key.contains("中标机构")

                ) {
                    return key.contains("地址");
                }
            } else if (str.equals("供应商名称")) {
                if ((key.contains("供应商")
                        || key.contains("中标人")
                        || key.contains("中标机构")) && (!key.contains("地址") && !key.contains("金额") && !key.contains("是否") && !key.contains("代码"))

                ) {
                    return true;
                }
            } else if (str.equals("项目预算")) {
                if ((key.contains("项目预算")
                        || key.contains("预算金额")
                        || key.contains("预算费用"))

                ) {
                    return true;
                }
            } else if (str.equals("招标公告日期")) {
                if ((key.contains("公告日期")
                        || key.contains("公告时间")
                        || key.contains("公告日期")
                        || key.contains("发布时间")
                        || key.contains("公布时间"))

                ) {
                    return true;
                }   // 此处说明 : 优先考虑是否为代理联系方式, 若不是,默认为项目联系方式
            } else if (str.equals("代理联系方式")) {
                if ((key.contains("代理") && (key.contains("电话") || key.contains("联系方式")))) {
                    return true;
                }
            } else if (str.equals("采购单位联系方式")) {
                if ((key.contains("电话") || key.contains("联系方式"))) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String reviseKey(String key) {
        Pattern p_script = Pattern.compile("[^\u4e00-\u9fa5]", Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(key);
        key = m_script.replaceAll(""); // 过滤script标签

        //首个字符为中文 一 , 二等
    /*    if(key.length()>0 && key.startsWith("[\\u4e00 \\u4e8c \\u4e09 \\u56db \\u4e94 \\u516d \\u4e03 \\u516b \\u4e5d \\u5341]{1,2}")){
            key = key.substring(1,key.length());
        }
*/
        key = key.replaceFirst("^[\\u4e00 \\u4e8c \\u4e09 \\u56db \\u4e94 \\u516d \\u4e03 \\u516b \\u4e5d \\u5341]{1,2}", "");
        //清楚中间空白
        key = StrUtil.cleanBlank(key);
        return key;
    }


    public static List<Element> getHtmlTableList(Document parse) {
        //    Elements newsCon = parse.getElementsByClass("newsCon");
        Element body = parse.body();
        List<String> tableList = new ArrayList<>();
        Elements tabs = null;
        if (body != null) {
            //     Element element = newsCon.get(0);
            tabs = body.getElementsByTag("table");
        /*    if (ts != null && ts.size() > 0) {
                Pattern pt = Pattern.compile(REG_TABLE);
                Matcher m = pt.matcher(body.html());
                while (m.find()) {
                    tableList.add(m.group());
                }
            }

         */
        }
        List<Element> list = new ArrayList<>();
        if (tabs == null) {
            return list;
        }
        for (Element table : tabs) {
            //       Document table = Jsoup.parse(tableStr);
            Elements tables = table.select("table");
            Element firstTable = tables.first();
            if (tables.size() > 1) {
                Elements children = firstTable.children();
                children.select("table").remove();

            }
            list.add(firstTable);
        }


        return list;

    }


    public static String maxStrLen(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        ;
        if (str.length() > 200) {
            return str.substring(0, 200);
        }
        return str;
    }

    public static String removeCNStr(String content) {

        Pattern p_script = Pattern.compile("：", Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(content);
        content = m_script.replaceAll(":");
        return content;

    }


    public static Map prasePToMap(Elements elements) {

        List<String> list = new LinkedList<>();
        boolean flag = false;
        String names = "";
        for (Element element : elements) {
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

        Map map = plistToMap(list);
        return map;
    }


    public static int countString(String str, String s) {
        int count = 0;
        while (str.indexOf(s) != -1) {

            str = str.substring(str.indexOf(s) + 1, str.length());
            count++;

        }
        return count;
    }

    public static Map plistToMap(List<String> list) {
        Map map = new HashMap();
        String flag = "";
        for (int i = 0; i < list.size(); i++) {
            String p = list.get(i);
            if (p.startsWith("第一") || p.startsWith("第二") || p.startsWith("第三") || p.startsWith("第四") || p.startsWith("第五") || p.startsWith("第六")) {
                flag = p.substring(0, 2);
                String s = list.get(i + 1);
                String s2 = list.get(i + 2);
                if (s.contains("地址") || s.contains("金额")) {
                    list.remove(i + 1);
                    list.add(i + 1, "ZW" + flag + s);
                }
                if (s2.contains("地址") || s2.contains("金额")) {
                    list.remove(i + 2);
                    list.add(i + 2, "ZW" + flag + s2);
                }

            }


            if (countString(p, ":") > 1 && (p.contains(String.valueOf((char) 32)) || p.contains(String.valueOf((char) 160)))) {
                String[] strings = CellUtils.splitCellInfo(p);
                if (strings != null) {

                    list.addAll(Arrays.asList(strings));
                    continue;
                }
            }
            ;

            p = p.replaceFirst(":", "#");
            String[] split = p.split("#");

            if (split.length == 2 && split[0].length() > 1) {
                //如果key 是联系人 . 地址 联系电话等有歧义信息,index向上 最多2 关联
                String key = split[0];
                if (key.equals("址")) {
                    key = "地址";
                }
                key = HtmlUtils.reviseKey(key);
                if (HtmlUtils.keyIsUnclear(key)) {
                    if (i >= 1) {
                        String key_up1 = list.get(i - 1);
                        //	String key_up2 = list.get(i - 2);
                        //优先认定index相隔为1的
                        if (key_up1.contains("代理")) {
                            key = "代理" + key;
                        } else {
                            key = "采购单位" + key;
                        }
                    }
                    list.set(i, key + ":" + split[1]);
                }
                //去除key非中文,以及首个字符为 一,二等
             /*   if(StringUtils.isBlank(key) && split[1].contains(":")){
                    String[] strings = split[1].split(":");
                    map.put(strings[0] ,strings[1]);
                    continue;
                }*/

                if (StringUtils.isNotBlank(key)) {
                    //重复key 第一个为准
                    if (map.containsKey(key)) {
                        continue;
                    }
                    map.put(key, split[1]);
                }
            }
        }

        return map;
    }


}
