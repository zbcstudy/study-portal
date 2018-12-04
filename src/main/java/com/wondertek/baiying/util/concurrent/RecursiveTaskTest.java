package com.wondertek.baiying.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask 有返回值
 * Created by wd on 2017/9/29.
 */
public class RecursiveTaskTest extends RecursiveTask {

    private long workLoad;

    public RecursiveTaskTest(long workLoad){
        this.workLoad = workLoad;
    }
    @Override
    protected Long compute() {
        if (this.workLoad > 16){
            System.out.println("splitting workLoad: " + this.workLoad);

            List<RecursiveTaskTest> subTasks = new ArrayList<RecursiveTaskTest>();
            subTasks.addAll(createSubTask());

            for (RecursiveTaskTest subTask : subTasks) {
                subTask.fork();
            }

            long result = 0;
            for(RecursiveTaskTest subTask : subTasks) {
                Object join = subTask.join();
                result += Long.getLong(join.toString());
            }
            return result;
        }else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;

        }
    }

    private List<? extends RecursiveTaskTest> createSubTask() {
        List<RecursiveTaskTest> subTasks = new ArrayList<RecursiveTaskTest>();

        RecursiveTaskTest subTask1 = new RecursiveTaskTest(this.workLoad / 2);
        RecursiveTaskTest subTask2 = new RecursiveTaskTest(this.workLoad / 2);

        subTasks.add(subTask1);
        subTasks.add(subTask2);
        return subTasks;
    }
}
