package com.wondertek.baiying.concurrent.threadPool;

/**
 * 定义一个线程池应该具备的基本操作和方法
 * Created by wd on 2018/6/28.
 */
public interface ThreadPool {

    /**
     * 提交任务到线程池
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutDown();

    /**
     * 获取线程池的初始化大小
     * @return
     */
    int getInitSize();

    /**
     * 获取线程池最大的线程数
     * @return
     */
    int getMaxSize();

    /**
     * 获取线程池的核心线程数
     * @return
     */
    int getCoreSize();

    /**
     * 获取线程池中用于缓存任务队列的大小
     * @return
     */
    int getQueueSize();

    /**
     * 获取线程池中活跃的线程数量
     * @return
     */
    int getActiveSize();

    /**
     * 查看线程池是否被shutDown
     * @return
     */
    boolean isShutDown();
}
