package com.wondertek.baiying.util.concurrent;

import java.util.concurrent.ForkJoinPool;

/**
 *
 * 测试多线程的分叉和合并
 * Created by wd on 2017/9/29.
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        RecursiveActionTest recursiveActionTest = new RecursiveActionTest(36);

        //forkJoinPool.invoke(recursiveActionTest);


        RecursiveTaskTest recursiveTaskTest = new RecursiveTaskTest(128);
        Object invoke = forkJoinPool.invoke(recursiveTaskTest);
        System.out.println("invoke: " + invoke.toString());
    }
}
