package com.wondertek.baiying.task.schedule;

import com.wondertek.baiying.task.schedule.entity.ScheduleTask;

import java.util.List;
import java.util.Map;

public interface ITaskService {

    /**
     * 获取所有表达式
     * @return
     */
    public Map<String, String> getAllCron();

    /**
     * 获取任务列表
     * @return
     */
    public List<ScheduleTask> getAllTask();

    /**
     * 根据任务ID获取一个任务
     * @param taskId
     * @return
     */
    public ScheduleTask getTaskById(String taskId);

    /**
     * 新建一个任务
     * @param task
     * @return
     */
    ScheduleTask addTask(ScheduleTask task);

    /**
     * 新建一个任务
     *
     * @param taskName      任务名称
     * @param taskClassName 任务class名称
     * @param triggerName   触发器名称
     * @param cron          cron表达式
     * @return
     */
    ScheduleTask addTask(String taskName, String taskClassName, String triggerName, String cron);

    /**
     * 新建一个定时任务
     * @param taskName 任务名称
     * @param taskGroupName 任务组名
     * @param taskClassName 任务类名
     * @param triggerGroupName 触发器组名
     * @param triggerName 触发器方法名
     * @param cron cron表达式
     * @return
     * @throws Exception
     */
    ScheduleTask addTask(String taskName, String taskGroupName, String taskClassName, String triggerGroupName, String
            triggerName, String cron) throws Exception;

    /**
     * 修改一个任务的触发时间
     * @param taskId
     * @param cron
     * @return
     */
    ScheduleTask modifyTaskCron(String taskId, String cron);

    /**
     * 移除一个任务
     * @param taskId
     * @return
     */
    ScheduleTask removeTask(String taskId);

    /**
     * 重启任务
     * @param taskId
     * @return
     */
    ScheduleTask restartTask(String taskId);

    /**
     * 暂停任务
     * @param taskId
     * @return
     */
    ScheduleTask pauseTask(String taskId);

    /**
     * 禁用任务
     * @param taskId
     * @return
     */
    ScheduleTask disableTask(String taskId);

    /**
     * 关闭定时任务
     * @param taskId
     * @return
     */
    ScheduleTask shutdownTask(String taskId);

    /**
     * 启动所有定时任务
     */
    void startAllTask();

    /**
     * 关闭所有定时任务
     */
    void shutDownAllTask();

}
