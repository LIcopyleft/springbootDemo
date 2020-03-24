package com.spring.springbootdemo;

import com.spring.springbootdemo.utils.TableConvert;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Test {


	public static void main(String[] args) throws IOException {
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
				Elements subtables = aTable.getElementsByTag("table");
				subtables.remove(aTable);
				if (subtables.size() == 0) {
					System.out.println("converting table...");
					Element[][] result = tableConvert.toTable(aTable);
					if (null != result)
						tableConvert.printTable(result);
					else
						System.out.println("Could not convert table.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


