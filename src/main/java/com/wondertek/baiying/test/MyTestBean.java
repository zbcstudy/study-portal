package com.wondertek.baiying.test;

/**
 * Created by wd on 2018/1/22.
 */
public class MyTestBean {

    private String name = "test-bean";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test() {
        System.out.println("test");
    }
}
