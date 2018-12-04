package com.wondertek.baiying.util;

import org.junit.Test;

/**
 * Created by wd on 2018/4/11.
 */
public class JdkVersion {

     private static final String jdkVsersion;

     static {
         jdkVsersion = System.getProperty("java.version");
     }

    public static String getJdkVsersion() {
        return jdkVsersion;
    }

    @Test
    public void test() {
        System.out.println(getJdkVsersion());
    }

}
