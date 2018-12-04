package com.wondertek.baiying.webservice;

import javax.xml.ws.Endpoint;

/**
 * Created by wd on 2017/12/14.
 */
public class HelloServiceMain {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        Endpoint.publish("http://127.0.0.1/hello",helloService);
        System.out.println("服务暴露成功");
    }

}
