package com.wondertek.baiying.rpc.netty.consumer;

import com.wondertek.baiying.rpc.netty.api.IRpcCalc;
import com.wondertek.baiying.rpc.netty.api.IRpcHello;
import com.wondertek.baiying.rpc.netty.consumer.proxy.RpcProxy;

/**
 * Created by wd on 2018/9/10.
 */
public class RpcConsumer {

    public static void main(String[] args) {

        IRpcHello rpcHello = RpcProxy.create(IRpcHello.class);

        System.out.println(rpcHello.hello("tom and jerry"));

        IRpcCalc rpcCalc = RpcProxy.create(IRpcCalc.class);

        System.out.println("8+2=" + rpcCalc.add(8, 2));
    }
}
