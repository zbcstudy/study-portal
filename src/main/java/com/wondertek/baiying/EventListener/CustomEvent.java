package com.wondertek.baiying.EventListener;

import java.util.EventObject;

/**
 * 事件类，用于封装事件源及一些与事件相关的参数
 * Created by wd on 2018/12/29.
 */
public class CustomEvent extends EventObject {

    //事件源
    private Object source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
