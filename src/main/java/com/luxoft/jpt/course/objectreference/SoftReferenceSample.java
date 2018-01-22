
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.objectreference;

import java.lang.ref.SoftReference;

import java.util.ArrayList;
import java.util.HashMap;

import static com.luxoft.jpt.course.util.TestUtil.invokeGC;


public class SoftReferenceSample {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Creating Soft References");

        SoftReference<String> stringPoolSoftReference = new SoftReference<String>("ABCDEF");

        SoftReference<String> stringHeapSoftReference = new SoftReference<String>(new String("FEDCBA"));

        HashMap<String, SoftReference<String>> hashMap = new HashMap<String, SoftReference<String>>();
        String keyHashMap = new String("Key");
        hashMap.put(keyHashMap, new SoftReference<String>(new String("QWERTY")));

        System.out.println("*** Before GC invocation ***");

        System.out.printf("String Pool SoftReference [%s] \n", stringPoolSoftReference.get());
        System.out.printf("String Heap SoftReference [%s] \n", stringHeapSoftReference.get());
        System.out.printf("HashMap with SoftReference [%s] \n", hashMap.get("Key").get());

        invokeGC();

        System.out.println("\n*** After GC invocation ***\n");

        // SoftReference keeps the value -> String Pool allocation
        System.out.printf("String Pool SoftReference [%s] \n", stringPoolSoftReference.get());
        // SoftReference keeps the value
        System.out.printf("String Heap SoftReference [%s] \n", stringHeapSoftReference.get());
        // Normal HashMap keeps its value
        System.out.printf("HashMap with SoftReference [%s] \n", hashMap.get("Key").get());

        System.out.println("\n*** Simulating OOME ... ***\n");

        simulateOutOfMemoryError();

        System.out.println("\n*** After OOME was thrown ***\n");

        // SoftReference keeps the value -> String Pool allocation
        System.out.printf("String Pool SoftReference [%s] \n", stringPoolSoftReference.get());
        // SoftReference becomes null (GC cleaned up this SoftReferences)
        System.out.printf("String Heap SoftReference [%s] \n", stringHeapSoftReference.get());
        // Normal HashMap becomes null (GC cleaned up all SoftReference values)
        System.out.printf("HashMap with SoftReference [%s] \n", hashMap.get("Key").get());
    }

    private static void simulateOutOfMemoryError() throws InterruptedException {
        try {
            final ArrayList<Object[]> allocations = new ArrayList<Object[]>();
            while (true)
                allocations.add(new Object[(int) Runtime.getRuntime().maxMemory()]);
        } catch (OutOfMemoryError e) {
            System.out.printf("Great, we have got an [%s] ! \t GC will clean all [%s] (s) ! \n", OutOfMemoryError.class.getSimpleName(), SoftReference.class.getName());
        }
        Thread.sleep(2000);
    }

}
