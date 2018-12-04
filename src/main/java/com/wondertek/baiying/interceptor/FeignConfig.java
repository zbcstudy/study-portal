package com.wondertek.baiying.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * feign整合hystrix时，造成cookie信息丢失
 * Created by wd on 2018/6/21.
 */
@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            template.header("Cookie", "SESSION=" + sessionId);
        };
    }
}
