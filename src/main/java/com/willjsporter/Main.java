package com.willjsporter;

import com.willjsporter.util.FileStreamerUtil;

import java.util.List;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
      List<Stream<String>> wireInstructions = FileStreamerUtil.streamFileInputAsDirections("puzzleInput/day3a.txt");
      long start = System.currentTimeMillis();
      Wire wire1 = new Wire(wireInstructions.get(0));
      long mid = System.currentTimeMillis();
      System.out.println("first wire took " + (mid - start) + " ms to load");
      Wire wire2 = new Wire(wireInstructions.get(1));
      long end = System.currentTimeMillis();
      System.out.println("second wire took " + (end - mid) + "ms to load");

      Coordinate intersection = wire1.getIntersectionPointWithLeastSteps(wire2);
      Integer intersectionSteps = wire1.getNumberOfStepsToIntersectionWithLeastSteps(wire2);

      System.out.println("x = " + intersection.getX() + ", y = " + intersection.getY());
      System.out.println("number of steps to reach intersection is: " + intersectionSteps);
      long intersectioncalctime = System.currentTimeMillis();
      System.out.println("intersection working time was " + (intersectioncalctime - end) + "millis");
      System.out.println(intersection.manhattanLength());
    }
}
