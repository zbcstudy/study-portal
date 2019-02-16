package com.wondertek.baiying.EventListener;

import java.util.EventListener;

/**
 * 事件监听器：
 *      实现java.util.EventListener接口,注册在事件源上,当事件源的属性或状态改变时,取得相应的监听器调用其内部的回调方法
 *      定义回调方法，将你想要做的事 放到这个方法下,因为事件源发生相应的事件时会调用这个方法
 * Created by wd on 2018/12/29.
 */
public class CustomEventListener implements EventListener {

    //事件发生后的回调方法
    public void fireCustomEvent(CustomEvent customEvent) {
        EventSourceObject object = (EventSourceObject) customEvent.getSource();
        System.out.println("name has been changed");
        System.out.println("get a new name: " + object.getName());
    }
}
