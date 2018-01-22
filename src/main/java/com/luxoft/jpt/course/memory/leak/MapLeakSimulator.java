
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.leak;

import java.util.HashMap;
import java.util.Map;

import static com.luxoft.jpt.course.util.TestUtil.ITER_1M;
import static com.luxoft.jpt.course.util.TestUtil.invokeGC;
import static com.luxoft.jpt.course.util.TestUtil.measureMemory;


public class MapLeakSimulator {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final String OBJECT_CONTENT = "*** Java Performance And Tuning Course *** ABCDEFGHIJKLMNOPQRSTUVWXYZ *** 1234567890 ***";
    private static final int NUMBER_OF_OBJECTS = ITER_1M;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    public final String key;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public MapLeakSimulator(String key) {
        this.key = key;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        Map<MapLeakSimulator, String> mapLeakSimulator = new HashMap<MapLeakSimulator, String>();

        measureMemory("Before creating HashMap");

        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            // Keep adding "duplicates"
            mapLeakSimulator.put(new MapLeakSimulator("Key"), OBJECT_CONTENT);
        }

        measureMemory("After creating HashMap");

        invokeGC();

        measureMemory("After invoking GC");

        System.out.printf("Created Memory Leak Map with size [%,d] \n", mapLeakSimulator.size());

        // searching for a Key will get a null
        MapLeakSimulator key = new MapLeakSimulator("Key");

        System.out.printf("Leak Map size [%,d] \n", mapLeakSimulator.size());
        System.out.printf("Retrieving [%s] [%s] \n", key, mapLeakSimulator.get(key));
    }

    @Override
    public String toString() {
        return "MemoryLeakSample [key=" + key + "]";
    }

}
