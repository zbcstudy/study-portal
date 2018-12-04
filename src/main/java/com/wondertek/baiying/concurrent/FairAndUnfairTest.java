package com.wondertek.baiying.concurrent;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesHandlerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wd on 2018/7/11.
 */
public class FairAndUnfairTest {

    private static Lock fairLock = new ReentrantLock2(true);

    private static Lock unFairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unFair() {
        testLock(unFairLock);
    }

    public void testLock(Lock lock) {
        //连续启动5个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Job(lock));
        }
        executorService.shutdown();
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getThreadGroup() + Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }

        }
    }

    /**
     * 内部类，扩展锁功能
     */
    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
