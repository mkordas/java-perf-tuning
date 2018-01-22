
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.prodcons;

import static com.luxoft.jpt.course.util.TestUtil.ITER_1M;


public class ProducerConsumerMain {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static final long MILLIS_TO_WAIT = 500L;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {

        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        System.out.println("Starting Producer and Consumer");

        Producer producer = new Producer(ITER_1M);
        Consumer consumer = new Consumer(ITER_1M, producer);

        Thread t = Thread.currentThread();
        t.setName(ProducerConsumerMain.class.getSimpleName());

        Thread t1 = new Thread(producer);
        t1.setName(Producer.class.getSimpleName());

        Thread t2 = new Thread(consumer);
        t2.setName(Consumer.class.getSimpleName());

        t2.start();
        t1.start();

        t1.join();
        t2.join();

        System.out.println("done");
    }

}
