
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.objectreference;

import java.lang.ref.WeakReference;

import java.util.HashMap;
import java.util.WeakHashMap;

import static com.luxoft.jpt.course.util.TestUtil.invokeGC;


public class WeakReferenceSample {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Creating Weak References");

        WeakReference<String> stringPoolWeakReference = new WeakReference<String>("ABCDEF");

        String stringReference = new String("FEDCBA");
        WeakReference<String> stringWeakReference = new WeakReference<String>(stringReference);

        HashMap<String, String> hashMap = new HashMap<String, String>();
        String keyHashMap = new String("Key");
        hashMap.put(keyHashMap, "QWERTY");

        WeakHashMap<String, String> weakHashMap = new WeakHashMap<String, String>();
        String weakKeyHashMap = new String("WeakKey");
        weakHashMap.put(weakKeyHashMap, "YTREWQ");

        stringReference = null;
        keyHashMap = null;
        weakKeyHashMap = null;

        System.out.println("*** Before GC invocation ***");

        System.out.printf("String Pool WeakReference [%s] \n", stringPoolWeakReference.get());
        System.out.printf("Normal HashMap [%s] \n", hashMap.get("Key"));
        System.out.printf("String WeakReference [%s] \n", stringWeakReference.get());
        System.out.printf("WeakHashMap [%s] \n", weakHashMap.get("WeakKey"));

        invokeGC();

        System.out.println("\n*** After GC invocation ***");

        // WeakReference keeps the value -> String Pool allocation
        System.out.printf("String Pool WeakReference [%s] \n", stringPoolWeakReference.get());
        // Normal HashMap keeps its value
        System.out.printf("Normal HashMap [%s] \n", hashMap.get("Key"));
        // WeakReference becomes null
        System.out.printf("String WeakReference [%s] \n", stringWeakReference.get());
        // WeakHashMap becomes null
        System.out.printf("WeakHashMap [%s] \n", weakHashMap.get("WeakKey"));
    }

}
