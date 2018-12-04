package com.wondertek.baiying.concurrent.threadPool;

/**
 * Created by wd on 2018/6/28.
 */
public class DenyPolicyException extends RuntimeException{

    public DenyPolicyException(String s) {
        super(s);
    }
}
