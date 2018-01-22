
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.classloader;

import java.util.Arrays;
import java.util.List;


public class A extends ASuperClass {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final int factor = 2;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final String operation = new String("Operation");
    private final List<Integer> numbers = Arrays.asList(10, 11, 12, 13);

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static int getFactor() {
        return factor;
    }

    public String getOperation() {
        return operation;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
