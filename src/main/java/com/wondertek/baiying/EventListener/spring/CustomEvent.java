package com.wondertek.baiying.EventListener.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 消息事件源
 * Spring中，事件源不强迫继承ApplicationEvent接口的，也就是可以直接发布任意一个对象类。
 * 但内部其实是使用PayloadApplicationEvent类进行包装了一层。这点和guava的eventBus类似
 * Created by wd on 2018/12/29.
 */
public class CustomEvent extends ApplicationEvent {

    private MessageEntity messageEntity;


    public CustomEvent(Object source, MessageEntity messageEntity) {
        super(source);
        this.messageEntity = messageEntity;
    }

    public MessageEntity getMessageEntity() {
        return messageEntity;
    }

    public void setMessageEntity(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
    }
}
