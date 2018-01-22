
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.oome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Allocates many objects on PermGen to test OutOfMemoryError: PermGen space OBS: This solution works on JDK versions
 * older than 1.7. Starting with JDK 1.7 String internals are represented in Heap
 *
 * @author IBalosin
 */
public class PermGenOutOfMemoryError {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final String CHARS = "*** Java Performance And Tuning Course *** ABCDEFGHIJKLMNOPQRSTUVWXYZ *** 1234567890 ***";
    private static final int NUMBER_OF_OBJECTS = 99999999;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        PermGenOutOfMemoryError pgoome = new PermGenOutOfMemoryError();
        System.out.printf("Allocating [%,d] objects on PermGen ...  \n", NUMBER_OF_OBJECTS);

        // With default PermGen size, it should throw OutOfMemoryError: PermGen space
        // Solution: increase the -XX:MaxPermSize PermGen size (e.g. -XX:MaxPermSize=2056m)
        pgoome.throwsOutOfMemoryError();

        System.out.println("done");
    }

    private void throwsOutOfMemoryError() {
        Random rnd = new Random();
        List<String> interned = new ArrayList<String>();
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {

            if ((i % 100000) == 0)
                System.out.printf("\t allocated [%,d] objects \n", i);

            int length = rnd.nextInt(100);
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < length; j++) {
                builder.append(CHARS.charAt(rnd.nextInt(CHARS.length())));
            }

            interned.add(builder.toString().intern());
        }
    }

}
