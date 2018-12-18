package com.wondertek.baiying.distributeLock.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 基于Zookeeper实现分布式锁
 * Created by wd on 2018/12/3.
 */
public class DistributeLock {

    private static final int SESSION_TIMEOUT = 10000;

    private static final int DEFAULT_TIMEOUT_PERIOD = 10000;

    private static final String CONNECTION_STRING = "127.0.0.1:2180,127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private static final byte[] data = {0x12, 0x34};

    private ZooKeeper zooKeeper;

    private String root;

    private String id;

//    private LockNode

    private String ownerId;

    private String lastChildId;

    private Throwable other = null;

    private KeeperException exception = null;

    private InterruptedException interruptedException = null;

    public DistributeLock(String root) {
        try {
            this.zooKeeper = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, null);
            this.root = root;
            ensureExists(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.root = root;
    }

    /**
     * 尝试获取锁，阻塞式，可被中断
     */
    public void lock() throws Exception {
        //在初始化时就已经失败
        if (exception != null) {
            throw exception;
        }
        if (interruptedException != null) {
            throw interruptedException;
        }
        if (other != null) {
            throw new Exception("", other);
        }

        //检测锁重入
        if (isOwner()) {
            return;
        }
        BooleanMutex mutex = new BooleanMutex();
        acquireLock(mutex);
    }


    /**
     * 判断某path端点是否存在，不存在就创建
     * @param root
     */
    private void ensureExists(String root) {

        try {
            Stat stat = zooKeeper.exists(root, false);
            if (stat != null) {
                return;
            }
            //不存在，创建
            zooKeeper.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            exception = e;
        } catch (InterruptedException e) {
            interruptedException = e;
        }
    }

    public boolean isOwner() {
        return id != null && ownerId != null && id.equals(ownerId);
    }

    /**
     * 执行lock操作，允许传递watch变量控制是否需要阻塞lock操作
     * @param mutex
     */
    private void acquireLock(BooleanMutex mutex) {
        try {
            do {
                if (id == null) { //构建当前lock的唯一标识
                    long sessionId = zooKeeper.getSessionId();
                    String prefix = "x-" + sessionId + "-";
                    //如果第一次，则创建一个节点
                    String path = zooKeeper.create(root + "/" + prefix, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                    int index = path.lastIndexOf("/");
                    id = StringUtils.substring(path, index + 1);
//                    new lockBode

                }
            } while (id == null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
