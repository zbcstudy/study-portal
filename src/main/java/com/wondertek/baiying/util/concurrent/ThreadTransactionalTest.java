package com.wondertek.baiying.util.concurrent;

/**
 * 使用多线程实现一个功能
 *  子线程循环10次，主线程循环100次，如此循环50次
 * 实现思路：专门的类实现专门的工作
 * Created by wd on 2018/9/12.
 */
public class ThreadTransactionalTest {

    public static void main(String[] args) {
        Business business = new Business();
        new Thread(()->{
                for (int i = 0; i < 50; i++) {
                    business.sub(i);
                }
            }).start();

        for (int i = 0; i < 50; i++) {
            business.main(i);
        }
    }
}

/**
 * 内部类专门用来实现线程的循环
 */
class Business{

    private boolean bShouldSub = true;
    //子循环
    public synchronized void sub(int num) {
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("sub running + " + i + " at loop " + num);
        }
        bShouldSub = false;
        this.notify();

    }

    public synchronized void main(int num){
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("main running + " + i + " at loop " + num);
        }
        bShouldSub = true;
        this.notify();
    }
}
