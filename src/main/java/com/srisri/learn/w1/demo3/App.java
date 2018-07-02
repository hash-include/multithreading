package com.srisri.learn.w1.demo3;

import org.apache.log4j.Logger;

public class App {
    private static final Logger log = Logger.getLogger(App.class);


    public static void main(String[] args) {
        // anonymous inner class.
        Thread t1 = new Thread(new Runnable() {
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
        });

        t1.start();

    }
}
