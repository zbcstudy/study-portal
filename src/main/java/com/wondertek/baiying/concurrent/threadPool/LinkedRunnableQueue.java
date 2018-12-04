package com.wondertek.baiying.concurrent.threadPool;

import java.util.LinkedList;

/**
 * RunnableQueue的实现类
 * Created by wd on 2018/6/28.
 */
public class LinkedRunnableQueue implements RunnableQueue{

    //任务队列的最大数量
    private final int limit;

    //当队列已满时，执行拒绝策略
    private final DenyPolicy denyPolicy;

    //存放任务的队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                //无法容纳新任务时执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        //从任务对列头部移除一个任务
        return runnableList.removeFirst();
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
