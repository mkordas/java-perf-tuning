
 /**
 *  
 *  Copyright Luxoft - Java Performance And Tuning Course. All Rights Reserved.
 *
 *  Author: Ionut Balosin 
 *  E-mail: ibalosin@luxoft.com
 *
 */
 
package com.luxoft.jpt.course.memory.oome;

/**
 * Computes the n-th Fibonaci element either recursive or iterative to test StackOverFlowError
 *
 * @author IBalosin
 */
public class StackOverFlowError {

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Static fields/initializers 
    //~ ----------------------------------------------------------------------------------------------------------------

    private static final int THE_Nth_FIBO_ELEMENT = 10000;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        StackOverFlowError sofe = new StackOverFlowError();
        System.out.printf("Computing the [%,d] th Fibonacci number ... \n", THE_Nth_FIBO_ELEMENT);

        // With default stack size, it should throw StackOverFlowError
        // Solution: increase the Xss Stack size (e.g. -Xss10m)
        System.out.printf("\t - recursive method = [%,d] \n", sofe.recursiveFibonacci(THE_Nth_FIBO_ELEMENT));

        // With default stack size, it should not throw any StackOverFlowError
        System.out.printf("\t - iterative method = [%,d] \n", sofe.iterativeFibonacci(THE_Nth_FIBO_ELEMENT));

        System.out.println("done");
    }

    private int recursiveFibonacci(int n) {
        if (0 == n)
            return 0;
        else if (1 == n)
            return 1;
        else
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    private int iterativeFibonacci(int n) {
        if (0 == n)
            return 0;
        else if (1 == n)
            return 1;

        int f_n_2 = 0;
        int f_n_1 = 1;
        int f_n = f_n_2 + f_n_1;

        for (int i = 2; i <= n; i++) {
            f_n = f_n_2 + f_n_1;
            f_n_2 = f_n_1;
            f_n_1 = f_n;
        }

        return f_n;
    }

}
