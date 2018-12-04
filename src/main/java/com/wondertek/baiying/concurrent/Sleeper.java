package com.wondertek.baiying.concurrent;

import java.security.AlgorithmConstraints;

/**
 * Created by wd on 2018/5/24.
 */
public class Sleeper extends Thread {

    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        this.duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(getName() + "was interrupted;" + "isinterrupted:"+isInterrupted());
        }

        System.out.println(getName() + "has awakened");
    }
}

class Joiner extends Thread{

    private Sleeper sleeper;

    public Joiner(String name,Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        this.start();
    }

    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interrupted");
        }
        System.out.println(getName() + " join completed");
    }

    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("sleepy", 2000);
        Sleeper grumpy = new Sleeper("grumpy", 2000);

        Joiner joiner1 = new Joiner("joiner1", sleepy);
        Joiner joiner2 = new Joiner("joiner2", grumpy);

        grumpy.interrupt();
    }
}
