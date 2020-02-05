package com.willjsporter;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Unit test for simple App.
 */
public class FuelCounterUpperTest {

    @Test
    public void givenModuleHasMass12_ThenRequiredFuelShouldBe2()
    {
        assertThat(FuelCounterUpper.calculateFuelRequired(12), is(2));
    }

    @Test
    public void givenModuleHasMass14_ThenRequiredFuelShouldBe2()
    {
        assertThat(FuelCounterUpper.calculateFuelRequired(14), is(2));
    }

    @Test
    public void givenModuleHasMass1969_ThenRequiredFuelShouldBe654()
    {
        assertThat(FuelCounterUpper.calculateFuelRequired(1969), is(654));
    }

    @Test
    public void givenModuleHasMass100756_ThenRequiredFuelShouldBe654()
    {
        assertThat(FuelCounterUpper.calculateFuelRequired(100756), is(33583));
    }
}
