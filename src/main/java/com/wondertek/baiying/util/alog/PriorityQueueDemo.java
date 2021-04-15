package com.wondertek.baiying.util.alog;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author aaron
 * @since 2021/4/15
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue((Comparator<Integer>) Integer::compareTo);
        queue.add(2);
        queue.add(6);
        queue.add(4);
        while (true) {
            if (queue.size() > 0) {
                System.out.println(queue.poll());
            } else {
                return;
            }
        }
    }
}
