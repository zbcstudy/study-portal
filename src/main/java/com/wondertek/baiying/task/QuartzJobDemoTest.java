package com.wondertek.baiying.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by wd on 2018/11/22.
 */
public class QuartzJobDemoTest {

    public static void main(String[] args) throws SchedulerException {
        //任务调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //jobdatail(任务实例)
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobDemo.class).withIdentity("quartzJob","quartzJobGroup").build();

        //trigger(触发器)
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("quartzJobTrigger", "TriggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
