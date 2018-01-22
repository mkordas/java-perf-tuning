
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.classloader.deadlock;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import com.luxoft.jpt.course.classloader.deadlock.classloader1.DedicatedClassLoader1;
import com.luxoft.jpt.course.classloader.deadlock.classloader2.DedicatedClassLoader2;


public class DeadlockClassLoader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final String CURRENT_PATH = new File(".").getAbsolutePath();

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        // Run scenario with Java 1.8 and registerAsParallelCapable() disabled
        // Run scenario with Java 1.8 and registerAsParallelCapable() enabled

        //J-
        DedicatedClassLoader1 dedicatedClassLoader1 =
            new DedicatedClassLoader1(new URL[] { new URL("file://" + CURRENT_PATH + "/external/generated-classes/classloader1/") });
        DedicatedClassLoader2 dedicatedClassLoader2 =
            new DedicatedClassLoader2(new URL[] { new URL("file://" + CURRENT_PATH + "/external/generated-classes/classloader2/") });
        //J+

        Thread t1 = new Thread(new ClassLoaderWorker(dedicatedClassLoader1, "com.luxoft.jpt.course.classloader1.AClass"), "ClassLoaderWorker-0");
        Thread t2 = new Thread(new ClassLoaderWorker(dedicatedClassLoader2, "com.luxoft.jpt.course.classloader2.CClass"), "ClassLoaderWorker-1");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class ClassLoaderWorker implements Runnable {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private ClassLoader classLoader;
    private String className;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    ClassLoaderWorker(ClassLoader classLoader, String className) {
        this.classLoader = classLoader;
        this.className = className;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public void run() {
        try {
            System.out.printf("Loading class [%s] with [%s] \n", className, classLoader.getClass().getName());
            classLoader.loadClass(className);
            System.out.printf("Loaded class [%s] \n", className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
