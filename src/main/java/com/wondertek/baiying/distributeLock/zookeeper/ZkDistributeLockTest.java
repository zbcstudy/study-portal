package com.wondertek.baiying.distributeLock.zookeeper;

/**
 * Created by wd on 2018/12/4.
 */
public class ZkDistributeLockTest {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            ZkDistributeLock lock = null;
            try {
                lock = new ZkDistributeLock("127.0.0.1:2181", "test1");
                lock.lock();
                secskill();
                System.out.println(Thread.currentThread().getName() + "正在运行");
            }finally {
                if (lock != null) {
                    lock.unlock();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
