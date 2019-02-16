package com.wondertek.baiying.EventListener.spring;

import org.omg.CORBA.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * 监听配置类
 * Created by wd on 2018/12/29.
 */
@Configuration
public class EventListenerConfig {

    public static final Logger log = LoggerFactory.getLogger(EventListenerConfig.class);

    @EventListener
    public void handleEvent(Object event) {
        //监听所有事件 可以看看 系统各类时间 发布了哪些事件
        //可根据 instanceof 监听想要监听的事件
//        if(event instanceof CustomEvent) {
//
//        }
        log.info("事件:{}", event);
    }

    /**
     * 监听CustomEvent事件
     * @param customEvent
     */
    @EventListener
    public void handleCustomEvent(CustomEvent customEvent) {
        log.info("监听到CustomEvent事件，消息为：{}，发布时间为：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }

    /**
     * 监听code为'okos'的事件
     * @param customEvent
     */
    @EventListener(condition = "#customEvent.messageEntity.code == 'okos'")
    public void handleCustomEventByCondition(CustomEvent customEvent) {
        log.info("监听到code为okos的CustomEvent事件，消息为：{}，发布时间为：{}", customEvent.getMessageEntity(),
                customEvent.getTimestamp());
    }

    /**
     * 这个和eventbus post方法一样了
     * @param messageEntity
     */
    @EventListener
    public void handleObjectEvent(MessageEntity messageEntity) {
        log.info("监听到事件对象，消息为：{}", messageEntity);
    }
}
