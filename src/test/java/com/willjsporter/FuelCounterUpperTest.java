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

}
