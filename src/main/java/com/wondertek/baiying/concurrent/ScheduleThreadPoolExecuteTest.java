package com.wondertek.baiying.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/7/26.
 */
public class ScheduleThreadPoolExecuteTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        System.out.println("主线程启用时间： "+System.currentTimeMillis());

        /**
         * 系统启动1s后,每隔5秒钟执行一次
         */
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis()+"  sss");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);

        executor.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis()+" ttt");
        }, 1000, 10000, TimeUnit.MILLISECONDS);
    }
}
