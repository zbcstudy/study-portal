package com.wondertek.baiying.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/5/24.
 */
public class SimpleDaemons implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(1000l);
                System.out.println(Thread.currentThread()+" "+this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            try {
                Thread thread = new Thread(new SimpleDaemons());
                thread.setDaemon(true);
                thread.start();
                TimeUnit.MILLISECONDS.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("all daemon thread start");

    }
}

class DaemonThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
