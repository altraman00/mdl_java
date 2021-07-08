package com.mdl.excel.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mdl.excel.mysql.utils.MySQLConnectSNMP;
import com.mdl.txt_read_and_writer.Writer;

public class TakeFilePathAndName {

//	private static String filePath = "C:/Users/xie_kun/Desktop/units/";
	
	private static String filePath = "C:/Users/xie_kun/Desktop/snmp/";
	

	public static void main(String[] args) {
		try {
			getFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getFile(String path) throws IOException {
		File file = new File(path);
		File[] array = file.listFiles();
		CsvToJson cj = new CsvToJson();
		List<Object> list = new ArrayList<Object>();
		
		Writer w = new Writer("C:/Users/xie_kun/Desktop/txt/TestJson.txt");
		
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				if (!array[i].getName().startsWith("._")) {
					String fileName = array[i].getName().split("\\.")[0];
					System.out.println(fileName);
					String str = cj.getJSONFromFile(filePath + fileName + ".csv", "\\,");
					
					w.writing(str);
					w.newLine();
					
					JSONArray jobj = JSON.parseArray(str);

					for (Object o : jobj) {
						list.add(o);
					}
					
				}
			} else if (array[i].isDirectory()) {
				getFile(array[i].getPath());
			}
		}
		
		w.close();
		
//		MySQLConnectOther mc = new MySQLConnectOther();
		MySQLConnectSNMP mc = new MySQLConnectSNMP();
		mc.connectMysql(list);
		
		System.out.println("整个文件夹里面的数据有：" + list.size() + "条");
		
	}


}
