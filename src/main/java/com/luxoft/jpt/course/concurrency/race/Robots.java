
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
 * Livelock example
 */
public class Robots {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {

        final Robot whiteRobot = new Robot("White Robot");
        final Robot blackRobot = new Robot("Black Robot");

        final Corridor corridor = new Corridor(whiteRobot);

        Runnable runnable1 = new Runnable() {
            public void run() {
                whiteRobot.launch(corridor, blackRobot);
            }
        };

        Runnable runnable2 = new Runnable() {
            public void run() {
                blackRobot.launch(corridor, whiteRobot);
            }
        };

        Thread t1 = new Thread(runnable1, "Robot-1");
        Thread t2 = new Thread(runnable2, "Robot-2");

        System.out.println("Starting robots's competition ...");

        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println("Waiting for robots to finish competition ...");

        t1.join();
        t2.join();
    }
}

class Corridor {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private Robot owner;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Corridor(Robot d) {
        owner = d;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Robot getOwner() {
        return owner;
    }

    public synchronized void setOwner(Robot d) {
        owner = d;
    }

    public synchronized void gotIn() {
        System.out.printf("[%s] got inside [corridor] !", owner.getName());
    }
}

class Robot {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private String name;
    private boolean hasWalkedThrough;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Robot(String n) {
        name = n;
        hasWalkedThrough = false;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public boolean hasWalkedThrough() {
        return hasWalkedThrough;
    }

    public void launch(Corridor corridor, Robot robot) {

        System.out.printf("[%s] reached at [corridor] \n", name);

        while (!hasWalkedThrough()) {

            // Cannot get in, so wait patiently for the other robot which is the owner
            if (corridor.getOwner() != this) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    continue;
                }
                continue;
            }

            // If one robot can get in, insist upon passing the permission to the other robot
            if (!hasWalkedThrough()) {
                //System.out.printf("[%s]: Could you please go first %s !?\n", name, robot.getName());
                corridor.setOwner(robot);
                continue;
            }

            // The Robot finally got inside, he is walking through the corridor
            corridor.gotIn();
            hasWalkedThrough = true;
            System.out.printf("[%s] got in [%s]! \n", name, robot.getName());
            corridor.setOwner(robot);
        }
    }
}
