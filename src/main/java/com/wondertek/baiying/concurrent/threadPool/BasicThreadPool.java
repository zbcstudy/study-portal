package com.wondertek.baiying.concurrent.threadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wd on 2018/6/28.
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    //初始化线程数量
    private final int initSize;

    //线程池线程最大数量
    private final int maxSize;

    //线程池核心线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int activeCount;

    //创建线程所需要的工厂
    private final ThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    private volatile boolean isShutDown = false;
    //工作线程队列
    private final Queue<ThreadTask> threadTaskQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final int keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, queueSize, DEFAULT_THREAD_FACTORY, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize, ThreadFactory threadFactory,
                           DenyPolicy denyPolicy, int keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.keepAliveTime = keepAliveTime;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.timeUnit = timeUnit;
        this.init();
    }

    /**
     * 初始化时，先创建initSize个线程
     */
    private void init() {
        start();
        for(int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutDown) {
            throw new IllegalStateException("the thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    /**
     * 销毁线程池
     * 主要是停止BasicThreadPool线程，停止线程池中的活动线程，并将isShutDown开关变量更改为true
     */
    @Override
    public void shutDown() {
        synchronized (this) {
            if (isShutDown) {
                return;
            }
            isShutDown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("the thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("the thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("the thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (this.isShutDown) {
            throw new IllegalStateException("the thread pool is destroy");
        }
        return runnableQueue.size();
    }

    @Override
    public int getActiveSize() {
        synchronized (this) {
            return activeCount;
        }
    }

    @Override
    public boolean isShutDown() {
        return this.isShutDown;
    }

    /**
     * run()方法继承自Thread，主要用于维护线程数量，如扩容、回收等工作
     */
    @Override
    public void run() {
        while (!isShutDown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutDown = true;
                break;
            }
            synchronized (this) {
                if (isShutDown) {
                    break;
                }
                //当前队列中有任务尚未进行处理，并且activeCount < coreSize,进行扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    //continue的目的是不希望线程的扩容直接打到maxSize
                    continue;
                }

                //当前队列中有任务尚未进行处理，并且activeCount<maxSize,进行扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                //当任务队列中没有任务，将线程池中的线程进行回收，回收至coreSize即可
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for(int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }

    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.newThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    private static class ThreadTask {

        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup THREAD_GROUP = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(THREAD_GROUP,r,"thread-pool-"+COUNTER.getAndDecrement());
        }
    }

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(1);
        System.out.println(integer.getAndIncrement());
        System.out.println(integer.getAndIncrement());
    }


}
