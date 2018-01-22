package com.luxoft.jpt.course.infinite;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfiniteAllocation {
    public static void main(String[] args) {
        int iteration = 0;
        while (true) {
            long start = System.currentTimeMillis();
            Map<Integer, ArrayList> map = new ConcurrentHashMap<>();
            for (int i = 0; i < 200_000; i++) {
                map.put(i, new ArrayList<>(1));

            }
            System.out.printf("%d iteration done, took %d ms, added size: %d%n",
                ++iteration, System.currentTimeMillis() - start, map.size());
        }
    }
}
