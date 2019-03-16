package com.wondertek.baiying.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * java8对流的处理
 * Created by wd on 2019/3/5.
 */
public class Stream01 {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("ddd2");
        lists.add("aaa2");
        lists.add("bbb1");
        lists.add("aaa1");
        lists.add("bbb3");
        lists.add("ccc");
        lists.add("bbb2");
        lists.add("ddd1");

        //filte功能
        lists.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println("------------------------");

        //sort功能
        lists.stream()
                .filter(s -> s.startsWith("b"))
                .sorted()
                .forEach(System.out::println);

        System.out.println("************************");
        //mapping功能同时倒序
        lists.stream()
                .map(s -> s.toUpperCase())
                .sorted((a,b)->b.compareTo(a))
                .forEach(System.out::println);

        System.out.println("-------------------------");
        //match功能
        boolean match = lists.stream()
                .anyMatch(s -> s.startsWith("a"));
        //list中的数据是否全部匹配
        boolean allMatch = lists.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(match+"==="+allMatch);

        //list中的数据是否全部不匹配
        boolean nonMatch = lists.stream().noneMatch(s -> s.startsWith("e"));
        System.out.println("list中的数据是否全部不匹配：" + nonMatch);

        //counting功能
        long startWithB = lists.stream().filter(s -> s.startsWith("b"))
                .count();
        System.out.println("list中以b开头的数据的数量：" + startWithB);

        Optional<String> reduce = lists.stream().sorted().reduce((a, b) -> a + "#" + b);
        System.out.println(reduce.get());
    }
}
