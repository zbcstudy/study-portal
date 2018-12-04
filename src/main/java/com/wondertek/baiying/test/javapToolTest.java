package com.wondertek.baiying.test;

/**
 * Created by wd on 2018/1/3.
 * java程序编译执行顺序：静态代码块，变量加载，代码块，构造函数
 */
public class javapToolTest {

    int count = 20;
    {
     count = 12;
    }


    static{
        System.out.println("静态代码块执行");
    }

    public javapToolTest() {
        System.out.println(count);
    }

    public javapToolTest(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        javapToolTest javapToolTest = new javapToolTest();
    }

}
