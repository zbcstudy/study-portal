package com.wondertek.baiying.util.designPattern;

/**
 * 单例模式的几种实现方式
 * Created by wd on 2017/9/30.
 */
public class Singlton {

    private Singlton(){}
    /**
     * 饿汉式
     */
    private static Singlton instance = new Singlton();

    public static Singlton getInstance(){
        return instance;
    }

}
