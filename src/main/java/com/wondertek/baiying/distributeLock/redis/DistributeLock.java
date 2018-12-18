package com.wondertek.baiying.distributeLock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.RedisPipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by wd on 2018/12/4.
 */
public class DistributeLock {

    private final JedisPool jedisPool;

    public DistributeLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 加锁
     * @param lockName 锁的名称
     * @param acquireTime 获取超时时间
     * @param timeout   锁的超时时间
     * @return  锁的标识
     */
    public String lockWithTimeout(String lockName, long acquireTime, long timeout) {
        Jedis jedis = null;
        String retIdentifier = null;

        try {
            //获取连接
            jedis = jedisPool.getResource();
            //随机生成一个value
            String identifier = UUID.randomUUID().toString();
            //锁名
            String lockKey = "lock:" + lockName;
            //超时时间，上锁超过此时则自动释放锁
            int lockExpire = (int) (timeout / 1000);

            //获取锁的超时时间，超过这个时间则自动放弃锁
            long end = System.currentTimeMillis() + acquireTime;

            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, identifier) == 1) {
                    jedis.expire(lockKey, lockExpire);
                    //返回value值，用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }

                //返回-1代表redis没有设置超时时间，为key设置一个超时时间
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (JedisException e) {

        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return retIdentifier;
    }

    public boolean releaseLock(String lockName, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;

        try {
            conn = jedisPool.getResource();
            while (true) {
                //监视lock，准备开始事务
                conn.watch(lockKey);
                //通过返回的value值判断是不是该锁，若是该锁，则删除 释放锁
                if (identifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> result = transaction.exec();
                    if (result == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (JedisException e) {

        }finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retFlag;
    }
}
