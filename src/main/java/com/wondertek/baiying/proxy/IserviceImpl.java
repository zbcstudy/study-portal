package com.wondertek.baiying.proxy;

/**
 * Created by wd on 2018/9/27.
 */
public class IserviceImpl implements Iservice {

    @Override
    public String doFirst() {
        System.out.println("执行doFirst()方法");
        return "abc";
    }

    @Override
    public String doSecond() {
        System.out.println("执行doSecond()方法");
        return "def";
    }
}
