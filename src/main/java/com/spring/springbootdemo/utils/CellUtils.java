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


	public static TableCell getMaybeHeaderCell(TableCell cell, List<TableCell> list) {

		TableCell cell2 = getUpCellIsHeaderOnSameColIndex(cell, list);

	/*	if(cell2 == null){
			return getUpCellIsHeaderOnSameRowIndex(cell, list);
		}*/
		return cell2;
	}

	/**
	 * 获取当前单元格，同一列，向上最近为表头的单元格， 如果上个紧邻单元格内容与当前单元格内容相同，继续向上寻找
	 */
	public static TableCell getUpCellIsHeaderOnSameColIndex(TableCell cell, List<TableCell> list) {
		Integer colIndex = cell.getColIndex();
		Integer rowIndex = cell.getRowIndex();
		if (rowIndex < 1) {
			return null;
		}
		String cellText = cell.getText();
		if(cellText == null){
			cellText = "";
		}
		while (rowIndex > 0){
			rowIndex--;
			TableCell tableCell = getCell(rowIndex, colIndex, list);
			String text = tableCell.getText();
			if(text == null){
				text = cellText;
			}
            String regexStr = tableCell.getRegexStr();
			boolean flag = true;
			if(regexStr != null){
			    flag = RegExpUtil.regCheck(cell.getText(),regexStr);
            }

            if(!tableCell.getText().equals(cell.getText()) && tableCell.isHeader() && flag){
				return tableCell;
			}


		}
		// 默认返回当前列第一行
		return null;
	}

	/**
	 * 相同行 优先于相同列，即先判断同行前面最近一个单元格是否包含表头单元格
	 * @param cell
	 * @param list
	 * @return
	 */

	public static TableCell getUpCellIsHeaderOnSameRowIndex(TableCell cell, List<TableCell> list) {
		Integer colIndex = cell.getColIndex();
		Integer rowIndex = cell.getRowIndex();
		if (colIndex < 1) {
			return null;
		}
		String cellText = cell.getText();
		if(cellText == null){
			cellText = "";
		}
		while (colIndex > 0){
			colIndex--;
			TableCell tableCell = getCell(rowIndex, colIndex, list);
			String text = tableCell.getText();
			if(text == null){
				text = cellText;
			}

            String regexStr = tableCell.getRegexStr();
          //  if(!tableCell.getText().equals(cell.getText()) && tableCell.isHeader() && tableCell.getRegexStr() == null ? true : RegExpUtil.regCheck(text,tableCell.getRegexStr())){
            if(!tableCell.getText().equals(cell.getText()) && tableCell.isHeader()){
				return tableCell;
			}

		}

	//	return getCell(rowIndex,0,list);
		return null;
	}


	/**
     * 获取当前单元格，同一列，上一行单元格
     */
    public static TableCell getUpOneCellOnColIndex(TableCell cell, List<TableCell> list) {
        Integer colIndex = cell.getColIndex();
        Integer rowIndex = cell.getRowIndex();
        if (rowIndex < 1) {
			return null;
        }

        for (TableCell tableCell : list) {
			rowIndex--;
            //    getCell(tableCell.getRowIndex(),tableCell.getColIndex(),list)
            if (tableCell.getRowIndex().equals(rowIndex) && tableCell.getColIndex().equals(colIndex)) {
                return tableCell;
            }

        }

        return null;
    }



	/**
	 * 获取当前单元格，同一列，第一行单元格
	 */
	public static TableCell getFirstRowCellOnColIndex(TableCell cell, List<TableCell> list) {
		Integer colIndex = cell.getColIndex();
		Integer rowIndex = cell.getRowIndex();
		if (rowIndex < 1) {
			return null;
		}

		for (TableCell tableCell : list) {

			//    getCell(tableCell.getRowIndex(),tableCell.getColIndex(),list)
			if ("0".equals(tableCell.getRowIndex()) && tableCell.getColIndex().equals(colIndex)) {
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
            }else if(string.equals("地")){
                continue;
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
