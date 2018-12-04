package com.wondertek.baiying.concurrent.threadPool;

/**
 * 任务队列 主要用来缓存提交到线程池中的任务
 * Created by wd on 2018/6/28.
 */
public interface RunnableQueue {

    /**
     * 当有新的任务进来时，首先会offer到队列中
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 从任务队列中获取任务
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中的任务数量
     * @return
     */
    int size();
}
