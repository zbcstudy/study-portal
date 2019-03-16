package com.wondertek.baiying.java8.stream;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * IntStream流式计算
 * Created by wd on 2019/3/6.
 */
public class Stream03 {

    //求偶数
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        //利用IntStream进行计算
        System.out.println("--------------------");
        IntStream.range(0,10).forEach((i)->{
            if (i % 2 == 1) {
                System.out.println(i);
            }
        });

        System.out.println("********************");

        IntStream.range(0, 10).filter(i -> i % 2 == 0).forEach(System.out::println);

        //求和
        OptionalInt reduce = IntStream.range(0, 10).reduce((a, b) -> a + b);
        System.out.println("reduce: " + reduce.getAsInt());

        int reduce1 = IntStream.range(0, 10).reduce(7, (a, b) -> a + b);
        System.out.println("reduce1: " + reduce1);
    }


}
