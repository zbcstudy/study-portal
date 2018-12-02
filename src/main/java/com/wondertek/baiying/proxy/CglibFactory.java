package com.wondertek.baiying.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 无接口的Cglib动态代理实现
 * Cglib动态代理增强的原理：通过创建子类来增强父类对象
 * 使用Cglib动态代理，目标类不能是final的：final类是不能有子类的
 * Created by wd on 2018/9/27.
 */
public class CglibFactory implements MethodInterceptor {

    private CgService target;

    public CglibFactory() {
        super();
    }

    public CglibFactory(CgService cgService) {
        super();
        this.target = cgService;
    }

    public CgService myCgService() {
        Enhancer enhancer = new Enhancer();
        //指定父类，即目标类，Cglib动态代理增强的原理：子类增强父类
        enhancer.setSuperclass(CgService.class);

        //设置回调接口对象
        enhancer.setCallback(this);

        //create()方法用于创建Cglib动态代理对象，即目标类的子对象
        return (CgService) enhancer.create();
    }

    /**
     * 回调接口的方法执行条件：代理对象执行目标时会触发该方法执行
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invoke = method.invoke(target, objects);
        String result = ((String) invoke).toUpperCase();
        if (StringUtils.isNotBlank(result)) {
            return result;
        }
        return null;
    }
}
