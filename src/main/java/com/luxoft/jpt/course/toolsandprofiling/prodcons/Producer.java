
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.toolsandprofiling.prodcons;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.luxoft.jpt.course.toolsandprofiling.prodcons.Item.MAXIMUM_PRICE;
import static com.luxoft.jpt.course.toolsandprofiling.prodcons.Item.MAXIMUM_QUANTITY;
import static com.luxoft.jpt.course.toolsandprofiling.prodcons.ProducerConsumerMain.MILLIS_TO_WAIT;


public class Producer extends Thread {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private int iterations;
    private BlockingQueue<Item> queue;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Producer(int iterations) {
        this.iterations = iterations;
        this.queue = new ArrayBlockingQueue<Item>(500);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {

        Random rnd = new Random();

        for (int i = 0; i <= iterations; i++) {
            produce(rnd);
        }

    }

    public BlockingQueue<Item> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Item> queue) {
        this.queue = queue;
    }

    private void produce(Random rnd) {
        try {
            Item item = new Item(UUID.randomUUID().toString(), "item", rnd.nextInt(MAXIMUM_PRICE), rnd.nextInt(MAXIMUM_QUANTITY));
            UUID.randomUUID();
            System.out.printf("\t + producing [%s] \n", item);
            queue.put(item);
            Thread.sleep(MILLIS_TO_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
