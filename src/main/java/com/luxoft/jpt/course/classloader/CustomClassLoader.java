
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.classloader;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * Custom Class Loader to load and instantiate {@link com.luxoft.jpt.course.classloader.A} object types. It also creates
 * two object instances and compares them (instanceof) against object type itself. Besides that, another instance of the
 * same type is loaded using Parent Class Loader.
 *
 * @author IBalosin
 */
public class CustomClassLoader extends java.lang.ClassLoader {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    @SuppressWarnings({ "unchecked", "unused" })
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        System.out.printf("Running application with Java version [%s] \n", System.getProperty("java.version"));

        System.out.printf("System ClassLoader [%s] \n", ClassLoader.getSystemClassLoader());

        // Run scenario with -verbose:class

        try {
            CustomClassLoader customClassLoader = new CustomClassLoader(CustomClassLoader.class.getClassLoader());

            Class<A> aCustomClass = (Class<A>) customClassLoader.loadClass(A.class.getName());
            AInterface aCustomInstance = (AInterface) aCustomClass.newInstance();

            System.out.println();

            System.out.printf("Class [%s] was loaded by [%s] \n", A.class.getSimpleName(), A.class.getClassLoader());
            System.out.printf("Class [%s] was re-loaded by [%s] \n", aCustomClass.getSimpleName(), aCustomClass.getClassLoader());
            System.out.printf("Class [%s] was loaded by [%s] \n", ASuperClass.class.getSimpleName(), ASuperClass.class.getClassLoader());
            System.out.printf("Class [%s] was loaded by [%s] \n", AInterface.class.getSimpleName(), AInterface.class.getClassLoader());

            System.out.println();

            System.out.printf("Instance [%s] was loaded by [%s] \n", aCustomInstance, aCustomInstance.getClass().getClassLoader());
            System.out.printf("Instance [%s] is an instance of A ? [%b] \n", aCustomInstance, (aCustomInstance instanceof A));
            System.out.printf("Instance [%s] is an instance of ASuperClass ? [%b] \n", aCustomInstance, (aCustomInstance instanceof ASuperClass));
            System.out.printf("Instance [%s] is an instance of AInterface ? [%b] \n", aCustomInstance, (aCustomInstance instanceof AInterface));

            // !!! Throws {@link java.lang.ClassCastException} !!!
            A aNewInstance = (A) aCustomClass.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Class loadClass(String classIdentifier) throws ClassNotFoundException {
        // Delegate Class Loading to super Class Loader if class identifier is
        // different of {@link com.luxoft.jpt.course.classloader.A}
        if (!A.class.getName().equals(classIdentifier)) {
            //System.out.printf("Loading [%s] with System ClassLoader \n", classIdentifier);
            return super.loadClass(classIdentifier);
        }

        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile("out/production/classes/" +
                A.class.getName().replaceAll("\\.", "\\/") + ".class", "r");
            byte[] b = new byte[(int) f.length()];
            f.read(b);
            //System.out.printf("Loading [%s] with CustomClassLoader \n", classIdentifier);
            // NB: ensure that the protected defineClass() method is called only
            // once for each class loader and class name pair
            return defineClass(A.class.getName(), b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != f)
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.printf("======== Loading [%s] with parent ClassLoader \n", name);
        return super.findClass(name);
    }

}
