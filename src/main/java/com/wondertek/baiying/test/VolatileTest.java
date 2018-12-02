package com.wondertek.baiying.test;

import java.util.concurrent.TimeUnit;

/**
 * 内存可见性：
 *     默认情况下，线程之间的内存是独立的：当多个线程操作共享数据时，彼此不可见
 * volatile : 当多个线程操作共享数据时，可以保证内存中的数据可见
 *            实时的将不同的线程中的缓存数据刷新到主存中(可以理解为对共享数据的操作都在主存中进行)
 *            不具有互斥性
 *            不能保证变量的"原子性"(不可变性)
 *     相较于synchronize来说，是一种较为轻量的同步策略。
 * Created by wd on 2018/9/17.
 */
public class VolatileTest {

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();

        while (true) {
            if (demo.isFlag()) {
                System.out.println("======================");
                break;
            }
        }
    }

}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag is " + flag);
    }

    public boolean isFlag() {
        return flag;
    }
}