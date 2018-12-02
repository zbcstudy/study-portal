package com.wondertek.baiying.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wd on 2018/9/26.
 */
public class ReflectionTest {

    public static void main(String[] args) {

        String path = "com.wondertek.baiying.reflection.User";

        try {
            /**
             * 当一个类别加载后。jvm会创建一个对应该类的Class对象，类的整个结构信息会放到对应的Class对象中
             * 这个Class对象像一面镜子一样，通过这面镜子可以看到对应类的全部信息。
             */
            Class<?> clazz = Class.forName(path);
            System.out.println(clazz.hashCode());

            Class<?> clazz2 = Class.forName(path);
            System.out.println(clazz2.hashCode());
            System.out.println(clazz == clazz2); //true

            //获取类的名字
            System.out.println(clazz.getName());
            System.out.println(clazz.getSimpleName());

            System.out.println("-----------------------");

            //获取类的属性信息
//            Field[] fields = clazz.getFields(); //只能获取public类型的field

            Field[] fields = clazz.getDeclaredFields();


            Field username = clazz.getDeclaredField("userName");
            System.out.println("userName: "+username);

            for (Field field : fields) {
                System.out.println("属性："+field.getName());
            }

            /**
             * 获取方法信息
             */
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Method method = clazz.getDeclaredMethod("getUserName", null);
            System.out.println("-----------method---------" + method.getName());
            for (Method declaredMethod : declaredMethods) {
                System.out.println("方法："+declaredMethod);
            }

            /**
             * 获取构造器信息
             * 可以通过传递不同的参数类型，获取不同的构造器
             *
             */
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("构造器：" + constructor);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
