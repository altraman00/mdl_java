package com.mdl.excel.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReadCSV {

	public static void main(String[] args) {
		try {
			File csv = new File("E:/my document/poi-excel/excle_file/mongodb.csv"); // CSV文件

			//直接使用BufferedReader读取数据会出现乱码
			// BufferedReader br = new BufferedReader(new FileReader(csv));

			
			//通过InputStreamReader解决乱码问题
			InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), "GBK");
			BufferedReader br = new BufferedReader(isr);

			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				// String lines = new String(line.getBytes(),"GB2312");

				// 把一行数据分割成多个字段
				StringTokenizer st = new StringTokenizer(line, ",");

				while (st.hasMoreTokens()) {
					// 每一行的多个字段用TAB隔开表示
					System.out.print(st.nextToken() + "###");
				}
				System.out.println();
			}
			br.close();

		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace();
		}
	}

}
