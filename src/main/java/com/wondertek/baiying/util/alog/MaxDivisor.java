package com.wondertek.baiying.util.alog;

import java.util.Scanner;

/**
 * @author aaron
 * @since 2021/4/14
 */
public class MaxDivisor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字有1：");
        int a = scanner.nextInt();
        System.out.println("请输入数字有2：");
        int b = scanner.nextInt();

        int min = Math.min(a, b);
        for (int i = min; i > 0; i--) {
            if (a % min == 0 && b % min == 0) {
                System.out.println("最大公约数为：" + i);
                return;
            }
        }
    }
}
