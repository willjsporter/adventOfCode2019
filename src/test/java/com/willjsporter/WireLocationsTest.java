package com.willjsporter;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WireLocationsTest {

    @Test
    public void givenU2Path_thenShouldReturnPathAsListOfCoordinates() {
        WireLocations wiring1 = new WireLocations(Stream.of("U2"));
        assertThat(wiring1.getWirePath(), is(List.of(Coordinate.of(0, 1), Coordinate.of(0, 2))));
    }

    @Test
    public void givenL2Path_thenShouldReturnPathAsListOfCoordinates() {
        WireLocations wiring1 = new WireLocations(Stream.of("L2"));
        assertThat(wiring1.getWirePath(), is(List.of(Coordinate.of(-1, 0), Coordinate.of(-2, 0))));
    }

    @Test
    public void givenMultiDirectionalPath_thenShouldReturnPathAsListOfCoordinates() {
        WireLocations wiring1 = new WireLocations(Stream.of("L2", "U1", "L1", "D4", "R1"));
        assertThat(wiring1.getWirePath(), is(
            List.of(
                Coordinate.of(-1, 0),
                Coordinate.of(-2, 0),
                Coordinate.of(-2, 1),
                Coordinate.of(-3, 1),
                Coordinate.of(-3, 0),
                Coordinate.of(-3, -1),
                Coordinate.of(-3, -2),
                Coordinate.of(-3, -3),
                Coordinate.of(-2, -3)
        )));
    }
}
