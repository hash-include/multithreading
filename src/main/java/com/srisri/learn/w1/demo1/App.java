package com.srisri.learn.w1.demo1;

import org.apache.log4j.Logger;


public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static class Runner extends Thread {
        @Override
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
        Runner run1 = new Runner();
        run1.start();


        Runner run2 = new Runner();
        run2.start();

    }

}
