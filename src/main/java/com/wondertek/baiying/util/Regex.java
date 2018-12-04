package com.wondertek.baiying.util;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wd on 2017/10/18.
 * 利用正则表达式获取字符串中的值
 */
public class Regex {
    public static void main(String[] args) {

        String inputString = "dvupgvuqv1212412rdasfa5qfvsd2555";
        Matcher matcher = Pattern.compile("\\d+").matcher(inputString);
        System.out.println(matcher);
        /*try {
            Thread.currentThread().sleep(5000); //当前程序暂停5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        Properties properties = System.getProperties();
        properties.list(System.out);
        System.out.println(properties.propertyNames().hasMoreElements()?properties.propertyNames().nextElement():null);
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory = "
                + rt.totalMemory()
                + " Free Memory = "
                + rt.freeMemory());
        while (matcher.find()){
            String group = matcher.group();
            System.out.println(Integer.parseInt(group));
        }

    }
}
