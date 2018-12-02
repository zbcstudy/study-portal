package com.wondertek.baiying.rpc.netty.core.msg;

import java.io.Serializable;

/**
 * Created by wd on 2018/9/11.
 */
public class InvokerMsg implements Serializable{

    //类名
    private String className;

    //方法名
    private String methodName;

    //参数类型
    private Class<?>[] params;

    //参数列表
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParams() {
        return params;
    }

    public void setParams(Class<?>[] params) {
        this.params = params;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
