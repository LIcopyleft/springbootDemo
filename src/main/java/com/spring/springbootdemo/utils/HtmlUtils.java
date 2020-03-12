package com.spring.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.model.WinBisInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/10
 */
public class HtmlUtils {
    public static List<WinBisInfo> parseRowTable(List<String> tables) {

        List<StringBuffer> list = new ArrayList<>();
        Map<String, String> map = new HashMap();
        StringBuffer colSpanStr = new StringBuffer();
        for (String table : tables) {
            StringBuffer sb = new StringBuffer();
            Document tab = Jsoup.parse(table);
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
                        m.put(j, name + "#" + num);
                        //下次循环到该index 直接拼接本次str
                    }
                    if (m != null && m.containsKey(j)) {
                        String[] split = m.get(j).split("#");
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
            list.add(sb);
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
                contentStr = contentStr.replace(m, "#" + m);
                String[] split = contentStr.split("\\#");
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
        Map tempMap = new HashMap();
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

}
