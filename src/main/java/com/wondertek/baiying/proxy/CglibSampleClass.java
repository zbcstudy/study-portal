package com.wondertek.baiying.proxy;

import net.sf.cglib.proxy.*;
import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created by wd on 2018/12/24.
 */
public class CglibSampleClass {

    String name = "sampleClass";

    public String test(String input) {
        System.out.println("cglib动态代理实现");
        if (StringUtils.isBlank(input)) {
            return name;
        }
        else
            return input;
    }

    /**
     * Enhancer不能操作static或者final类
     */
    @Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibSampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before method run...");
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("end method run...");
                return result;
            }
        });

        CglibSampleClass cglibSampleClass = (CglibSampleClass) enhancer.create();
        System.out.println(cglibSampleClass.getClass().getName());
        cglibSampleClass.test(null);
    }

    /**
     * 测试FixedValue
     * Fixedvalue用来对所有拦截的方法返回相同的值
     * Enhancer只拦截非final方法，不拦截final方法
     */
    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(CglibSampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "cglib代理返回固定的值";
            }
        });
        CglibSampleClass sampleClass = (CglibSampleClass) enhancer.create();
        System.out.println(sampleClass.test(null));
        System.out.println(sampleClass.getClass());
        System.out.println(sampleClass.toString());
    }

    /**
     * 测试invocationHandler
     */
    @Test
    public void testInvocationHandler() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(CglibSampleClass.class);

        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "hello cglib";
                }else {
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        CglibSampleClass sampleClass = (CglibSampleClass) enhancer.create();
        Assert.assertEquals("hello cglib", sampleClass.test(null));
        Assert.assertNotEquals("Hello cglib", sampleClass.toString());
    }

    /**
     * 测试只对特定的方法进行拦截
     */
    @Test
    public void testCallBackFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibSampleClass.class);

        //使用callbackHelper对特定方法进行过滤
        CallbackHelper callbackHelper = new CallbackHelper(CglibSampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                            Object result = methodProxy.invokeSuper(o, objects);
                            return result.toString().toUpperCase();
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };

        enhancer.setSuperclass(CglibSampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //创建增强对象
        CglibSampleClass proxy = (CglibSampleClass) enhancer.create();
        System.out.println(proxy.test(null));
        System.out.println(proxy.test("zbc"));
        System.out.println(proxy.toString());
    }

    /**
     * spring core包中自带了一个cglib实现包，测试是否可用
     * result 可用
     */
    @Test
    public void testSpringCglib() {
        org.springframework.cglib.proxy.Enhancer enhancer = new org.springframework.cglib.proxy.Enhancer();
        enhancer.setSuperclass(CgService.class);
        enhancer.setCallback(new org.springframework.cglib.proxy.MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, org.springframework.cglib.proxy.MethodProxy methodProxy) throws Throwable {
                Object result = methodProxy.invokeSuper(o, objects);

                return result + "$$$$" +result.toString().toUpperCase();
            }
        });
        CgService cgService = (CgService) enhancer.create();
        System.out.println(cgService.doFirst());
    }
}
