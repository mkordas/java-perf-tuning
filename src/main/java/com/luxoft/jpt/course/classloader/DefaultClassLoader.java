
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.classloader;

/**
 * Default Class Loader to load and instantiate {@link com.luxoft.jpt.course.classloader.A} object types.
 *
 * @author IBalosin
 */
public class DefaultClassLoader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        System.out.printf("System ClassLoader [%s] \n", ClassLoader.getSystemClassLoader());

        try {
            java.lang.ClassLoader defaultClassLoader = DefaultClassLoader.class.getClassLoader();

            Class<A> aDefaultClass = (Class<A>) defaultClassLoader.loadClass(A.class.getName());
            A aDefaultInstance = (A) aDefaultClass.newInstance();

            System.out.println();

            System.out.printf("Class [%s] was loaded by [%s] \n", A.class.getSimpleName(), A.class.getClassLoader());
            System.out.printf("Class [%s] was re-loaded by [%s] \n", aDefaultClass.getSimpleName(), aDefaultClass.getClassLoader());
            System.out.printf("Class [%s] was loaded by [%s] \n", ASuperClass.class.getSimpleName(), ASuperClass.class.getClassLoader());
            System.out.printf("Class [%s] was loaded by [%s] \n", AInterface.class.getSimpleName(), AInterface.class.getClassLoader());

            System.out.println();

            System.out.printf("Instance [%s] was loaded by [%s] \n", aDefaultInstance, aDefaultInstance.getClass().getClassLoader());
            System.out.printf("Instance [%s] is an instance of A ? [%b] \n", aDefaultInstance, (aDefaultInstance instanceof A));
            System.out.printf("Instance [%s] is an instance of ASuperClass ? [%b] \n", aDefaultInstance, (aDefaultInstance instanceof ASuperClass));
            System.out.printf("Instance [%s] is an instance of AInterface ? [%b] \n", aDefaultInstance, (aDefaultInstance instanceof AInterface));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
