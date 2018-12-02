package com.wondertek.baiying.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射API动态操作 构造器，方法，属性
 * Created by wd on 2018/9/26.
 */
public class ReflectionTest2 {

    public static void main(String[] args) {

        String path = "com.wondertek.baiying.reflection.User";

        try {
            Class<?> clazz = Class.forName(path);

            //通过反射API调用构造方法，创建对象
            User user = (User) clazz.newInstance(); //调用了User的无参构造方法
            System.out.println(user);

            Constructor<?> c = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class);

            User user2 = (User) c.newInstance("1", "2", "3", "4", "5");
            System.out.println(user2.getAge());

            /**
             * 通过反射API调用对象的方法
             *
             */
            User u3 = (User) clazz.newInstance();
            Method method = clazz.getDeclaredMethod("setUserName", String.class);
            method.invoke(u3, "赵必成");
            System.out.println(u3.getUserName());

            /**
             * 通过反射api操作属性
             *
             */
            User u4 = (User) clazz.newInstance();
            Field field = clazz.getDeclaredField("userName");
            field.setAccessible(true); //设置可以直接访问，不需要做安全检查
            field.set(u4,"张飞");  //通过反射写属性
            System.out.println(u4.getUserName());  //通过反射直接读取属性

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
