package com.spring.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.TableCell;
import com.spring.springbootdemo.model.WinBisInfo;
import com.spring.springbootdemo.thread.GOVDataCleanTask;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author tengchao.li
 * @description 表格处理逻辑, 表格数 1 大于1 两类
 * @date 2020/4/13
 */
public class TableDeal {
    private static final Logger logger = LoggerFactory.getLogger(TableDeal.class);

    public static List<String> tableSizeOverOne(List<Element> tableList) {
        List<String> cellInfoList = new ArrayList<>();
        for (Element table : tableList) {
            int colMaxIndex = 0;
            int rowMaxIndex = 0;
            //   Document table = Jsoup.parse(tableStr);
            //    Elements table1 = table.getElementsByTag("table");
            logger.debug("抽象化表格。展示表格结构*******************************");
            List<TableCell> tableCells = TableConvert.toCellList(table);
            logger.debug("*********************************************");
            if (tableCells == null || tableCells.size() < 1) {
                continue;
            }
            logger.debug("1.开始判断表格类型********************");
            //1.如果只有两列,特殊处理,按 P 型处理
            colMaxIndex = CellUtils.getMaxIndexRowOrCol("col", tableCells);
            if (colMaxIndex == 1) {
                //判断 单个单元格内是否有多条信息
                //遍历单元格,单元格内包含多个: 并且冒号后
                logger.debug("2.表格类型判定为只有只有两列***********");
                cellInfoList = only2td(tableCells);
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                return cellInfoList;
            }

            //2.包含thead 和 th
            List<String> list = haveThead(table);
            if (list != null) {
                logger.debug("2.表格类型判定为  表头 thread th  型***********");
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                return list;

            }


            logger.debug("2.非特殊类型表格  分析表头 开始***********");

            //分析单元格是否为表头(表头分析)
            int[] maxIndex = TableMarkHeader.markHeader(tableCells);
            logger.debug("2.1 分析表头 表头单元格判定结束***********");
            //领属关系分析模块
            TableMarkHeader.relationshipAnalysis(maxIndex, tableCells);
            logger.debug("2.2 分析表头和单元格领属关系解饿书***********");
            //抽取模块
            //           System.err.println(JSON.toJSONString(tableCells));
            for (TableCell cell : tableCells) {
                String text = cell.getText();
                if(listIsContains(cellInfoList,text)){
                    continue;
                }
                if (cell.isHeader() && text != null && text.contains(":")) {
                    String pattern = "\\([^\\)]*\\)";
                    text = text.replace("（", "(").replace("）", ")").replaceAll(String.valueOf((char) 160), " ").trim();
                    text = text.replaceAll(pattern, "");
                    if (!text.contains(":")) {
                        continue;
                    }
                    cellInfoList.add(text);
                } else if (!cell.isHeader() && text != null && text.contains(":") && !text.contains("20")) {
                    //   text.replace()
                    cellInfoList.add(text);
                } else if (text.contains(":")) {
                    cellInfoList.add(text);
                } else if (!cell.isHeader() && cell.getHeaderType() != null) {
                    cellInfoList.add(cell.getHeaderType() + ":" + text);
                }
            }
        }
        logger.debug("3.表格内容抽取为字符串列表结束******************");
        return cellInfoList;
    }

    private static List<String> only2td(List<TableCell> tableCells) {
        List<String> cellInfoList = new ArrayList<>();
        for (TableCell cell : tableCells) {
            String text = cell.getText();
            if (text == null || StringUtils.isBlank(text)) {
                continue;
            }
            if (HtmlUtils.countString(text, ":") == 1) {
                if (text.split(":").length < 2 || StringUtils.isBlank(text.split(":")[1])) {
                    continue;
                }
            } else if (HtmlUtils.countString(text, ":") > 1) {
                String[] strings = CellUtils.splitCellInfo(text);
                if (strings != null) {
                    for (String str : strings) {
                        cellInfoList.add(str);
                    }
                }
            }
        }
        return cellInfoList;
    }


    public static List<String> haveThead(Element table) {
        //	Element table = tableList.get(0);
        //    Document table = Jsoup.parse(tab);
        Elements thead = table.getElementsByTag("thead");
        Elements tbody = table.getElementsByTag("tbody");
        Elements ths = table.getElementsByTag("th");
        Elements trs = table.getElementsByTag("tr");

        //1.判断是否包含 thread 表头标识标签,
        //2.判断是否有th标签
        if (ths.size() > 0) {
            StringBuilder sb = new StringBuilder();
            Elements td1 = table.getElementsByTag("td");
            if (ths.size() != td1.size()) {
                //List<TableCell> tableCells = TableConvert.toCellList(table);

                return TableConvert.toList(table);
            }

            for (int i = 0; i < ths.size(); i++) {
                Element element = ths.get(i);
                sb.append(element.text() + ":" + (td1.size() > i ? td1.get(i).text() : ""));
                sb.append("|");
            }
            String str = sb.toString();
            if (str.equals("|")) {
                return null;
            }
            str = str.lastIndexOf("|") == str.length() - 1 ? str.substring(0, str.length() - 1) : str.toString();

            List<String> list = Arrays.asList(str.split("\\|"));
//			map = HtmlUtils.plistToMap(list);
//			data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());
            return list;
        }
        return null;
        //4.上下结构表头在上
/*		List<WinBisInfo> winBisInfos = HtmlUtils.parseRowTable(tableList);
		if (winBisInfos.size() > 1) {
			data.setWinBisInfoStr(JSON.toJSONString(winBisInfos));
		}
		return data;
*/
    }


    public static List<String> have2td(Element table) {
        //3.判断是否为左右结构.一行tr 内 两个td
        Elements trs = table.getElementsByTag("tr");
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

            //		map = HtmlUtils.plistToMap(list);
            //		data = (GovData) ReflectionUtils.mapToField(map, data, Contant.filedBJValueSet());
            return list;

        }
        return null;
    }


    public static boolean listIsContains(List<String> list, String str) {
        if (str == null) {
            return true;
        }
        for (String s : list) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

}
