package com.willjsporter;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OrbitCalculatorTest {

    @Test
    public void aStreamOfOrbitInfoCanBeUsedToInitialiseOrbitCalculatorAndPopulatesTheDirectOrbitTrackerMap() {
        Stream<String> orbitStream = Stream.of("AAA)BBB", "BBB)CCC", "BBB)DDD");
        OrbitCalculator orbitCalculator = new OrbitCalculator(orbitStream);
        assertThat(orbitCalculator.getDirectOrbitTracker(), is(Map.of(
            "BBB", "AAA",
            "CCC", "BBB",
            "DDD", "BBB"
        )));
    }

    @Test
    public void orbitCalculatorOffersFunctionalityToCountNumberOfDirectAndIndirectOrbits() {
        Stream<String> orbitStream = Stream.of("AAA)BBB", "BBB)CCC", "BBB)DDD", "DDD)EEE", "DDD)FFF");
        OrbitCalculator orbitCalculator = new OrbitCalculator(orbitStream);
        assertThat(orbitCalculator.countDirectAndIndirectOrbits(), is(11));
    }
}
