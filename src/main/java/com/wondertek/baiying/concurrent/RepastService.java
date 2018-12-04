package com.wondertek.baiying.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Semaphor类实现生产者/消费者模型，并控制生产者、消费者的数量
 * Created by wd on 2018/6/26.
 */
public class RepastService {

    //生产者,数量10
    private volatile Semaphore setSemaphor = new Semaphore(10);
    //消费者,数量20
    private volatile Semaphore getSemaphor = new Semaphore(20);

    private volatile ReentrantLock lock = new ReentrantLock();

    private volatile Condition setCondition = lock.newCondition();

    private volatile Condition getCondition = lock.newCondition();

    //存储容量
    private volatile Object[] producePosition = new Object[4];

    /**
     * 判断存储容器是否为空
     * @return
     */
    public Boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] != null) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断存储容器是否存储满
     * @return
     */
    public boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < producePosition.length; i++) {
            if (producePosition[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    /**
     * 生产数据
     */
    public void set() {
//        System.out.println("set value");
        try {
            //最多允许10个线程进行生产
            setSemaphor.acquire();
            //获取显示锁
            lock.lock();
            //容器中数据已满
            while (isFull()) {
                //System.out.println("生产者在等待");
                setCondition.await();//等待
            }
            for (int i = 0; i < producePosition.length; i++) {
                //容器该位置下无数据
                if (producePosition[i] == null) {
                    System.out.println(Thread.currentThread().getName()+"生产了数据");
                    producePosition[i] = "数据";
                    break;
                }
            }
            //唤醒所有等待的线程
            getCondition.signalAll();
            //释放所
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            setSemaphor.release();
        }
    }

    /**
     * 从容器中获取数据
     */
    public void get() {
//        System.out.println("get value");
        try {
            //最多同时允许20个线程获取数据
            getSemaphor.acquire();
            lock.lock();
            while (isEmpty()) {
                //容器中数据为空,获取数据线程等待
                getCondition.await();
            }
            for (int i = 0; i < producePosition.length; i++) {
                if (producePosition[i] != null) {
                    System.out.println(Thread.currentThread().getName() + "消费了" + producePosition[i]);
                    producePosition[i] = null;
                    break;
                }
            }
            //唤醒所有等待线程
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            getSemaphor.release();
        }
    }

    public static void main(String[] args) {

    }
}
