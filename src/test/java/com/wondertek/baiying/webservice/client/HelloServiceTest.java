package com.wondertek.baiying.webservice.client;

/**
 * Created by wd on 2017/12/14.
 */
public class HelloServiceTest {
    public static void main(String[] args) {
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        HelloService helloService = helloServiceImpl.getHelloServiceImplPort();
        String result = helloService.sayHello("赵必成");
        System.out.println(result);
    }


}
