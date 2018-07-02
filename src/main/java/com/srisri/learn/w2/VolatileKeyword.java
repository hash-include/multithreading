package com.srisri.learn.w2;

import java.util.Scanner;


class Processor extends Thread {
    /**
     * Volatile keyword will ensure that the thread running the while loop doesn't cache the value of the variable.
     * Even without volatile keyword, the while loop will check for the current value of running-variable, but in
     * some implementations of JVM this is not the case. To ensure the code runs in all the systems, prefer putting
     * the volatile keyword.
     */
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void shutdown() {
        running = false;
    }
}


public class VolatileKeyword {
    public static void main(String[] args) {
        Processor run1 = new Processor();
        run1.start();

        System.out.println("Press return to shutdown ..");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        run1.shutdown();
    }
}
