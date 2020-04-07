package com.spring.springbootdemo;

import com.spring.springbootdemo.utils.TableConvert;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Test {


	public static void main(String[] args) throws IOException {

		getSimilarity("主要中标、成交标的名称","成交标的名称");
	//	strFamate();

		/*
		String url = "1.html";
		InputStream in;
		TableConvert tableConvert = new TableConvert();
		//	Connection connect = Jsoup.connect(url);
		//	Document doc = connect.get();
		try {
			in = new FileInputStream(url);
			Document doc = Jsoup.parse(in, null, "");

			Elements tables = doc.getElementsByTag("table");
			for (Element aTable : tables) {
				Elements table = aTable.select("table");
				if (table.size() > 1) {
					Elements children = aTable.children();
					Elements table1 = children.select("table").remove();
					System.out.println(aTable.select("table").size());

				}

				Elements subtables = aTable.getElementsByTag("table");
				subtables.remove(aTable);
				if (subtables.size() == 0) {
					System.out.println("converting table...");
					Element[][] result = tableConvert.toTable(aTable);


					if (null != result) {
						{
							tableConvert.printTable(result);
						}
					} else {
						System.out.println("Could not convert table.");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} */
	}


	public static void strFamate(String str) {
	//	String str = "中标单位:青海经纬水利水电设计有限公司 成交金额:￥ 488900.00元 设计周期:60天 采购代理机构:青海仁捷工程项目管理有限公司 联系人:索女士 联系电话:0971-2230320 联系地址:湟中县鲁沙尔镇北环路1号（鲁沙尔文化旅游产业园双创基地）六楼";

		String all = str.replaceAll("\\s", "#@#");
		all = all.replaceAll("：", ":");
		String[] split = all.split("#@#");
		StringBuilder sb = new StringBuilder();
		for (String string : split) {
			sb.append(string);
			if (string.contains(":")) {
				sb.append("#@#");
			}else{
				int start = sb.lastIndexOf("#@#");
				sb.delete(start, sb.length());
				sb.append(string+"#@#");
			}
		}
		split = sb.toString().split("#@#");

	}





	private static double getSimilarity(String doc1, String doc2) {
		if (StringUtils.isBlank(doc1) || StringUtils.isBlank(doc2)) {
			return 0L;
		}
		Map<Character,int[]> algMap=new HashMap<>();
		for (int i = 0; i<doc1.length(); i++) {
			char d1 = doc1.charAt(i);
			int[] fq = algMap.get(d1);
			if (fq != null && fq.length == 2) {
				fq[0]++;
			} else {
				fq = new int[2];
				fq[0] = 1;
				fq[1] = 0;
				algMap.put(d1, fq);
			}
		}
		for (int i = 0; i<doc2.length(); i++) {
			char d2 = doc2.charAt(i);
			int[] fq = algMap.get(d2);
			if (fq != null && fq.length == 2) {
				fq[1]++;
			} else {
				fq = new int[2];
				fq[0] = 0;
				fq[1] = 1;
				algMap.put(d2, fq);
			}
		}
		double sqdoc1 = 0;
		double sqdoc2 = 0;
		double denuminator = 0;
		for (Map.Entry entry : algMap.entrySet()) {
			int[] c = (int[]) entry.getValue();
			denuminator += c[0] * c[1];
			sqdoc1 += c[0] * c[0];
			sqdoc2 += c[1] * c[1];
		}
		double v = denuminator / Math.sqrt(sqdoc1 * sqdoc2);
		System.out.println(v);
		return v;
	}

}


