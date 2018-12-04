package com.wondertek.baiying.util;

import java.io.*;
import java.net.URL;

/**
 *	try-catch-resource格式
 */
public class FileReadByByte{
	public void readByte() {
		File file = new File("E:\\test.txt");
//		InputStream is = null;
//		OutputStream os = null;
		try(InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(new File("E:/writeTest.txt"))){
			/*is = new FileInputStream(InputStream is);
			os = new FileOutputStream(new File("E:/writeTest.txt"));*/
			System.out.println("以字节为读取单位，一次读取一个字节");

			int temp ;
			while ((temp = is.read()) != -1){
				System.out.println(temp);
				os.write(temp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}/*finally {
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/

	}

	/**
	 * 一次读取多个字节
	 * @param writePath
     */
	public  void readResource(String readPath,String writePath){
		//读取resource文件夹下的文件
		//InputStream is = this.getClass().getResourceAsStream("/banner.txt");

		FileInputStream is = null;
		//获取文件的绝对路径
		URL url = this.getClass().getResource("/banner.txt");
		System.out.println(url);
		OutputStream os = null;
		try {
			is = new FileInputStream(new File(readPath));
			byte[] tempByte = new byte[1024];
			os = new FileOutputStream(writePath);
			int result;
			while ((result = is.read(tempByte)) != -1){
				System.out.write(tempByte,0,result);
				os.write(tempByte,0,result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
