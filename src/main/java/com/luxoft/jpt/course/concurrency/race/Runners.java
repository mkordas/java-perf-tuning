
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.concurrency.race;

/**
 * Deadlock example
 */
public class Runners {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        String obstacle1 = new String("obstacle1");
        String obstacle2 = new String("obstacle2");
        String obstacle3 = new String("obstacle3");

        Runner runner1 = new Runner(obstacle1, obstacle2);
        Runner runner2 = new Runner(obstacle2, obstacle3);
        Runner runner3 = new Runner(obstacle3, obstacle1);

        Thread t1 = new Thread(runner1, "Runner-1");
        Thread t2 = new Thread(runner2, "Runner-2");
        Thread t3 = new Thread(runner3, "Runner-3");

        System.out.println("Starting runner's competition ...");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        System.out.println("Waiting for runners to finish competition ...");

        t1.join();
        t2.join();
        t3.join();

        System.out.println("done");
    }
}

class Runner implements Runnable {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private String obstacle1;
    private String obstacle2;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Runner(String o1, String o2) {
        this.obstacle1 = o1;
        this.obstacle2 = o2;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (obstacle1) {
            System.out.printf("[%s] reached at [%s] \n", name, obstacle1);
            work();
            synchronized (obstacle2) {
                System.out.printf("[%s] reached at [%s] \n", name, obstacle2);
                work();
            }
            System.out.printf("[%s] left at [%s] \n", name, obstacle2);
        }
        System.out.printf("[%s] left at [%s] \n", name, obstacle1);

        System.out.printf("[%s] Finished competition ! \n", name);
    }

    private void work() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
