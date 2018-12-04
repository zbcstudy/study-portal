package com.wondertek.baiying.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wd on 2018/11/22.
 */
public class QuartzJobDemo implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String name = context.getJobDetail().getKey().getName();
        System.out.println(name);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
    }
}
