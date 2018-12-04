package com.wondertek.baiying.util;

import org.junit.Test;

/**
 * Created by wd on 2018/4/12.
 */
public class ExtendsTest {

    /**
     * 执行结果
     * foo---------static
     son------------------static
     foo-------------constrator
     son------------constrator
     foo-------------constrator
     */
    @Test
    public void test() {
        //Foo foo = new Son();
        Foo foo = new Foo();
    }

}

class Foo{

    static{
        System.out.println("foo---------static");
    }

    public Foo() {
        System.out.println("foo-------------constrator");
    }
}

class Son extends Foo{
    static{
        System.out.println("son------------------static");
    }

    public Son() {
        System.out.println("son------------constrator");
    }
}
