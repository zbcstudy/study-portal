package com.wondertek.baiying.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * spring-redis的订阅代码
 * Created by wd on 2019/1/7.
 */
public class ConsumerRedisListener implements MessageListener{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(new String(pattern));
        doMessage(message);
    }

    private void doMessage(Message message) {
        Object body = stringRedisTemplate.getValueSerializer().deserialize(message.getBody());
        System.out.println("get message: " + body.toString());
    }
}
