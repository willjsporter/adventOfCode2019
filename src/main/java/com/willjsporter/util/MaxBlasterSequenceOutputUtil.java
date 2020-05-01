package com.willjsporter.util;

import com.willjsporter.Coordinate;
import com.willjsporter.intcode.IntcodeBlasterOutputReader;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

public class MaxBlasterSequenceOutputUtil {

    IntcodeBlasterOutputReader intcodeBlasterOutputReader;

    public MaxBlasterSequenceOutputUtil(IntcodeBlasterOutputReader intcodeBlasterOutputReader) {
        this.intcodeBlasterOutputReader = intcodeBlasterOutputReader;
    }

    public Integer tryAllOrdersAndGetMaxOutput() {
        Map<Integer, Integer> blastOutputs = new HashMap<>();

        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                if (b != a) {
                    for (int c = 0; c < 5; c++) {
                        if (c != a && c != b) {
                            for (int d = 0; d < 5; d++) {
                                if (d != a && d != b && d != c) {
                                    for (int e = 0; e < 5; e++) {
                                        if (e != a && e != b && e != c && e != d) {
                                            Coordinate blasterOutputForGivenSequence = intcodeBlasterOutputReader.getBlasterOutputForGivenSequence(a, b, c, d, e);
                                            blastOutputs.put(blasterOutputForGivenSequence.getX(), blasterOutputForGivenSequence.getY());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Integer maxSignal = blastOutputs.entrySet().stream().max(comparingByValue()).map(Map.Entry::getValue).orElse(0);
        return maxSignal;
    }
}
