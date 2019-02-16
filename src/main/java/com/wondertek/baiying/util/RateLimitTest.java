package com.wondertek.baiying.util;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;
import com.sun.jmx.snmp.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

/**
 * 令牌桶算法基本实现测试
 * 使用guava中实现好的
 * Created by wd on 2019/1/4.
 */
public class RateLimitTest {

    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

        //指定每秒放一个令牌
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0; i < 50; i++) {
            //请求Ratelimiter,超过permits会被阻塞
            //acquire(int permits)函数主要用于获取permits个令牌，并计算需要等待多长时间，进而挂起等待，并将该值返回
            Double acquire = null;
            if (i == 1) {
                acquire = rateLimiter.acquire(1);
            } else if (i == 2) {
                acquire = rateLimiter.acquire(10);
            } else if (i == 3) {
                acquire = rateLimiter.acquire(2);
            } else if (i == 4) {
                acquire = rateLimiter.acquire(20);
            }else {
                acquire = rateLimiter.acquire(2);
            }
            executorService.submit(new Task("获取令牌成功，获取耗:" + acquire + " 第" + i + "个任务执行"));
        }
    }

    static class Task implements Runnable {
        String str;

        public Task(String str) {
            this.str = str;
        }

        @Override
        public void run() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateFormat.format(new Date()) + "|" + Thread.currentThread().getName() + str);

        }
    }
}
