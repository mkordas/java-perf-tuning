
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.oome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Allocates many objects on Heap to test OutOfMemoryError: Java heap space
 */
public class HeapOutOfMemoryError {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final String OBJECT_CONTENT = "*** Java Performance And Tuning Course *** ABCDEFGHIJKLMNOPQRSTUVWXYZ *** 1234567890 ***";
    private static final int NUMBER_OF_OBJECTS = 9999999;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        HeapOutOfMemoryError oome = new HeapOutOfMemoryError();
        System.out.printf("Allocating [%,d] objects on Heap ...  \n", NUMBER_OF_OBJECTS);

        // With default heap size, it should throw OutOfMemoryError: Java heap space
        // Solution: increase the -Xmx Heap size (e.g. -Xmx6024m)
        // -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap_dump1.hprof
        // -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap_dump1.hprof -Xrunhprof:heap=sites,cpu=samples,depth=10,monitor=y,thread=y,doe=y
        oome.throwsOutOfMemoryError();

        // With default heap size, it should not throw any OutOfMemoryError: Java heap space
        oome.doNotThrowOutOfMemoryError();

        System.out.println("done");
    }

    private void throwsOutOfMemoryError() {
        List<String> list = new ArrayList<String>(NUMBER_OF_OBJECTS);
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            if ((i % 1000000) == 0)
                System.out.printf("\t allocated [%,d] objects; \t list size is [%,d] \n", i, list.size());
            list.add(new String(OBJECT_CONTENT));
        }
    }

    private void doNotThrowOutOfMemoryError() {
        Set<String> list = new HashSet<String>();
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            if ((i % 1000) == 0)
                System.out.printf("\t allocated [%,d] objects; \t list size is [%,d] \n", i, list.size());
            list.add(new String(OBJECT_CONTENT));
        }
    }

}
