package com.wondertek.baiying.config;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

/**
 * rabbitMq配置
 *  一般情况下，使用rabbitMq是不需要进行任何配置的，rabbitMq默认采用二进制进行传输，这里进行修改
 *  改为使用json进行传输
 * Created by wd on 2017/9/8.
 */

//@Configuration
//public class RabbitMqConfig {
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactoryPlus(
//            SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory,
//            Jackson2JsonMessageConverter jackson2JsonMessageConverter){
//        rabbitListenerContainerFactory.setMessageConverter(jackson2JsonMessageConverter);
//        return rabbitListenerContainerFactory;
//    }
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Bean
//    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper xssObjectMapper) {
//        return new Jackson2JsonMessageConverter(xssObjectMapper);
//    }
//
//}
