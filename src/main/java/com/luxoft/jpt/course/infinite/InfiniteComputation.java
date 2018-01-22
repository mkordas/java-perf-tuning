package com.luxoft.jpt.course.infinite;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfiniteComputation {

    private static volatile BigInteger result;

    public static void main(String[] args) {
        int iteration = 0;
        while (true) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1_000_000; i++) {
                result = sqrt(new BigInteger("1234567890"));

            }
            System.out.printf("%d iteration done, took %d ms, result: %s%n",
                ++iteration, System.currentTimeMillis() - start, result.toString());
        }
    }

    private static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        while (true) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }
}
