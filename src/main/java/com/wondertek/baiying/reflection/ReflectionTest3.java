package com.wondertek.baiying.reflection;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * 通过反射API 泛型操作
 * Created by wd on 2018/9/26.
 */
public class ReflectionTest3 {

    public void test01(Map<String, User> map, List<User> list) {
        System.out.println("ReflectionTest3.test01");
    }

    public Map<Integer, User> test02() {
        System.out.println("ReflectionTest3.test02");
        return null;
    }

    public static void main(String[] args) {

        try {

            /**
             * 获得指定方法的泛型参数信息
             */
            Method method = ReflectionTest3.class.getMethod("test01", Map.class, List.class);
            Type[] parameterTypes = method.getGenericParameterTypes();
            for (Type parameterType : parameterTypes) {
                System.out.println("#"+parameterType);

                if (parameterType instanceof ParameterizedType) {
                    Type[] types = ((ParameterizedType) parameterType).getActualTypeArguments();
                    for (Type type : types) {
                        System.out.println("泛型类型：" + type);
                    }
                }
            }

            /**
             * 获得指定方法返回值泛型信息
             *
             */
            Method m2 = ReflectionTest3.class.getMethod("test02", null);
            Type returnType = m2.getGenericReturnType();
            System.out.println("返回值的类型：" + returnType);
            if (returnType instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
                for (Type type : types) {
                    System.out.println("返回值的泛型类型："+type);
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
