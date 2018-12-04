package com.wondertek.baiying.util;

import java.io.*;

public class FileReadByLine {
	public static void read() {
		File file = new File("E:/test.txt");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		StringBuffer str = new StringBuffer("");
		try {
			fis = new FileInputStream(file);
			System.out.println(file.length());
			isr = new InputStreamReader(fis);
			bufferedReader = new BufferedReader(isr);
			String readLine = null;
			while ( (readLine = bufferedReader.readLine()) != null) {
				readLine = readLine.replaceAll("\t", "");
				str.append(readLine);

			}
			System.out.println(str.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void readLine(String readPath,String writePath) {

	}
}
