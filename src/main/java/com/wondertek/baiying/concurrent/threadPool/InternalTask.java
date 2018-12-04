package com.wondertek.baiying.concurrent.threadPool;

/**
 * Runnable接口的一个实现
 * 用于从RunnableQueue不断地取出runnable 并执行run()
 * Created by wd on 2018/6/28.
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        /**
         * 如果当前任务没有被中断，则不停地从队列中获取任务
         */
        while (running && !Thread.currentThread().isInterrupted()) {

            try {
                Runnable take = runnableQueue.take();
                take.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务
     */
    public void stop() {
        this.running = false;
    }
}
