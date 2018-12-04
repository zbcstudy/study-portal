package com.wondertek.baiying.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程异常捕获时，普通的try-catch无法捕获
 * Created by wd on 2018/5/24.
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception has been handler");
        }

    }
}
