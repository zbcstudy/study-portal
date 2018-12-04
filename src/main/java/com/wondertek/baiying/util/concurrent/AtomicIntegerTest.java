package com.wondertek.baiying.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试原子操作类的作用
 * 原子操作类 eg:atomicInteger 适用于高并发情况下使用
 * 线程安全意味着牺牲一部分性能来实现
 * Created by wd on 2018/2/26.
 */
public class AtomicIntegerTest {
    public static int value = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0;i < 10000;i++) {
            executorService.execute(()->{
                for (int j = 0; j < 4; j++) {
                    System.out.println(value++);
                    /**
                     * 使用锁关键字同样能达到原子操作相同的效果
                     */
                    synchronized(AtomicIntegerTest.class){
                        System.out.println(value++);
                    }

                }
            });
        }

        executorService.shutdown();
        Thread.sleep(3000l);
        System.out.println("最终结果："+value);
    }

    @Test
    public void atomicTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0;i < 10000;i++) {
            executorService.execute(()->{
                for (int j = 0; j < 4; j++) {
                    System.out.println(atomicInteger.addAndGet(1));
                }
            });
        }

        executorService.shutdown();
        Thread.sleep(3000l);
        System.out.println("最终结果："+atomicInteger);
    }
}
