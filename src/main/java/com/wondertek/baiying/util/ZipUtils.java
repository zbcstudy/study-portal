package com.wondertek.baiying.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;

/**
 * Created by wd on 2017/9/14.
 * 压缩文件的操作
 */
public class ZipUtils {

    public static void main(String[] args) {
        //解压压缩文件

        try {
            //D:/test/impl.zip
            zipRealease("D:/test/gradle-3.4.1-bin.zip","D:/test/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压文件
     * @param zipFileName 压缩文件名
     * @param extPlace  解压地址
     * @return
     */
    public static Boolean zipRealease(String zipFileName,String extPlace) throws IOException {

        ZipFile zipFile = new ZipFile(zipFileName,"utf-8");
        //获取压缩包下的目录结构
        Enumeration<ZipEntry> entries = zipFile.getEntries();
        ZipEntry zipEntry = null;
        while (entries.hasMoreElements()){
            zipEntry = entries.nextElement();
            String entryName = zipEntry.getName();
            String[] names = entryName.split("/");
            for (String s : names) {
                System.out.println(s);
            }
            String path = extPlace;
            int length = names.length;
            for (int v = 0;v<length;v++){
                if (v < length - 1){
                    path += names[v] + "/";
                    System.out.println(path);
                    new File(path).mkdir();
                }else{ //最后一个
                    if (entryName.endsWith("/")){ //创建文件夹
                        new File(extPlace + entryName).mkdir();
                    }else {
                        InputStream is = zipFile.getInputStream(zipEntry);
                        OutputStream os = new FileOutputStream(extPlace + entryName);
                        //字节读写
                        byte[] bytes = new byte[1024 * 3];
                        int len;
                        while ((len = is.read(bytes)) > 0){
                            os.write(bytes,0,len);
                            //System.out.println(len);
                        }
                        os.close();
                        is.close();
                    }
                }
            }
            //System.out.println(zipEntry +"-----"+zipEntry.getName()+"-----"+zipEntry.getSize());
        }
        zipFile.close();
        return null;
    }
}
