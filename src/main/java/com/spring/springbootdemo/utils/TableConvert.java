package com.spring.springbootdemo.utils;

import com.alibaba.fastjson.JSON;
import com.spring.springbootdemo.model.TableCell;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TableConvert {
	/**
	 * 将表格进行二维展开
	 *
	 * @param table
	 * @return
	 */
	public static Element[][] toTable(Element table) {
		if (!table.nodeName().equals("table")) {
			return null;
		}

		Elements tableRows = table.getElementsByTag("tr");
		int tableHeight = tableRows.size();

		//找 展开的最大列数，存在问题：如果某一列 全部使用 colspan 且其值都 大于2，有可能出错
		int tableWidth = 0;
		for (int tr_idx = 0; tr_idx < tableHeight; tr_idx++) {
			Elements tds = tableRows.get(tr_idx).select("td, th");
			int colspanNum = 0;//合并列
			for(Element td : tds){
				if(td.hasAttr("colspan")){
					colspanNum = Integer.valueOf(td.attr("colspan"));
				}
			}

			int td_size = tds.size() + colspanNum - 1;
			if (td_size > tableWidth)
				tableWidth = td_size;
		}

		System.out.println("tableHeight:" + tableHeight + ";tableWidth:" + tableWidth);

		if (tableHeight < 2 || tableWidth < 2)
			return null;

		//定义二维数组
		Element[][] result = new Element[tableHeight][tableWidth];

		//使用canreplace 来占位
		for (int i = 0; i < tableHeight; i++) {
			for (int j = 0; j < tableWidth; j++) {
				result[i][j] = new Element(Tag.valueOf("canreplace"), "");
			}
		}


		//出现不规范的 colspan 则会出现   实际列数 > tableWidth ，直接抛出异常
		try {
			for (int rowIndex = 0; rowIndex < tableHeight; rowIndex++) {
				Elements colCells = tableRows.get(rowIndex).select("td, th");

				System.out.println("row" + rowIndex + ":\n" + colCells);
				int pointIndex = 0;//列的索引
				for (int colIndex = 0; colIndex < colCells.size(); colIndex++) {
					Element currentCell = colCells.get(colIndex);
					//放到二维数组
					if (result[rowIndex][colIndex].tagName().equalsIgnoreCase("canreplace")) {
						result[rowIndex][colIndex] = currentCell;
						pointIndex = colIndex;
					} else {
						pointIndex = colIndex + 1;
						//查找可放置 一直找到一个可替换
						pointIndex = getPointIndex(tableWidth, result, rowIndex, pointIndex, currentCell);
					}


					// 检查 colspan
					int colspan = 1;
					if (currentCell.hasAttr("colspan")) {
						colspan = Integer.valueOf(currentCell.attr("colspan"));
						currentCell.removeAttr("colspan");
					}

					//复制表格内容
					if (colspan > 1) {
						for (int emptyColindex = 1; emptyColindex < colspan; emptyColindex++) {
							pointIndex++;
							pointIndex = getPointIndex(tableWidth, result, rowIndex, pointIndex, currentCell);
						}
					}

					// 检查rowspan
					int rowspan = 1;
					if (currentCell.hasAttr("rowspan")) {
						rowspan = Integer.valueOf(currentCell.attr("rowspan"));
						currentCell.removeAttr("rowspan");
					}

					if (rowspan > 1) {
						for (int i = 1; i < rowspan; i++) {
							if (i >= tableHeight) break; // ignore bad rowspans
							System.out.println("===rowIndex===" + pointIndex + "====tempColIndex===" + pointIndex + "===" + result[rowIndex][pointIndex].tagName());
							result[rowIndex + i][colIndex] = currentCell;//new Element(invalidTag, "");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	private static int getPointIndex(int tableWidth, Element[][] result, int rowIndex, int pointIndex, Element currentCell) throws Exception {
		while (pointIndex < tableWidth && !result[rowIndex][pointIndex].tagName().equalsIgnoreCase("canreplace") ) {
			pointIndex++;
			System.out.println("===rowIndex===" + rowIndex + "====tempColIndex===" + pointIndex + "===" + result[rowIndex][pointIndex].tagName());
		}
		if (pointIndex < tableWidth && result[rowIndex][pointIndex].tagName().equalsIgnoreCase("canreplace")) {
			result[rowIndex][pointIndex] = currentCell;
		} else {

			throw new Exception("table格式有错误！");
		}
		return pointIndex;
	}


	public static List<TableCell> printTable(Element[][] table) {
		if (table == null) return null;
		List<TableCell> cellList = new ArrayList<>();
		System.out.println("==================");
		for (int rowIndex = 0; rowIndex < table.length; rowIndex++) {
			System.out.print("|");
			for (int colIndex = 0; colIndex < table[rowIndex].length; colIndex++) {
				Element element = table[rowIndex][colIndex];

				TableCell cell = new TableCell();
				cell.setColIndex(rowIndex);
				cell.setRowIndex(colIndex);

				if (element == null) {
					cell.setText(null);
					System.out.print("  ");
				} else {
					cell.setText(element.text());
					System.out.print(element.text());
				}
				cellList.add(cell);
				System.out.print(" |");
			}
			System.out.println();
		}
		System.out.println("==================");
		System.out.println(JSON.toJSONString(cellList));

		return cellList;
	}


	public static List<TableCell> toCellList(Element table){

		Element[][] elements = toTable(table);
		return printTable(elements);
	}


}
