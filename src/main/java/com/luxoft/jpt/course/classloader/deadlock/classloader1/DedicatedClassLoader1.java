
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.classloader.deadlock.classloader1;

import java.net.URL;
import java.net.URLClassLoader;

import com.luxoft.jpt.course.classloader.deadlock.classloader2.DedicatedClassLoader2;


public class DedicatedClassLoader1 extends URLClassLoader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    static ClassLoader classLoader;

    static {
        // Comment this out to disable parallel capability
        // Parallel capability indicates that all instances of your custom class loader are multithread safe
        //registerAsParallelCapable();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public DedicatedClassLoader1(URL[] urls) {
        super(urls);
        classLoader = this;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static ClassLoader getClassLoader() {
        return classLoader;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.contains("AClass") || name.contains("DClass") || name.contains("Object"))
            return super.loadClass(name);
        else {
            try {
                Thread.sleep(1000L); // make the loading more expensive to get deadlock (by delaying for concurrent thread)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return DedicatedClassLoader2.getClassLoader().loadClass(name);
        }
    }
}
