package com.wondertek.baiying.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class myJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.err.println(System.currentTimeMillis() + "  do something");

    }
}
