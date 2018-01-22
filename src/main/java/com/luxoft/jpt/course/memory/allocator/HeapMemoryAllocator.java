
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.allocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.luxoft.jpt.course.util.TestUtil.ITER_10M;
import static com.luxoft.jpt.course.util.TestUtil.nanoToSecConverter;


public class HeapMemoryAllocator {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final int NUMBER_OF_OBJECTS = ITER_10M;
    private static final int ITERATIONS = 28;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unused")
    private List<Coordinate> list;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        HeapMemoryAllocator ma = new HeapMemoryAllocator();

        // Run with JVM arguments:
        // -XX:+PrintGC
        // -XX:+PrintGCDetails
        // -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps

        // -XX:+UseSerialGC
        // -XX:+UseParallelGC
        // -XX:+UseParallelOldGC
        // -XX:+UseConcMarkSweepGC
        // -XX:+UseG1GC

        // -XX:+AlwaysTenure
        // -XX:+NeverTenure
        // -Xms1280m -Xmx1280m

        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        final long start = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            ma.list = ma.allocateObjects(i);
            //Thread.sleep(200L);
        }

        final long end = System.nanoTime();

        System.out.printf("Elapsed time=[%,f] sec \n", nanoToSecConverter(end - start));
    }

    private List<Coordinate> allocateObjects(int iteration) {
        Random rnd = new Random();

        System.out.printf("[%d] Allocating [%,d] objects ...", iteration, NUMBER_OF_OBJECTS);

        List<Coordinate> list = new ArrayList<Coordinate>(NUMBER_OF_OBJECTS);
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            list.add(new Coordinate(rnd.nextInt(NUMBER_OF_OBJECTS), rnd.nextInt(NUMBER_OF_OBJECTS)));
        }

        System.out.printf(" done \n");

        // return null to minimize full GC cycles
        return list;
    }

}
