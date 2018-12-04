package com.wondertek.baiying.concurrent.threadPool;

        import java.util.concurrent.TimeUnit;

/**
 * 测试自定义线程池
 * Created by wd on 2018/6/28.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {

        //初始化线程数2,核心线程数4 最大线程数8 ，任务队列最多允许1000个任务
        ThreadPool threadPool = new BasicThreadPool(2, 8, 4, 1000);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //不断输出线程池信息
        for (;;) {
            System.out.println("getActiveCount: "+threadPool.getActiveSize());
            System.out.println("getQueueSize: " + threadPool.getQueueSize());
            System.out.println("getCoreSize: " + threadPool.getCoreSize());
            System.out.println("getMaxSize: " + threadPool.getMaxSize());
            System.out.println("====================================");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
