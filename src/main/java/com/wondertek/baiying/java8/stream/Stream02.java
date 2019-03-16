package com.wondertek.baiying.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2019/3/5.
 */
public class Stream02 {

    public static final int MAX = 10000;

    public static void sortSequential() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    public static void sortParallel() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());

        }
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();

        long millis = TimeUnit.MICROSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        sortSequential();
        sortParallel();
    }
}
