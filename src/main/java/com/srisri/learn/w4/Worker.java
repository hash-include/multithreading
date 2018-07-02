package com.srisri.learn.w4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private List<Integer> l1 = new ArrayList<Integer>();
    private List<Integer> l2 = new ArrayList<Integer>();


    private synchronized void stage1() {
        /*
         * The system will produce WRONG results if "synchronized" keyword is not used. As two threads are calling the
         * process method.
         */
       stageLogic(l1);
    }

    private void stage1_correctLocking() {
        /*
         * Efficient and correct way of locking as stage2 is independent of stage1. The synchronized stage1 is not
         * efficient as it will lock on Worker.class thus no thread though independent can run other stage when this
         * stage is locked.
         */
        synchronized (lock1) {
            stageLogic(l1);
        }
    }



    private synchronized void stage2() {
        stageLogic(l2);
    }

    private void stage2_correctLocking() {
        synchronized (lock2) {
            stageLogic(l2);
        }
    }

    private void stageLogic(List<Integer> list) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(random.nextInt(100));
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
//            stage1();
//            stage2();
            stage1_correctLocking();
            stage2_correctLocking();
        }
    }

    class Runner implements Runnable {
        public void run() {
            process();
        }
    }

    public void main() {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("time taken: " + (System.currentTimeMillis() - start));
        System.out.println("len(l1) : " + l1.size() + " | len(l2) : " + l2.size());
    }
}
