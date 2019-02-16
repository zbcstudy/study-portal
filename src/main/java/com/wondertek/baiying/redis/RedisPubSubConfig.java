package com.wondertek.baiying.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 使用spring-data-redis实现redis的发布订阅模式
 * Created by wd on 2019/1/7.
 */
public class RedisPubSubConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public ConsumerRedisListener consumerRedisListener() {
        return new ConsumerRedisListener();
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("string-topic");
    }


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(consumerRedisListener(),topic());
        return container;
    }
}
