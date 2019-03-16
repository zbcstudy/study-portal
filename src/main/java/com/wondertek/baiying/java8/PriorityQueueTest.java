package com.wondertek.baiying.java8;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 优先级队列，topN的java实现 堆排序
 * Created by wd on 2019/3/12.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<String> strings = new PriorityQueue<>();
        strings.add("cde");
        strings.add("abc");
        strings.add("bcd");

        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void sortTest() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        priorityQueue.add(4);
        priorityQueue.add(8);
        priorityQueue.add(1);
        priorityQueue.add(6);

        while (priorityQueue.peek() != null) {
            System.out.println(priorityQueue.poll());
        }
    }
}
