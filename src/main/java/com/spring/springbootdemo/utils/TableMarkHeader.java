package com.spring.springbootdemo.utils;

import com.spring.springbootdemo.contant.Contant;
import com.spring.springbootdemo.enums.WinBidNoticeEnum;
import com.spring.springbootdemo.model.TableCell;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author tengchao.li
 * @description
 * @date 2020/3/31
 */
public class TableMarkHeader {

 //   private static Set set = new LinkedHashSet();

    public static int[] markHeader(List<TableCell> tableCells) {
        int colMaxIndex = 0;
        int rowMaxIndex = 0;
        int[] maxIndex = new int[2];

        for (TableCell cell : tableCells) {

            if (cell.getColIndex() > colMaxIndex) {
                colMaxIndex = cell.getColIndex();
            }
            if (cell.getRowIndex() > rowMaxIndex) {
                rowMaxIndex = cell.getRowIndex();
            }

            if (StringUtils.isNotBlank(cell.getText())) {
                //   if (cell.getText().contains("名称") || cell.getText().contains("金额") || cell.getText().contains("单价") || cell.getText().contains("总价")) {

            }

            WinBidNoticeEnum type = WinBidNoticeEnum.getFiledType(cell.getText());
            if (type != null) {
                cell.setHeader(true);
                cell.setHeaderType(type.getHeaderType());
                cell.setHeaderClass(type.getHeaderClass());
            }
            //     Iterator iterator = set.iterator();

        }
        maxIndex[0] = rowMaxIndex;
        maxIndex[1] = colMaxIndex;
 //       FileUtils.writeAppendFile("feild.txt", set);

        return maxIndex;
    }

    /**
     * 领属关系分析模块
     *
     * @param maxIndex
     * @param tableCells
     */
    public static void relationshipAnalysis(int[] maxIndex, List<TableCell> tableCells) {
        int rowMaxIndex = maxIndex[0];
        int colMaxIndex = maxIndex[1];

        for (TableCell cell : tableCells) {
            if (!cell.isHeader()) {
                Integer rowIndex = cell.getRowIndex();//行索引
                Integer colIndex = cell.getColIndex();//列索引
                //如果第一列
                if(colIndex == 0){
                //第一列中第一行 (未匹配为表头,问题单元格,暂设置为无)
                    if(rowIndex == 0){
                       // cell.set
                    }
                    //第一列同一向上查找表头,向上一格或最顶格

                    //非表头单元格 领属表头判断优先级 1.左一格（左一格文本内容与当前内容相同，继续向前推）2.左边第一列 3.上一格，4.上方第一行

                    TableCell headerCell = CellUtils.getHeaderCell(cell, tableCells);


                }

                //colindex > 1 第二列为例,获取colIndex - 1 rowIndex 元素 cell,如果为表头单元格,优先判定为领属表头单元格.不是,则向上判断同列距离最近单元格表头为当前单元格的领属表头单元格


                // 第一行 最后一行: 一般第一行为表头,


                //如果第一列,
                if (rowIndex == 0) {

                }

                //第一列 最后一列


            }
        }


    }


}
