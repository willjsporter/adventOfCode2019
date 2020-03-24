package com.willjsporter;

import com.willjsporter.util.FileStreamerUtil;

import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Stream<String> programInput = FileStreamerUtil.streamFileLines("puzzleInput/day6a.txt");
        System.out.println(new OrbitCalculator(programInput).calculateOrbitalTransfers("SAN", "YOU"));
    }
}
