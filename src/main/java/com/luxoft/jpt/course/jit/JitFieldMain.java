package com.luxoft.jpt.course.jit;

public class JitFieldMain {

    private int one = 1;
    private int two = 2;

    public static void main(String[] args) {
        new JitFieldMain().run();
    }

    public int getThree() {
        return one + two;
    }

    private void run() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 200_000; i++) {
            sum += one + getThree();
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Took " + elapsed + " ms to compute sum: " + sum);
    }
}
