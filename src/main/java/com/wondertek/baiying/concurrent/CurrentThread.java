package com.wondertek.baiying.concurrent;

/**
 * Created by wd on 2018/7/9.
 */
public class CurrentThread {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread());
        });

        thread.start();
        System.out.println(Thread.currentThread());
        String name = Thread.currentThread().getName();
        System.out.println(name.equals("main"));
    }
}
