package com.wondertek.baiying.concurrent;

/**
 * Created by wd on 2018/5/29.
 */
public class MultiLock {

    public synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("f1() call f2() with count: " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("f2() call f1() with count: " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        new Thread(){
            public void run() {
               new MultiLock().f1(10);
            }
        }.start();
        //中断线程
//        Thread.currentThread().interrupt();
    }
}
