package com.wondertek.baiying.webservice;

import javax.jws.WebService;
import java.util.Date;

/**
 * Created by wd on 2017/12/14.
 */
@WebService(endpointInterface = "com.wondertek.baiying.webservice.HelloService",serviceName = "HelloServiceImpl")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "您好: " + name + " 现在时间是: " + new Date();
    }
}
