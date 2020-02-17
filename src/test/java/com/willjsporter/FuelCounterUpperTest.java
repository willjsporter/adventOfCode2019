package com.willjsporter;


import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FuelCounterUpperTest {

    private FuelCounterUpper fuelCounterUpper;

    @Before
    public void setup() {
        fuelCounterUpper = new FuelCounterUpper();
    }

    @Test
    public void givenModuleHasMass12_ThenRequiredFuelShouldBe2()
    {
        assertThat(fuelCounterUpper.calculateFuelRequired(12), is(2));
    }

    @Test
    public void givenModuleHasMass14_ThenRequiredFuelShouldBe2()
    {
        assertThat(fuelCounterUpper.calculateFuelRequired(14), is(2));
    }

    @Test
    public void givenModuleHasMass1969_ThenRequiredFuelShouldBe654()
    {
        assertThat(fuelCounterUpper.calculateFuelRequired(1969), is(654));
    }

    @Test
    public void givenModuleHasMass100756_ThenRequiredFuelShouldBe654()
    {
        assertThat(fuelCounterUpper.calculateFuelRequired(100756), is(33583));
    }

    @Test
    public void givenMassIs14_ThenFuelToCarryFuelShouldBe0 () {
        assertThat(fuelCounterUpper.calculateTotalFuelRequiredIncludingFuel(14), is(2));
    }

    @Test
    public void givenMassIs1969_ThenFuelToCarryFuelShouldBe302 () {
        assertThat(fuelCounterUpper.calculateTotalFuelRequiredIncludingFuel(1969), is(966));
    }

    @Test
    public void givenMassIs100756_ThenFuelToCarryFuelShouldBe50346 () {
        assertThat(fuelCounterUpper.calculateTotalFuelRequiredIncludingFuel(100756), is(50346));
    }

    @Test
    public void shouldBeAbleToSumMultipleFuelRequirements() {
        int actualFuelRequired = fuelCounterUpper.calculateFuelRequiredForFleet(IntStream.of(12, 13, 14, 14));
        assertThat(actualFuelRequired, is(8));
    }

    @Test
    public void shouldBeAbleToSumMultipleTotalFuelRequirements() {
        int actualFuelRequired = fuelCounterUpper.calculateFuelRequiredForFleet(IntStream.of(14, 1969, 100756));
        assertThat(actualFuelRequired, is(51314));
    }
}
