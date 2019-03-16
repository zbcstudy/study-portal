package com.wondertek.baiying.java8;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wd on 2019/3/14.
 */
public class ReflectionTest {

    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("java.lang.Thread");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取类声明的内部类
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            System.out.println(declaredClass);
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        System.out.println("--------------");
        //获取类声明的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        System.out.println(clazz);
    }
}
