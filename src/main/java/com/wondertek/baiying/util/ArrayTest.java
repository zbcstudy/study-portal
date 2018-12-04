package com.wondertek.baiying.util;

import java.util.Arrays;

/**
 * Created by wd on 2018/1/11.
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] a = {1, 12, 2, 22,};
        int[] b = new int[4];
        System.arraycopy(a,0,b,0,a.length);
        Arrays.sort(b);
        String s = Arrays.toString(b);
        System.out.println(s);
        int i1 = Arrays.binarySearch(b, 2);
        System.out.println(i1);
        for (int i : b) {
            System.out.println(i);
        }

        System.out.println(1<<13);
    }
}
