package com.wondertek.baiying.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wd on 2019/3/6.
 */
public class Stream04 {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("d2", "a2", "b1", "b3", "c");

//        test01(stringList);
//        test02(stringList);
        test03(stringList);
    }

    private static void test03(List<String> stringList) {
        stringList.stream().filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("b");
        }).map(s1 -> {
            System.out.println("map: " + s1);
            return s1.toUpperCase();
        }).forEach(s2 -> {
            System.out.println("forEach:" + s2);

        });

    }

    private static void test02(List<String> stringList) {
        stringList.stream().map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        }).filter(s1 -> s1.startsWith("B")).forEach(s2 -> System.out.println("forEach: " + s2));

    }

    private static void test01(List<String> stringList) {

        stringList.stream().filter(s -> {
            System.out.println("filter:" + s);
            return true;
        }).forEach(s1 -> System.out.println("forEach: " + s1));
    }


}
