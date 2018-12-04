package com.wondertek.baiying.rpc.netty.provider;

import com.wondertek.baiying.rpc.netty.api.IRpcCalc;

/**
 * Created by wd on 2018/9/10.
 */
public class RpcCalc implements IRpcCalc {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
