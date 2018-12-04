package com.wondertek.baiying.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wd on 2018/10/26.
 */
public class CountDownLatchTest {

    private static CountDownLatch cdl = new CountDownLatch(1);

    Integer stock = 8; //线程可以获取的数量

    public void reduce(int num) {
        int a = 0;
        if (stock - num >= 0) {
            //为了更好的模拟线程并发,使线程在此处等待
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock -= num;
            System.out.println(Thread.currentThread().getName() + " 成功卖出" + num + "张,库存剩余：" + stock + "张");
        }else {
            System.out.println("失败");
        }
    }

    public void reduceByLock(int num) {
        boolean flag = false;
        synchronized (stock) {
            if (stock - num >= 0) {
                //为了更好的模拟线程并发,使线程在此处等待
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stock -= num;
                flag = true;

            }
        }
        if (flag) {
            System.out.println(Thread.currentThread().getName() + " 成功卖出" + num + "张,库存剩余：" + stock + "张");
        }else {
            System.out.println(Thread.currentThread().getName() + " 失败");
        }
    }

    public static void main(String[] args) {
        CountDownLatchTest cdlt = new CountDownLatchTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> cdlt.reduceByLock(1)).start();
        }
        cdlt.cdl.countDown();
    }
}
