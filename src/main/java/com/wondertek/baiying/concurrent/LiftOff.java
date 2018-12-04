package com.wondertek.baiying.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/5/24.
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;//default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff") + ")";
    }

    @Override
    public void run() {
        while (--countDown > 0) {
            System.out.println(status());
//            Thread.yield();

            try {
                Thread.sleep(1000l);
                TimeUnit.MILLISECONDS.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MainThread{
    public static void main(String[] args) {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
        System.out.println("主线程结束");
    }
}

class BaseThread{
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("主线程结束");
    }
}

class MoreBaseThread{
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("main thread off");
    }
}

