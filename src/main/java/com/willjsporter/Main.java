package com.willjsporter;

import com.willjsporter.intcode.IntcodeBlasterOutputReader;
import com.willjsporter.util.MaxBlasterSequenceOutputUtil;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByKey;

class Main {

    public static void main(String[] args) {
        System.out.println(new MaxBlasterSequenceOutputUtil(new IntcodeBlasterOutputReader("puzzleInput/day7a.txt")).tryAllOrdersAndGetMaxOutput());
//        Output of: 38834 for sequence: 13042
    }
}
