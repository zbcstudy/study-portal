package com.wondertek.baiying.distributeLock.zookeeper2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wd on 2019/1/31.
 */
public class DistributeLock {
    private static final Logger LOG = LoggerFactory.getLogger(DistributeLock.class);

    //根节点
    private static final String ROOT_NODE_LOCK = "/ROOT_LOCK";

    private ZooKeeper zooKeeper;

    //当前节点锁的id
    private String currentLockId;

    //节点存放的数据
    private final static String DATA = "node";

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistributeLock () {
        //初始化连接实例
        this.zooKeeper = ZkClient.getInstance();
    }

    /**
     * 检查根节点是否存在
     * 根节点是持久化节点
     */
    public void checkRootNode() {
        try {
            Stat stat = zooKeeper.exists(ROOT_NODE_LOCK, false);
            if (stat == null) {
                //根节点不存在，创建
                zooKeeper.create(ROOT_NODE_LOCK,DATA.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            LOG.info("根节点:{}创建异常",ROOT_NODE_LOCK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取分布式锁
     * @return
     */
    public synchronized boolean lock() {
        try {
            currentLockId = zooKeeper.create(ROOT_NODE_LOCK + "/", DATA.getBytes(),
                                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOG.info("线程{}创建了节点{}", Thread.currentThread().getName(), currentLockId);
            //获取根节点下的子节点的集合
            List<String> childrenNodeList = zooKeeper.getChildren(ROOT_NODE_LOCK, true);
            //对子节点进行排序
            Collections.sort(childrenNodeList);
            //获取到第一个节点
            String firstNode = childrenNodeList.get(0);
            String currentNode = currentLockId.substring(currentLockId.lastIndexOf("/") + 1);

            //如果当前子节点就是第一个节点
            if (currentNode.equals(firstNode)) {
                LOG.info("线程{}获取到分布式锁，当前子节点{}", Thread.currentThread().getName(), currentLockId);
                return true;
            }
            int index = childrenNodeList.indexOf(currentNode);
            LOG.info("线程{}对应的子节点不是当前子节点，当前子节点{}", Thread.currentThread().getName(), currentLockId);

            if (index > 0) {
                //获取当前子节点的前一个节点
                String preNode = childrenNodeList.get(index - 1);
                LOG.info("线程{}获取当前字节点的前一个节点,当前子节点{},前一个节点{}", Thread.currentThread().getName(),
                        currentLockId, preNode);

                zooKeeper.exists(ROOT_NODE_LOCK + "/" + preNode, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        //监听前一个节点的删除事件
                        if (event.getType().equals(Event.EventType.NodeDeleted)) {
                            LOG.info("当前节点{}监听到前一个节点{}的删除事件", currentLockId, preNode);
                            countDownLatch.countDown();
                        }
                    }
                });
                countDownLatch.await();
                LOG.info("线程{}获取到分布式锁，当前子节点{}", Thread.currentThread().getName(), currentLockId);
                return true;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public synchronized boolean unLock() {
        LOG.info("线程{}开始释放分布式锁,当前子节点{}", Thread.currentThread().getName(), currentLockId);
        //删除当前子节点
        try {
            zooKeeper.delete(currentLockId, -1);
            LOG.info("线程{}释放分布式锁成功,当前子节点{}", Thread.currentThread().getName(), currentLockId);
            return true;
        } catch (Exception e) {
            LOG.error("释放分布式锁失败");
            e.printStackTrace();
        }
        return false;
    }
}
