package com.luxoft.jpt.course.reallife;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpacesAround {
    private static volatile List<String> result;

    public static void main(String[] args) {
        while (true) {
            result = Stream.of("value1=1", "value2=2", "value3=3")
                .map(s -> s.replaceAll("(.*)=(.*)", "$1 = $2"))
                .collect(Collectors.toList());
        }
    }
}
