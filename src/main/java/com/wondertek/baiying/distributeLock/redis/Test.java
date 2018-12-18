package com.wondertek.baiying.distributeLock.redis;

/**
 * Created by wd on 2018/12/4.
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
