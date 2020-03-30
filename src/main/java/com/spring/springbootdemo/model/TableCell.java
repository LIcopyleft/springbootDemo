package com.spring.springbootdemo.model;

import lombok.Data;


/**
 * 名词解释:领属表头单元格,即为表头类型的单元格
 *
 * 1.根据预定义树分析哪些单元格为表头
 *
 * 2.判断表头类别,由小类,可知大类
 *
 * 3.根据位置判断获取非表头单元格的领属表头单元格
 *
 * 4.
 *
 * 招标公示:
 *
 *
 *
 * 根据非表头单元格与领属表头单元格的对应关系，以及对应的表头类 别的输出规则，
 * 抽取并输出表头单元格和非表头单元格中的内容
 *
 *
 * 非表头单元格和表头单元格中的内容要根据它们的领属关系成对输出，
 * 在输出时，根据表头单元格对应的表头类别的输出规则，确定具体输出那些内容，
 * 以及，以什么样的格式输出这些内容。
 */
@Data
public class TableCell {

	//行索引
	private Integer rowIndex;
	//列索引
	private Integer colIndex;

	//是否表头
	private boolean isHeader;
	//表头类别(小类) 例如: 中标人名称
	private Integer headerType;
	private String headerTypeName;


	// 表头类别(大类) 例如:中标人信息
	private Integer headerClass;
	//单元格内容
	private String text;

	//非表头单元格的领属单元

}
