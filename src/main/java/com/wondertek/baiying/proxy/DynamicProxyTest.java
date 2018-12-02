package com.wondertek.baiying.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wd on 2018/9/27.
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        final Iservice target = new IserviceImpl();

        Iservice service = (Iservice) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),   //目标类的类加载器
                target.getClass().getInterfaces(),    //目标类所实现的所有接口
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy 代理对象
                     * @param method 目标方法
                     * @param args 目标方法的参数列表
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = method.invoke(target, args);
                        return ((String)result).toUpperCase();
                    }
                }
        );

        String result = service.doFirst();
        System.out.println(result);
        service.doSecond();
    }

    @Test
    public void test01() {
        CgService target = new CgService();

        CgService service = new CglibFactory(target).myCgService();

        String result = service.doFirst();
        System.out.println(result);
        System.out.println(service.doSecond());
    }
}
