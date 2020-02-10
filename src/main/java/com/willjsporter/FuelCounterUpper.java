package com.willjsporter;

import java.util.stream.IntStream;

public class FuelCounterUpper
{
    static int calculateFuelRequired(int mass) {
        return mass/3 -2;
    }

    public static int calculateFuelRequiredForFleet(IntStream intStream) {
        return intStream
            .map(FuelCounterUpper::calculateFuelRequired)
            .sum();
    }

}
