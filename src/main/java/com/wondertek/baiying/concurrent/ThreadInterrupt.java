package com.wondertek.baiying.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/7/9.
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("线程被中断!!");
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Test
    public void testThreadInterrupted() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true) {
                //do nothing
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupt ? %s\n",thread.isInterrupted());
        //中断线程
        thread.interrupt();
        System.out.printf("Thread is interrupt ? %s\n",thread.isInterrupted());
    }

    /**
     * 可中断方法捕获到了中断信号之后，为了不影响线程中其他方法的执行，
     * 将线程的interrupt标识复位
     */
    @Test
    public void testThreadInterrupt2() {
        Thread thread = new Thread(()->{
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf("I am be interrupted ? %s\n",Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread is interrupt ? %s\n",thread.isInterrupted());
        //中断线程
        thread.interrupt();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread is interrupt ? %s\n",thread.isInterrupted());
    }
}
