package com.wondertek.baiying.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * RecursiveAction 没有返回结果
 * Created by wd on 2017/9/29.
 */
public class RecursiveActionTest extends RecursiveAction {

    private long workLoad;

    public RecursiveActionTest(long workLoad){
        this.workLoad = workLoad;
    }
    @Override
    protected void compute() {
        if (this.workLoad > 16){
            System.out.println("splitting workLoad: " + this.workLoad);

            List<RecursiveActionTest> subTasks = new ArrayList<RecursiveActionTest>();
            subTasks.addAll(createSubTask());

            for (RecursiveAction subTask : subTasks) {
                subTask.fork();
            }
        }else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<? extends RecursiveActionTest> createSubTask() {
        List<RecursiveActionTest> subTasks = new ArrayList<RecursiveActionTest>();

        RecursiveActionTest subTask1 = new RecursiveActionTest(this.workLoad / 2);
        RecursiveActionTest subTask2 = new RecursiveActionTest(this.workLoad / 2);

        subTasks.add(subTask1);
        subTasks.add(subTask2);
        return subTasks;
    }
}
