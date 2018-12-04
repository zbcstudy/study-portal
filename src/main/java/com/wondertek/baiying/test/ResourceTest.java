package com.wondertek.baiying.test;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * Created by wd on 2018/7/18.
 */
public class ResourceTest {

    public static void main(String[] args) {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = patternResolver.getResources("classpath:application.yml");
            for (Resource resource : resources) {
                System.out.println(resource.getDescription());
                System.out.println(IOUtils.toString(resource.getInputStream()));
            }
            System.out.println("--------------------------");
            Resource[] resources1 = patternResolver.getResources("classpath:com/wondertek/baiying/test/*");
            for (Resource resource : resources1) {
                System.out.println(resource.getFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
