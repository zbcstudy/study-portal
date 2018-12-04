package com.wondertek.baiying.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by wd on 2017/9/4.
 */
public class QuartzTest {

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobBuilder jobBuilder = JobBuilder.newJob(myJob.class);
            JobDetail jobDetail = jobBuilder.build();

            TriggerBuilder<Trigger> trigger = TriggerBuilder.newTrigger();
            trigger.forJob(jobDetail);
            trigger.startNow();
            trigger.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5));  //每隔5秒钟执行一次
            //trigger.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(15,46));   //在每天的特定时间执行 14:46
            scheduler.scheduleJob(jobDetail,trigger.build());
            scheduler.start();
            System.out.println("主线程任务执行");
            System.out.println(1<<4);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

