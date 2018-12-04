package com.wondertek.baiying.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/7/30.
 */
public class FutureTaskTest {
    /**
     * 第一种方式:Future + ExecutorService
     * Task task = new Task();
     * ExecutorService service = Executors.newCachedThreadPool();
     * Future<Integer> future = service.submit(task1);
     * service.shutdown();
     */



    /**
     * 第二种方式: FutureTask + ExecutorService
     * ExecutorService executor = Executors.newCachedThreadPool();
     * Task task = new Task();
     * FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
     * executor.submit(futureTask);
     * executor.shutdown();
     */

    /**
     * 第三种方式:FutureTask + Thread
     */
    public static void main(String[] args) {
        //新建FutureTask对象，
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new task());
        //新建Thread对象并启用
        Thread thread = new Thread(futureTask);
        thread.setName(" task thread");
        //线程启动
        thread.start();

        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running!");

        //判断任务是否结束
        if (!futureTask.isDone()) {
            System.out.println("task is not done");
            try {
                TimeUnit.MILLISECONDS.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }


    /**
     * 继承Callable接口 实现call方法，
     * 泛型类型为返回值类型
     */
    static class task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running!");
            Integer result = 0;
            for (int i = 0; i < 100; i++) {
                result += i;
            }
            TimeUnit.MILLISECONDS.sleep(3000l);
            return result;
        }
    }
}
