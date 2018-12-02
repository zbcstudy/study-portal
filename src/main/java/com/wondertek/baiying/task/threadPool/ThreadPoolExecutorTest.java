package com.wondertek.baiying.task.threadPool;

import java.util.concurrent.*;

/**
 * Created by wd on 2018/11/21.
 */
public class ThreadPoolExecutorTest{

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 1l, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        executor.execute(()->{
            System.out.println("线程池");
        });
    }
}
