package com.wondertek.baiying.concurrent;

/**
 * Created by wd on 2018/6/27.
 */
public class RepastServiceTest {

    public static void main(String[] args) throws InterruptedException {
        RepastService repastService = new RepastService();

        Thread[] arrayP = new Thread[60];
        Thread[] arrayC = new Thread[60];
        for (int i = 0; i < 60; i++) {
            arrayP[i] = new ThreadP(repastService);
            arrayC[i] = new ThreadC(repastService);
        }
        for (int i = 0; i < 60; i++) {
            arrayP[i].start();
            arrayC[i].start();
        }
    }

}
class ThreadP extends Thread {
    private RepastService repastService;

    public ThreadP(RepastService repastService) {
        this.repastService = repastService;
    }

    @Override
    public void run() {
        repastService.set();
    }
}

class ThreadC extends Thread {
    private RepastService repastService;

    public ThreadC(RepastService repastService) {
        this.repastService = repastService;
    }

    @Override
    public void run() {
        repastService.get();
    }
}
