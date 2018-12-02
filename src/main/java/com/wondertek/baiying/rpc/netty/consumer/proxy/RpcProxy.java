package com.wondertek.baiying.rpc.netty.consumer.proxy;

import com.wondertek.baiying.rpc.netty.core.msg.InvokerMsg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wd on 2018/9/10.
 */
public class RpcProxy {

    public static <T> T create(Class<?> clazz) {

//        MethodProxy proxy = new MethodProxy(clazz);
//        return T;
        return null;

    }

}

class MethodProxy implements InvocationHandler{

    private Class<?> clazz;

    private MethodProxy(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //如果传进来的是一个类，不做任何处理
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            //如果传进来的是一个接口

            return rpcInvoke(proxy, method, args);
        }
        return null;
    }

    private Object rpcInvoke(Object proxy, Method method, Object[] args) {
        InvokerMsg invokerMsg = new InvokerMsg();
        invokerMsg.setClassName(this.clazz.getName());
        invokerMsg.setMethodName(method.getName());
        invokerMsg.setParams(method.getParameterTypes());
        invokerMsg.setValues(args);

        return null;
    }


}
