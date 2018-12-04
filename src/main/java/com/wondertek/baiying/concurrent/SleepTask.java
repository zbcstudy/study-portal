package com.wondertek.baiying.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 线程休眠时间是随机的
 * Created by wd on 2018/5/24.
 */
public class SleepTask implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int j = getRandom();
            System.out.println(Thread.currentThread()+" 线程休眠时间为："+j+"s");
            try {
                TimeUnit.MILLISECONDS.sleep(j*1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRandom(){
        Random random = new Random();
        return random.nextInt(10);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new SleepTask());
        thread.start();
    }
}
