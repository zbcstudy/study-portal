package com.wondertek.baiying.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/7/31.
 */
public class ThreadShutdownTest {


    public static void main(String[] args) {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "countThread");
        countThread.start();
        //线程休眠一秒
        try {
            TimeUnit.MILLISECONDS.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "countThread");
        countThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        two.concel();
    }

    /**
     * 定义一个线程
     */
    public static class Runner implements Runnable {
        private long i = 0;
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("count i: " + i);
        }

        public void concel() {
            this.running = false;
        }

    }

}
