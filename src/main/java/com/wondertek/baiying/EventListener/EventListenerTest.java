package com.wondertek.baiying.EventListener;

/**
 * Created by wd on 2018/12/29.
 */
public class EventListenerTest {
    public static void main(String[] args) {
        EventSourceObject eventSourceObject = new EventSourceObject();
        //注册监听器
        eventSourceObject.addCustomListener(new CustomEventListener(){
            @Override
            public void fireCustomEvent(CustomEvent customEvent) {
                super.fireCustomEvent(customEvent);
            }
        });
        eventSourceObject.setName("new name");
    }
}
