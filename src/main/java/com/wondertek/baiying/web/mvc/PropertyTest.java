package com.wondertek.baiying.web.mvc;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wd on 2018/12/21.
 */
public class PropertyTest {
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("DispatcherServlet.properties", PropertyTest.class.getClassLoader());
        Properties properties = PropertiesLoaderUtils.loadProperties(classPathResource);
        for (Map.Entry<Object, Object> map : properties.entrySet()) {
            Object key = map.getKey();
            Object value = map.getValue();
            System.out.println("key: " + key + ",value: " + value);
        }
        System.out.println(properties.getProperty("org.springframework.web.servlet.HandlerAdapter"));
    }
}
