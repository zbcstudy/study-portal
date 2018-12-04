package com.wondertek.baiying.test;

/**
 * Created by wd on 2018/2/8.
 */
public class 对象类型 {
    public static void main(String[] args) {
        Integer i = 1;
        System.out.println(i.getClass());
        String s = "122#123#123";
        String[] split = s.split("#",2);
        for (String s1 : split) {
            System.out.println(s1);
        }
    }
}
