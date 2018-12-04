package com.wondertek.baiying.webservice;

import javax.jws.WebService;

/**
 * Created by wd on 2017/12/14.
 */
@WebService
public interface HelloService {
    String sayHello(String name);
}
