package com.wondertek.baiying.task.schedule;

import com.wondertek.baiying.task.schedule.entity.ScheduleTask;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService implements ITaskService {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;


    @Override
    public Map<String, String> getAllCron() {
        return null;
    }

    @Override
    public List<ScheduleTask> getAllTask() {
        return null;
    }

    @Override
    public ScheduleTask getTaskById(String taskId) {
        return null;
    }

    @Override
    public ScheduleTask addTask(ScheduleTask task) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        CronTrigger cronTrigger = new CronTriggerImpl();

        try {
            scheduler.scheduleJob(cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ScheduleTask addTask(String taskName, String taskClassName, String triggerName, String cron) {
        return null;
    }

    @Override
    public ScheduleTask addTask(String taskName, String taskGroupName, String taskClassName, String triggerGroupName, String triggerName, String cron) throws Exception {
        return null;
    }

    @Override
    public ScheduleTask modifyTaskCron(String taskId, String cron) {
        return null;
    }

    @Override
    public ScheduleTask removeTask(String taskId) {
        return null;
    }

    @Override
    public ScheduleTask restartTask(String taskId) {
        return null;
    }

    @Override
    public ScheduleTask pauseTask(String taskId) {
        return null;
    }

    @Override
    public ScheduleTask disableTask(String taskId) {
        return null;
    }

    @Override
    public ScheduleTask shutdownTask(String taskId) {
        return null;
    }

    @Override
    public void startAllTask() {

    }

    @Override
    public void shutDownAllTask() {

    }
}
