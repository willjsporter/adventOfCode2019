package com.willjsporter;

import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
      long start = System.currentTimeMillis();
      System.out.println(IntStream.rangeClosed(273025, 767253).filter(PasswordCracker::checkPassword).count());
      long end = System.currentTimeMillis();
      System.out.println("solution took " + (end - start) + " ms to load");
    }
}
