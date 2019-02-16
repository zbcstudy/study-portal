package com.wondertek.baiying.EventListener.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/12/29.
 */
@Component
public class EventListener implements ApplicationListener<CustomEvent> {

    private static final Logger log = LoggerFactory.getLogger(EventListener.class);

    /**
     * 这里也可以监听所有事件 使用  ApplicationEvent 类即可
     * 这里仅仅监听自定义事件 CustomEvent
     * @param event
     */
    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("ApplicationListener方式监听事件：{}", event);
    }
}
