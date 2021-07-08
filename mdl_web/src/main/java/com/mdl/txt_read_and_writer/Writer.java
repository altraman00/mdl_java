package com.mdl.txt_read_and_writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {

	BufferedWriter bw ;
	
	public Writer(String pathname) {
		File statText = new File(pathname);
		out(statText);
	}
	
	private void out(File statText){
		FileOutputStream is = null;
		try {
			is = new FileOutputStream(statText);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(is);
		bw = new BufferedWriter(osw);
	}
	
	public void close() throws IOException{
		bw.close();
	}
	
	public void newLine() throws IOException{
		bw.newLine();
	}

	public void writing(String str) throws IOException {
		bw.write(str);
	}

	public static void main(String[] args) throws IOException {
		Writer w = new Writer("C:/Users/xie_kun/Desktop/txt/Test.txt");
		w.writing("123456");
		w.newLine();
		w.writing("portyuio");
		w.newLine();
		w.writing("lkklkk");
		w.close();
	}
}