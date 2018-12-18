package com.wondertek.baiying.distributeLock.redis;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wd on 2018/12/4.
 */
public class Service {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 6379;

    private static final int DEFAULT_TIMEOUT = 3000;

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(200);
        //设置最大空闲数
        config.setMaxIdle(8);
        //设置最大等待时间
        config.setMaxWaitMillis(1000 * 10);
        //在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);

        jedisPool = new JedisPool(config, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_TIMEOUT);
    }

    DistributeLock distributeLock = new DistributeLock(jedisPool);

    int n = 500;

    public void seckill() {
        //返回锁的value值，供释放锁时进行判断
        String identifier = distributeLock.lockWithTimeout("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "======获得了锁====");
        System.out.println(--n);
        distributeLock.releaseLock("resource", identifier);
    }
}
