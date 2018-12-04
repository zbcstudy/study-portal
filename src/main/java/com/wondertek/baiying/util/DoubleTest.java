package com.wondertek.baiying.util;

import org.junit.Test;

/**
 * Created by wd on 2018/4/12.
 */
public class DoubleTest {

    @Test
    public void test() {
        float a = 16.3f;
        double c = (double)a;
        System.out.println(a==c);
        System.out.println((double)a);
        float b = 16.5f;
        double d = (double) b;
        System.out.println(b == d);
        System.out.println((double)b);
    }
}
