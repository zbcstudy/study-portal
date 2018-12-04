package com.wondertek.baiying.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wd on 2018/5/24.
 */
public class TaskWithResult implements Callable {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        return "task with result " + id;
    }

}

class CallableDemo{
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future future = executorService.submit(new TaskWithResult(i));
            results.add(future);
        }
        for (Future<String> result : results) {
            try {
                //获取结果集
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
    }
}
