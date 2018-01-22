package com.luxoft.jpt.course.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FineLogging {

    private static final Logger LOG = Logger.getLogger("");

    public static void main(String[] args) {
        LOG.log(Level.FINE,
            "I am here, and the value of X is {0} and Y is {1}",
            new Object[]{calcX(), calcY()});
    }

    private static double calcY() {
        return Math.cbrt(100);
    }

    private static double calcX() {
        return Math.pow(2, 100);
    }

}
