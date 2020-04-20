package com.spring.springbootdemo.utils;

import com.spring.springbootdemo.model.GovData;
import com.spring.springbootdemo.model.TableCell;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @author tengchao.li
 * @description 表格处理逻辑, 表格数 1 大于1 两类
 * @date 2020/4/13
 */
public class TableDeal {

    public static void tableSizeOverOne(GovData data, List<Element> tableList, List<String> cellInfoList) {

        //    logger.debug(data.getUrl());
            for (Element table : tableList) {
                int colMaxIndex = 0;
                int rowMaxIndex = 0;
                //   Document table = Jsoup.parse(tableStr);
                //    Elements table1 = table.getElementsByTag("table");
                List<TableCell> tableCells = TableConvert.toCellList(table);
                if (tableCells == null || tableCells.size() < 1) {
                    continue;
                }
                //如果只有两列,特殊处理,按 P 型处理
                colMaxIndex = CellUtils.getMaxIndexRowOrCol("col", tableCells);
                if(colMaxIndex == 1){
                    //判断 单个单元格内是否有多条信息

                    //遍历单元格,单元格内包含多个: 并且冒号后
                    for(TableCell cell : tableCells){
                        String text = cell.getText();
                        if(text == null || StringUtils.isBlank(text)){
                            continue;
                        }
                        if(HtmlUtils.countString(text,":") == 1){
                            if(text.split(":").length < 2 || StringUtils.isBlank(text.split(":")[1])){
                                continue;
                            }
                        }else if(HtmlUtils.countString(text,":") > 1){
                            String[] strings = CellUtils.splitCellInfo(text);
                            if(strings != null){

                            for(String str : strings){
                                cellInfoList.add(str);
                            }
                            }

                        }

                    }

                }


                //分析单元格是否为表头(表头分析)
                int[] maxIndex = TableMarkHeader.markHeader(tableCells);

                //领属关系分析模块
                TableMarkHeader.relationshipAnalysis(maxIndex, tableCells);


                //抽取模块

                //  System.err.println(JSON.toJSONString(tableCells));
            }



        }

}
