package com.wondertek.baiying.util;

public class FileTest {
	
	public static void main(String[] args) {
		FileReadByByte fileReadByByte = new FileReadByByte();
		fileReadByByte.readByte();
		/**
		 * 单个字节读取
		 */
		//FileReadByLine.read();

		/**
		 * 一次读取多个字节
		 */

//		fileReadByByte.readResource("E:/writeTest.txt");


		//图片读写
		//fileReadByByte.readResource("E:/640.jpg","E:/Z-test/000.jpg");

		//测试csv文件的读取
		//fileReadByByte.readResource("E:/comment_20170420143043.csv","E:/Z-test/001.csv");
		/**
		 * 按行读取
		 */
		FileReadByLine.read();
		//FileReadByLine.readLine("E:/test.txt","E:/writeTest.txt");
	}
}


