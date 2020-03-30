package com.spring.springbootdemo;

import com.spring.springbootdemo.model.TableCell;

import java.util.ArrayList;
import java.util.List;


public class TablePraseDRC {

	/**
	 * table表格解析专利，表格解析代码
	 */

	/**
	 * 1.  解析模块: 解析 html
	 *
	 * 2. 表格处理模块:根据表格代码中单元格跨行跨列属性值分析是否包含
	 * 	跨行 列 等合并的单元格,讲合并的单元格分解为多个单元格
	 *
	 * 	3.表头分析模块: 对每个单元格进行规则分析,从而获取表头单元格和对应的表头类别
	 *
	 * 	4.领属关系分析模块: 用于根据表格中的非表头单元格相对于表头单元格的位置关系,
	 * 	确定非表头单元格的领属表头单元格
	 *
	 * 	5.抽取模块:用于根据非表头单元格与领属表头单元格的对应关系,以及对应的表头类别
	 * 		的输出规则,抽取并输出表头单元格和非表头单元格中的内容
	 *
	 *
	 */


private void test() {


	//表头分析模块

	List<TableCell> list = new ArrayList<>();

	int rowIndexMax = 0;
	int colIndexMax = 0;
	for(int cellIndex = 0 ; cellIndex < list.size() ; cellIndex++){
		TableCell cell = list.get(cellIndex);
		if(cell.getColIndex() > colIndexMax){
			colIndexMax = cell.getColIndex();
		}
		if(cell.getRowIndex() > rowIndexMax){
			rowIndexMax = cell.getRowIndex();
		}

		//判断逻辑:判断单元格是否为表头
		//TODO 表头分析模块
		if(cell.getText().contains(" ")){
			cell.setHeader(true);
			cell.setHeaderType(null);
			cell.setHeaderClass( null);
			cell.setHeaderTypeName(null);
		}else {
			cell.setHeader(false);
			//遍历查找 非表头单元格的领属表头单元格

			//领属关系分析模块
			//TODO
			cell.setHeaderClass(null);
			cell.setHeaderClass(null);

		}




	}


}




	//cell.setLayoutX();

	//CellUtil.setCellValue(cell,1,1,true);




}
