package com.wondertek.baiying.concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题
 * Created by wd on 2018/6/25.
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) {
        Integer reference = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();
        System.out.println("reference:" + reference + "============" + "atamp:" + stamp);


        Thread task1 = new Thread(() -> {
            System.out.println("reference:" + reference + "============" + "stamp:" + stamp + "=====" +
                    atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));
            }
        );

        Thread task2 = new Thread(()->{
            Integer reference1 = atomicStampedReference.getReference();
            System.out.println("reference:" + reference1 + "============" + "stamp:" + stamp + "=====" +
                    atomicStampedReference.compareAndSet(reference1, reference1 + 10, stamp, stamp - 1));
            }
        );

        try {
            task1.start();
            task1.join();
            task2.start();
            task2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());

    }
}
