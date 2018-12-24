package com.wondertek.baiying.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wd on 2018/12/24.
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    public JdkDynamicProxy() {
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do something before");
        Object result = method.invoke(target, args);
        System.out.println("Do something after");
        return result;
    }

    /**
     * main方法中的内容放到@Test方法中不可运行
     * @param args
     */
    public static void main(String[] args) {
        //保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //jdk动态代理测试
        Iservice iservice = new JdkDynamicProxy(new IserviceImpl()).getProxy();
        System.out.println(iservice.doFirst());
    }


}
