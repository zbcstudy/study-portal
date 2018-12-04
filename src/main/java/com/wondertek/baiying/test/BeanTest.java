package com.wondertek.baiying.test;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * Created by wd on 2018/6/7.
 */
public class BeanTest {

    @Test
    public void beanTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        MyTestBean bean = (MyTestBean) applicationContext.getBean("myTestBean");
        bean.test();
    }

    @Test
    public void beanTest2() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource resource = resolver.getResources("classpath:beanFactoryTest.xml")[0];
            DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
            reader.loadBeanDefinitions(resource);
            MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
            myTestBean.test();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
