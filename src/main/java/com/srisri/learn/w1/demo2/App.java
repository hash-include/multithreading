package com.srisri.learn.w1.demo2;

import org.apache.log4j.Logger;

public class App {
    private static final Logger log = Logger.getLogger(App.class);

    static class Runner implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                log.info("Hello " + i);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();


    }
}
