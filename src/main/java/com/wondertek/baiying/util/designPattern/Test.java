package com.wondertek.baiying.util.designPattern;

/**
 * Created by wd on 2017/9/30.
 */
public class Test {

    public static void main(String[] args) {
        Singlton instance = Singlton.getInstance();
        System.out.println(instance);
    }


    @org.junit.Test
    public void lanHanSingltonTest(){
        lanHanSinglton instance = lanHanSinglton.getInstance();
        System.out.println(instance);
        instance.showMessage();
    }
}
