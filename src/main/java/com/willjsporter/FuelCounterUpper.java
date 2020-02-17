package com.willjsporter;

import java.util.stream.IntStream;

public class FuelCounterUpper {

    private int totalFuelToCarry;
    //looks like issue is that this is being iterated on
    //the figure was out by an order of magnitude of about 100 so probably need to find a way to reset the totalFuelToCarry to zero for each vessel


    int calculateFuelRequired(int mass) {
        return mass/3 -2;
    }

    public int calculateFuelRequiredForFleet(IntStream intStream) {
        return intStream
            .map(this::invokeTotalFuelCalculation)
            .sum();
    }

    public int calculateTotalFuelRequiredIncludingFuel(int mass) {
        if(calculateFuelRequired(mass) <= 0) {
            return totalFuelToCarry;
        } else {
            totalFuelToCarry += calculateFuelRequired(mass);
            return calculateTotalFuelRequiredIncludingFuel(calculateFuelRequired(mass));
        }
    }

    public int invokeTotalFuelCalculation (int mass) {
        totalFuelToCarry = 0;
        return calculateTotalFuelRequiredIncludingFuel(mass);
    }
}
