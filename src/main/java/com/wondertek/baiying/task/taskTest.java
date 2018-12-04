package com.wondertek.baiying.task;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * Created by wd on 2018/11/21.
 */
public class taskTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-task.xml");
    }

}
