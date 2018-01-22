
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.util;

public final class TestUtil {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static final int MB = 1024 * 1024;

    //J-
    public static final int ITER_10K    =          10 * 1000;
    public static final int ITER_100K   =         100 * 1000;
    public static final int ITER_1M     =        1000 * 1000;
    public static final int ITER_10M    =   10 * 1000 * 1000;
    public static final int ITER_100M   =  100 * 1000 * 1000;
    public static final int ITER_1000M  = 1000 * 1000 * 1000;
    //J+

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    private TestUtil() {
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static double nanoToMsConverter(long time) {
        return time / ITER_1M;
    }

    public static double nanoToSecConverter(long time) {
        return time / ITER_1000M;
    }

    public static void invokeGC() throws InterruptedException {
        System.out.printf("\nInvoking GC ...");
        System.gc();
        Thread.sleep(3000);
        System.out.printf(" done \n\n");
    }

    public static void measureMemoryBefore() {
        measureMemory("Before");
    }

    public static void measureMemoryAfter() {
        measureMemory("After");

    }

    public static void measureMemory(String phase) {
        long totalMemory = Runtime.getRuntime().totalMemory() / MB;
        long freeMemory = Runtime.getRuntime().freeMemory() / MB;
        long maxMemory = Runtime.getRuntime().maxMemory() / MB;

        //J-
        System.out.printf(
            "[%s]: total_memory=[%,d] (MB) \t free_memory=[%,d] (MB) \t max_memory=[%,d] (MB) \t used_memory=[%,d] (MB) \n",
            phase,
            totalMemory,
            freeMemory,
            maxMemory,
            totalMemory - freeMemory
        );
        //J+

    }
}
