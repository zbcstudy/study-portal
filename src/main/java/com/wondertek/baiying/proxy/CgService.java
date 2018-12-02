package com.wondertek.baiying.proxy;

/**
 * Created by wd on 2018/9/27.
 */
public class CgService {

    public String doFirst() {
        System.out.println("执行doFirst()方法");
        return "abc";
    }

    public String doSecond() {
        System.out.println("执行doSecond()方法");
        return "def";
    }
}
