package com.luxoft.jpt.course.decompile;

import java.util.concurrent.Callable;

public class DisassemblerAmbiguity implements Callable<Double> {
    @Override
    public Double call() {
        return Double.NaN;
    }
}
