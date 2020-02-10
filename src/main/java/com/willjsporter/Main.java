package com.willjsporter;

import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    IntStream intStream = FileStreamerUtil.fileLinesAsIntegers("puzzleInput/day1a.txt");
    System.out.println(FuelCounterUpper.calculateFuelRequiredForFleet(intStream));
  }
}
