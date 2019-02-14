package com.wondertek.baiying.distributeLock.zookeeper2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wd on 2019/1/31.
 */
public class DistributeLockTest {
    private static final Logger LOG = LoggerFactory.getLogger(DistributeLockTest.class);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //定义一个定长的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            executorService.execute( new Runnable() {
                DistributeLock distributeLock = null;
                @Override
                public void run() {
                    try {
                        distributeLock = new DistributeLock();
                        //检查根节点
                        distributeLock.checkRootNode();
                        countDownLatch.countDown();
                        countDownLatch.await();
                        //获取锁
                        distributeLock.lock();
                        //随机睡眠一段时间，模拟业务处理
                        Thread.sleep(random.nextInt(1000));
                    } catch (Exception e) {
                        LOG.error("处理业务出现异常：", e);
                    } finally {
                        if (distributeLock != null) {
                            //释放锁
                            distributeLock.unLock();
                        }
                    }
                }

            });
        }
    }
}
