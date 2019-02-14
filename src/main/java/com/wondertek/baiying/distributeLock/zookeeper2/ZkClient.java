package com.wondertek.baiying.distributeLock.zookeeper2;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 创建zookeeper连接
 * Created by wd on 2019/1/31.
 */
public class ZkClient {
    private static ZooKeeper instance = null;
    //连接地址
    private final static String CONNECT_STRING ="127.0.0.1:2181";
    //会话超时时间
    private final static int SESSION_TIMEOUT = 3000;
    //私有的构造方法
    private ZkClient() {}

    public static ZooKeeper getInstance() {
        if (instance == null) {
            synchronized (ZkClient.class) {
                if (instance == null) {
                    try {
                        instance = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
                            @Override
                            public void process(WatchedEvent event) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
}
