package com.wondertek.baiying.distributeLock.redis;

/**
 * Created by wd on 2018/12/4.
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
