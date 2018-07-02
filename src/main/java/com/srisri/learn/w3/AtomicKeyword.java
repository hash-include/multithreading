package com.srisri.learn.w3;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicKeyword {
    private static final boolean runBuggy = false;

    private int count = 0;
    private AtomicInteger countAtomic = new AtomicInteger(0);

    public static void main(String[] args) {
        AtomicKeyword app = new AtomicKeyword();
        app.doWork();
    }

    private void doWork() {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            // Will wait for the threads to execute and join the caller thread: "main" in this specific case.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (runBuggy) {
            System.out.println("Count is: " + count);
        } else {
            System.out.println("Count is: " + countAtomic.get());
        }

    }

    class Runner implements Runnable {
        public void run() {
            for (int i = 0; i < 10000; i++) {
                if (runBuggy) {
                    count++;
                    /* Buggy: as count = count + 1 has three internal operations:
                           1. Read the value of count
                           2. Increement the value by 1
                           3. Overwrite the value of count with new value.

                       Because of thread interleaving, count value not being atomic will have inconsistent values.

                       So the solution is
                       a) Either to use "atomic" keyword for the simple int variable. But is not a general solution
                       for complex operations.
                       b) Use a synchrnoized method to perform the increment operation. Sync internally used a mutex
                       lock to make sure the thread in use does the complete work before other thread can use the
                       resource.

                     */
                } else {
                    countAtomic.getAndAdd(1);
                }
            }
        }
    }
}
