
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.leak;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ClassAInvocationHandler implements InvocationHandler {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private Object classAImpl;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public ClassAInvocationHandler(Object impl) {
        this.classAImpl = impl;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //System.out.printf("Handler invocation for method [%s] \n", method.getName());

        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy)) + ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }

        return method.invoke(classAImpl, args);
    }
}
