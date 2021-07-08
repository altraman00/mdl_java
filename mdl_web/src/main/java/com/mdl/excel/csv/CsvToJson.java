package com.mdl.excel.csv;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

public class CsvToJson {

	public static String getJSONFromFile(String fileName, String separator) throws IOException {

		byte[] bytes = null;
		bytes = FileUtils.readFileToByteArray(new File(fileName));
		String csv = new String(bytes, "GBK");
		return getJSON(csv, separator).replace("\r", "");
	}

	public static String getJSON(String content, String separator) {
		StringBuilder sb = new StringBuilder("[\n");
		String csv = content;
		String csvValues[] = csv.split("\n");
		String header[] = csvValues[0].split(separator);
		
//		System.out.println(JSON.toJSON(header));
		
		for (int i = 1; i < csvValues.length; i++) {
			sb.append("\t").append("{").append("\n");
			String tmp[] = csvValues[i].split(separator);
			for (int j = 0; j < tmp.length; j++) {
				sb.append("\t").append("\t\"").append(header[j]).append("\":\"").append(tmp[j]).append("\"");
				
				if (j < tmp.length - 1) {
					sb.append(",\n");
				} else {
					sb.append("\n");
				}
			}
			if (i < csvValues.length - 1) {
				sb.append("\t},\n");
			} else {
				sb.append("\t}\n");
			}
		}

		sb.append("]");
		
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getJSONFromFile("C:/Users/xie_kun/Desktop/units/couchdb.csv", "\\,"));
//		System.out.println(getJSONFromFile("C:/Users/xie_kun/Desktop/snmp/couchdb.csv", "\\,"));
	}
}
