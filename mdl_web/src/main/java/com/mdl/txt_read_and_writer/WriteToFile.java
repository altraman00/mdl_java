package com.mdl.txt_read_and_writer;

import java.io.File;
import java.io.FileWriter;

/**
 * 
 * @author Vava 测试目的：测试向一个文件中写入换行 测试结果：测试成功
 */
public class WriteToFile {
	public static void main(String[] args) throws Exception {
		String Url = "C:/Users/xie_kun/Desktop/txt/Test.txt";
		String line = System.getProperty("line.separator"); // 在这个位置更换为自己想使用的换行符
		// File fout = new File("fout.txt"); // 创建文件输出对象
		File fout = new File(Url); // 创建文件输出对象
		FileWriter out = new FileWriter(fout); // 创建文件字符流 写 对象，传递文件对象
		out.write("你好某某某某某某某某123");
		out.write("\r\n");
		out.write("你好某某某某某某某某321");
		out.write(line);
		out.write("你好某某某某某某某某1234567");
		out.flush();
		out.close();
	}
}