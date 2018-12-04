package com.wondertek.baiying.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wd on 2018/5/24.
 */
public class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("run() by " + thread);
        System.out.println("th：" + thread.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught: " + e+" 未捕获的线程："+t);
    }
}

class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " create new thread");
        Thread th = new Thread(r);
        System.out.println(th + "created");
        th.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        System.out.println("th: " + th.getUncaughtExceptionHandler());

        return th;
    }
}

class CaptureUncaughtException{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        System.out.println("执行另一个线程");
        executorService.execute(new ExceptionThread2());
    }
}
