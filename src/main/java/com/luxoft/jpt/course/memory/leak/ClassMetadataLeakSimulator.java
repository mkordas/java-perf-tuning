
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.leak;

import java.lang.reflect.Proxy;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import java.util.HashMap;
import java.util.Map;


public class ClassMetadataLeakSimulator {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static Map<String, ClassA> classLeakingMap = new HashMap<String, ClassA>();
    private static final int ITERATIONS = 50000;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws MalformedURLException, InstantiationException {

        // Scenario 1
        // Run application with Java 1.7 with -Xmx1024m -XX:MaxPermSize=56m
        // Run application with Java 1.8 with -Xmx1024m -XX:MaxMetaspaceSize=56m

        // Scenario 2
        // Run application with Java 1.7 with -Xmx1024m
        // Run application with Java 1.8 with -Xmx1024m

        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        ClassMetadataLeakSimulator cms = new ClassMetadataLeakSimulator();
        cms.createMemoryLeak();

        System.out.println("done");
    }

    public void createMemoryLeak() throws MalformedURLException, InstantiationException {

        for (int i = 0; i < ITERATIONS; i++) {

            if ((i % 10000) == 0)
                System.out.printf("Looping over [%,d] iterations; \t Map current size is [%,d] \n", i, classLeakingMap.size());

            String fictiousClassloaderFile = "file:" + ClassAImpl.class.getName() + i + ".class";

            URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderFile) };

            // Create a new Class Loader instance
            URLClassLoader urlClassLoader = new URLClassLoader(fictiousClassloaderURL);

            // Create a new Proxy instance

            ClassA classAImpl = new ClassAImpl(i);
            ClassAInvocationHandler classAInvocationHandler = new ClassAInvocationHandler(classAImpl);

            //J-
            ClassA proxyInstance =
                (ClassA) Proxy.newProxyInstance(
                    urlClassLoader,
                    new Class<?>[] { ClassA.class },
                    new ClassAInvocationHandler(classAInvocationHandler)
                );
            //J+

            // All method invocations on proxyInstance will go through {@link ClassAInvocationHandler}
            //System.out.printf("HashCode for object called through proxy is [%d] \n", proxyInstance.hashCode());

            // Add the new Proxy instance to the leaking HashMap
            classLeakingMap.put(fictiousClassloaderFile, proxyInstance);
        }

    }

}
