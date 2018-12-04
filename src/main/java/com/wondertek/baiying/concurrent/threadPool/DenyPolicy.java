package com.wondertek.baiying.concurrent.threadPool;

/**
 * 当任务队列中的任务数量达到limit上限时，决定以何种策略通知提交者
 * Created by wd on 2018/6/28.
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);


    /**
     * 直接将任务丢弃。不做任何操作
     */
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    /**
     * 抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new DenyPolicyException("the runnable " + runnable + "will be abort");
        }
    }

    /**
     * 该策略会使任务在提交者所在的线程中执行
     */
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

            if (!threadPool.isShutDown()) {
                runnable.run();
            }
        }
    }
}
