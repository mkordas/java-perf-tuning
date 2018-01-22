package com.luxoft.jpt.course.benchmark;

public class Fib {
    private static final double nLoops = 1;

    public static void main(String[] args) {
        new Fib().doTest();
    }

    public void doTest() {
        double l;
        long then = System.currentTimeMillis();
        for (int i = 0; i < nLoops; i++) {
            l = fibImpl1(40);
        }
        long now = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (now - then));
    }

    private double fibImpl1(int n) {
        if (n < 0) throw new IllegalArgumentException("Must be > 0");
        if (n == 0) return 0d;
        if (n == 1) return 1d;
        double d = fibImpl1(n - 2) + fibImpl1(n - 1);
        if (Double.isInfinite(d)) throw new ArithmeticException("Overflow");
        return d;
    }
}
