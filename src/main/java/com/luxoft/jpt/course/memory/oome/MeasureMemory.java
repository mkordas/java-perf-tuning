
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.oome;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.luxoft.jpt.course.util.TestUtil.ITER_1M;
import static com.luxoft.jpt.course.util.TestUtil.measureMemoryAfter;
import static com.luxoft.jpt.course.util.TestUtil.measureMemoryBefore;


/**
 * Measure memory used by one million Integers Objects
 *
 * @author IBalosin
 */
public class MeasureMemory {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final int NUMBER_OF_OBJECTS = ITER_1M;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        MeasureMemory mm = new MeasureMemory();

        measureMemoryBefore();

        System.out.printf("Allocating [%,d] objects on Heap ...\n", NUMBER_OF_OBJECTS);
        mm.allocateObjects();
        System.out.println("done");

        measureMemoryAfter();

    }

    private void allocateObjects() {
        Random rnd = new Random();
        Set<Integer> list = new HashSet<Integer>();
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            if ((i % 1000) == 0)
                System.out.printf("Allocated [%,d] objects; \t list size is [%,d]\n", i, list.size());
            list.add(new Integer(rnd.nextInt(NUMBER_OF_OBJECTS)));
        }
    }
}
