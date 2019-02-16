package com.wondertek.baiying.EventListener;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 事件源
 * Created by wd on 2018/12/29.
 */
public class EventSourceObject {

    private String name;

    //监听器容器
    private Set<CustomEventListener> listeners;

    public EventSourceObject() {
        this.name = "defaultName";
        this.listeners = new HashSet<>();
    }

    //给事件源注册监听器
    public void addCustomListener(CustomEventListener listener) {
        this.listeners.add(listener);
    }

    public void notifies() {
        CustomEventListener listener = null;
        Iterator<CustomEventListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            listener = iterator.next();
            listener.fireCustomEvent(new CustomEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    //模拟触发器，当成员变量
    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }
}
