package com.wondertek.baiying.java8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁的简单实现
 * Created by wd on 2019/3/5.
 */
public class DeadLock {

    private static Lock lock1 = new ReentrantLock();

    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println("thread1线程执行");
            synchronized (lock1){
                System.out.println("thread1 get lock1");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("thread1 get lock2");
                }
            }
            System.out.println("thread1线程执行完成");
        }).start();

        new Thread(()->{
            System.out.println("thread2线程执行");
            synchronized (lock2) {
                System.out.println("thread2 get lock2");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("thread2 get lock1");
                }
            }
            System.out.println("thread2 线程执行");
        }).start();
    }
}
