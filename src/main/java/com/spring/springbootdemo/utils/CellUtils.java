package com.spring.springbootdemo.utils;

import com.spring.springbootdemo.model.TableCell;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author tengchao.li
 * @description
 * @date 2020/4/7
 */
public class CellUtils {


    /**
     * 获取当前单元格，同一列，上一行单元格
     */
    public static TableCell getUpOneCellOnColIndex(TableCell cell, List<TableCell> list) {
        Integer colIndex = cell.getColIndex();
        Integer rowIndex = cell.getRowIndex();
        if (rowIndex < 1) {

        }

        for (TableCell tableCell : list) {

            //    getCell(tableCell.getRowIndex(),tableCell.getColIndex(),list)
            if (tableCell.getRowIndex().equals(rowIndex) && tableCell.getColIndex().equals(colIndex)) {
                return tableCell;
            }

        }

        return null;
    }

    /**
     * 对表格第一行特殊处理.
     * 获取第一行左边最近表头单元格
     *
     * @param cell
     * @param list
     * @return
     */
    public static TableCell getHeaderCell(TableCell cell, List<TableCell> list) {
        Integer colIndex = cell.getColIndex();
        Integer rowIndex = cell.getRowIndex();

        TableCell upOneCell = null;

        for (int index = colIndex; index > 0; index--) {
            TableCell tableCell = getCell(rowIndex, index, list);
            String text = cell.getText();
            if (StringUtils.isNotBlank(text)) {
                text = "";
            }
            if (text.equals(StringUtils.isBlank(tableCell.getText()) ? "" : tableCell.getText())) {
                continue;
            }

            if (tableCell.isHeader()) {
                upOneCell = tableCell;
                break;
            }

        }

        for (int index = rowIndex; index > 0; index--) {
            TableCell tableCell = getCell(index, colIndex, list);
            String text = cell.getText();
            if (StringUtils.isNotBlank(text)) {
                text = "";
            }
            if (text.equals(tableCell.getText() == null ? "" : tableCell.getText())) {
                continue;
            }

            if (tableCell.isHeader()) {
                upOneCell = tableCell;
                break;
            }

        }

        /*
        //第一行 非第一列
        if (rowIndex == 0 && colIndex > 0) {
            while (true) {
                colIndex--;
                TableCell c1 = getCell(rowIndex, colIndex, list);
                if (!cell.getText().equals(c1.getText()) || colIndex < 1) {
                    upOneCell = c1;
                    break;
                }
            }
        //第一列 非第一行
        }else if(rowIndex > 0 && colIndex == 0){

        }
*/
        if (upOneCell != null && upOneCell.isHeader()) {
            ifHeader(cell, upOneCell);
        }

        return upOneCell;
    }

    private static void firstRowFirstCol(TableCell cell, List<TableCell> list) {
        Integer rowIndex = cell.getRowIndex();
        Integer colIndex = cell.getColIndex();

        if (rowIndex == 0 || colIndex == 0) {
            if (colIndex == 0) {
                TableCell maybeHeaderCell = getCell(0, 0, list);

            }
        }


    }

    private static void ifHeader(TableCell cell, TableCell maybeHeaderCell) {
        if (maybeHeaderCell.isHeader()) {
            cell.setHeaderClass(maybeHeaderCell.getHeaderClass());
            cell.setHeaderType(maybeHeaderCell.getHeaderType());
        }
    }


    public static TableCell getCell(Integer rowIndex, Integer colIndex, List<TableCell> list) {

        for (TableCell cell : list) {
            if (cell.getColIndex().equals(colIndex) && cell.getRowIndex().equals(rowIndex)) {
                return cell;
            }
        }
        return null;
    }


    public static Integer getMaxIndexRowOrCol(String rowOrCol, List<TableCell> list) {

        Integer colMaxIndex = 0;
        Integer rowMaxIndex = 0;

        for (TableCell cell : list) {

            if (cell.getColIndex() > colMaxIndex) {
                colMaxIndex = cell.getColIndex();
            }
            if (cell.getRowIndex() > rowMaxIndex) {
                rowMaxIndex = cell.getRowIndex();
            }


        }

      if("row".equalsIgnoreCase(rowOrCol)){
          return rowMaxIndex;
      }
      return colMaxIndex;
    }


    public static String[] splitCellInfo(String str){
     //   String all = str.trim().replaceAll("\\s", "#@#");
       String all = str.replaceAll("\\s|\\u00A0","#@#");
        all = all.replaceAll("：", ":");
        String[] split = all.split("#@#");
        StringBuilder sb = new StringBuilder();
        for (String string : split) {
            if(StringUtils.isBlank(string)){
                continue;
            }
            sb.append(string);
            if (string.contains(":")) {
                sb.append("#@#");
            }else{
                int start = sb.lastIndexOf("#@#");
                try {

                sb.delete(start, sb.length());
                }catch (Exception e){
                    return null;
                }
                sb.append(string+"#@#");
            }
        }
        split = sb.toString().split("#@#");

        return split;
    }
}
