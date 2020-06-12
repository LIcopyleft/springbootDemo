package com.spring.springbootdemo.utils;

import com.mysql.cj.xdevapi.Table;
import com.spring.springbootdemo.model.DataContentWithBLOBs;
import com.spring.springbootdemo.model.TableCell;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tengchao.li
 * @description 表格处理逻辑, 表格数 1 大于1 两类
 * @date 2020/4/13
 */
public class TableDeal {
    private static final Logger logger = LoggerFactory.getLogger(TableDeal.class);

    public static List<String> tableSizeOverOne(List<Element> tableList) {
        List<String> resultList = new ArrayList<>();
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
            rowMaxIndex = CellUtils.getMaxIndexRowOrCol("row", tableCells);
            if (colMaxIndex == 1) {
                //判断 单个单元格内是否有多条信息
                //遍历单元格,单元格内包含多个: 并且冒号后
                logger.debug("2.表格类型判定为只有只有两列***********");
                cellInfoList = only2td(tableCells);
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                if (cellInfoList.size() > 0) {
                    resultList.addAll(cellInfoList);
                }
                continue;
            }

            if (rowMaxIndex == 1) {
                logger.debug("2.表格类型判定为只有两行***********");
                cellInfoList = TableConvert.tableToListStr(table);
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                if (cellInfoList.size() > 0) {
                    resultList.addAll(cellInfoList);
                }
                continue;
            }

            if (colMaxIndex == 3) {
                //判断 单个单元格内是否有多条信息
                //遍历单元格,单元格内包含多个: 并且冒号后
                logger.debug("2.表格类型判定为只有只有两列***********");
                cellInfoList = only4td(tableCells);
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                if (cellInfoList.size() > 0) {
                    resultList.addAll(cellInfoList);
                }
                continue;
            }

            //2.包含thead 和 th
            List<String> list = haveThead(table);
            if (list != null) {
                logger.debug("2.表格类型判定为  表头 thread th  型***********");
                logger.debug("3.表格内容抽取为字符串列表结束******************");
                resultList.addAll(list);
                continue;
            }

            logger.debug("2.非特殊类型表格  分析表头 开始***********");

            //分析单元格是否为表头(表头分析)
            int[] maxIndex = TableMarkHeader.markHeader(tableCells);
            logger.debug("2.1 分析表头 表头单元格判定结束***********");
            //领属关系分析模块
            TableMarkHeader.relationshipAnalysis(maxIndex, tableCells);
            logger.debug("2.2 分析表头和单元格领属关系结束***********");
            //抽取模块
            // System.err.println(JSON.toJSONString(tableCells));
            for (TableCell cell : tableCells) {
                String text = cell.getText();
                if (listIsContains(resultList, text)) {
                    continue;
                }
                if (cell.isHeader() && text != null && text.contains(":")) {
                    String pattern = "\\([^\\)]*\\)";
                    text = text.replace("（", "(").replace("）", ")").replaceAll(String.valueOf((char) 160), " ").trim();
                    text = text.replaceAll(pattern, "");
                    if (!text.contains(":")) {
                        continue;
                    }
                    resultList.add(text);
                } else if (!cell.isHeader() && text != null && text.contains(":") && !text.contains("20")) {
                    //   text.replace()
                    resultList.add(text);
                } else if (text.contains(":")) {
                    resultList.add(text);
                } else if (!cell.isHeader() && cell.getHeaderType() != null) {
                    resultList.add(cell.getHeaderType() + ":" + text);
                }
            }

            //如果最大为4行,重复拼接一次, 修复部分重要表格识别不到文字
            if (maxIndex[0] < 4 && maxIndex[0] > 0) {
                List list1 = TableConvert.tableToListStr(table);
                resultList.addAll(list1);
            }

        }


        logger.debug("3.表格内容抽取为字符串列表结束******************");
        return resultList;
    }

    private static List<String> only2td(List<TableCell> tableCells) {
        List<String> cellInfoList = new ArrayList<>();

        String str = "";
        int i = 0;
        for (TableCell cell : tableCells) {
            String text = cell.getText();
            if (text == null || StringUtils.isBlank(text)) {
                continue;
            }

            if (i % 2 == 0) {
                if (HtmlUtils.countString(text, ":") == 1 && StringUtils.endsWithIgnoreCase(text, ":")) {
                    str = text;
                } else {
                    str = text + ":";
                }
                i++;
                continue;
            }
            if (i % 2 == 1) {
                if (!text.contains(":")) {
                    cellInfoList.add(str + text);
                } else if (HtmlUtils.countString(text, ":") == 1) {
                    cellInfoList.add(text);
                } else if (HtmlUtils.countString(text, ":") > 1) {
                    String[] strings = CellUtils.splitCellInfo(text);
                    if (strings != null) {
                        for (String s : strings) {
                            cellInfoList.add(s);
                        }
                    }
                }
                i++;
                str = "";
            }
/*
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
            }*/
        }
        return cellInfoList;
    }


    /**
     * 偶数列 , 奇数为表头
     *
     * @param tableCells
     * @return
     */
    private static List<String> only4td(List<TableCell> tableCells) {
        List<String> cellInfoList = new ArrayList<>();

        String str = "";
        int i = 0;
        for (TableCell cell : tableCells) {
            String text = cell.getText();
            if (text == null || StringUtils.isBlank(text)) {
                i++;
                continue;
            }

            if (i % 2 == 0) {
                if (HtmlUtils.countString(text, ":") == 1 && StringUtils.endsWithIgnoreCase(text, ":")) {
                    str = text;
                } else {
                    str = text + ":";
                }
                i++;
                continue;
            }
            if (i % 2 == 1) {
                if (!text.contains(":")) {
                    /*if(str.equals(text+":")){
                        String s = tableCells.get(index + 1).getText();
                        cellInfoList.add(str + s);
                        continue;
                    }*/
                    cellInfoList.add(str + text);
                } else if (HtmlUtils.countString(text, ":") == 1 && StringUtils.endsWithIgnoreCase(text, ":")) {
                    cellInfoList.add(text);
                } else if (HtmlUtils.countString(text, ":") > 1) {
                    String[] strings = CellUtils.splitCellInfo(text);
                    if (strings != null) {
                        for (String s : strings) {
                            cellInfoList.add(s);
                        }
                    }
                }
                i++;
                str = "";
            }
/*
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
            }*/
        }

        List<String> list = new LinkedList<>();
        for (int j = 0; j < tableCells.size(); j = j + 4) {

            TableCell c1 = tableCells.get(j);
            TableCell c2 = tableCells.get(j + 1);
            TableCell c3 = tableCells.get(j + 2);
            TableCell c4 = tableCells.get(j + 3);

            if (c1.getText().equals(c2.getText()) && c3.getText().equals(c4.getText())) {
                if (HtmlUtils.countString(c3.getText(), ":") > 1) {
                    continue;
                  /*  String[] strings = CellUtils.splitCellInfo(c3.getText());
                    if (strings != null) {
                        for (String s : strings) {
                            cellInfoList.add(s);
                        }
                    }*/
                }

                list.add(c1.getText() + ":" + c3.getText());
            }


        }
        List<String> result = new LinkedList<>();


        cellInfoList.stream().forEach(item -> {
            if (item != null && item.contains(":")) {
                String[] split = item.split(":");
                if (split.length > 1 && !split[0].equals(split[1])) {
                    // cellInfoList.remove(item);
                    result.add(item);
                }

            }

        });


        result.addAll(list);
        return result;
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
