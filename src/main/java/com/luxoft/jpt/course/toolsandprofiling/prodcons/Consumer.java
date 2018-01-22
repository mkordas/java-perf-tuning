
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.prodcons;

import java.util.concurrent.TimeUnit;

import static com.luxoft.jpt.course.toolsandprofiling.prodcons.ProducerConsumerMain.MILLIS_TO_WAIT;


public class Consumer extends Thread {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private int iterations;
    private Producer producer;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    Consumer(int iterations, Producer prod) {
        this.iterations = iterations;
        this.producer = prod;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {

        for (int i = 0; i < iterations; i++) {
            consume();
        }

    }

    private void consume() {
        try {
            System.out.printf("\t - consuming [%s] \n", producer.getQueue().poll(10, TimeUnit.SECONDS));
            Thread.sleep(MILLIS_TO_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
