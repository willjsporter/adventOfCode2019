package com.willjsporter;

import com.willjsporter.util.FileStreamerUtil;
import com.willjsporter.util.InputReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Integer> programInput = FileStreamerUtil.streamFileInputAsIntegers("puzzleInput/day5a.txt");
        new IntcodeProgram(programInput, new InputReader(new BufferedReader(new InputStreamReader(System.in)))).run();
    }
}
