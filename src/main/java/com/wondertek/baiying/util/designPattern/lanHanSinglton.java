package com.wondertek.baiying.util.designPattern;

/**
 * 懒汉式单例模式
 * Created by wd on 2017/9/30.
 */
public class lanHanSinglton {

    private lanHanSinglton(){};

    private static lanHanSinglton instance;

    public static lanHanSinglton getInstance(){
        if (instance == null){
            instance = new lanHanSinglton();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("你真帅");
    }
}
