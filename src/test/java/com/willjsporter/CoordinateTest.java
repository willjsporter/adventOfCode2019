package com.willjsporter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void shouldBeAbleToCompareCoordinatesBasedOnManhattanLengthFromOrigin() {
        Coordinate closerCoordinate = Coordinate.of(1,1);
        Coordinate furtherCoordinate = Coordinate.of(2,2);

        assertThat(closerCoordinate.compareTo(furtherCoordinate), is(-1));
    }

    @Test
    public void shouldBeAbleToAddCoordinates_ByAddingTheirXandYValues() {
        Coordinate coord1 = Coordinate.of(1,2);
        Coordinate coord2 = Coordinate.of(-2,4);

        assertThat(coord1.add(coord2), is(Coordinate.of(-1, 6)));
    }

}
