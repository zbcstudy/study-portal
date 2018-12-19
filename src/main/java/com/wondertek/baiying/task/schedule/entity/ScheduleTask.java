package com.wondertek.baiying.task.schedule.entity;

public class ScheduleTask {

    private String id;
    private String parentId = ""; //父任务id
    private String name = ""; //任务名称
    private String desc = ""; //任务描述
    private int planExec; //计划执行次数，默认为0，表示满足条件循环执行
    private String group = ""; //任务组名称
    private String groupDesc = ""; //任务组描述
    private String cron = ""; //任务表达式
    private String cronDesc = ""; //表达式描述
    private String trigger = ""; //触发器
    private String triggerDesc = ""; //触发器描述
    //    private String triggerGroup = ""; //触发器组
//    private String triggerGroupDesc = ""; //触发器组描述
    private int exec = 0; //任务被执行过多少次
    private Long lastExeTime = 0l; //最后一次开始执行时间
    private Long lastFinishTime = 0l; //最后一次执行完成时间
    private int state = 1; //任务状态 0：禁用 1启动 2 删除
    private int deply = 0; //延时启动默认为0，表示不延时启动

    private ScheduleTask(String taskId) {
        this.id = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPlanExec() {
        return planExec;
    }

    public void setPlanExec(int planExec) {
        this.planExec = planExec;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getCronDesc() {
        return cronDesc;
    }

    public void setCronDesc(String cronDesc) {
        this.cronDesc = cronDesc;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getTriggerDesc() {
        return triggerDesc;
    }

    public void setTriggerDesc(String triggerDesc) {
        this.triggerDesc = triggerDesc;
    }

    public int getExec() {
        return exec;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public Long getLastExeTime() {
        return lastExeTime;
    }

    public void setLastExeTime(Long lastExeTime) {
        this.lastExeTime = lastExeTime;
    }

    public Long getLastFinishTime() {
        return lastFinishTime;
    }

    public void setLastFinishTime(Long lastFinishTime) {
        this.lastFinishTime = lastFinishTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDeply() {
        return deply;
    }

    public void setDeply(int deply) {
        this.deply = deply;
    }
}
