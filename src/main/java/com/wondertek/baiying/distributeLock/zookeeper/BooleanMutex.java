package com.wondertek.baiying.distributeLock.zookeeper;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 互斥信号共享锁
 * Created by wd on 2018/12/3.
 */
public class BooleanMutex {


    private final class Sync extends AbstractQueuedSynchronizer{
        private static final long serialVersionUID = -7828117401763700385L;

        //状态为1 则唤醒被阻塞在状态为false的所有线程
        private static final int TRUE = 1;
        //状态为0 则当前线程阻塞，等待被唤醒
        private static final int FALSE = 0;

        //返回值大于0则执行，返回值小于0则阻塞
        @Override
        protected int tryAcquireShared(int arg) {
            return getState() == 1 ? 1 : -1;
        }


        /**
         * 实现AQS的接口，释放共享锁的判断
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return true;
        }

        private boolean innerState() {
            return getState() == 1;
        }

        private void innerLock() throws InterruptedException {
            acquireSharedInterruptibly(0);
        }

        private void innerLock(long nanosTimeout) throws InterruptedException, TimeoutException {
            if (!tryAcquireSharedNanos(0, nanosTimeout)) {
                throw new TimeoutException();
            }
        }

        public void innerSetTrue() {
            for (;;) {
                int s = getState();
                if (s == TRUE) {
                    return; //直接退出
                }
                if (compareAndSetState(s, FALSE)) { //cas更新状态，避免并发更新false操作
                    setState(FALSE);
                }
            }
        }
    }
}
