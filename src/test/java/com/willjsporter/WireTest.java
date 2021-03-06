package com.willjsporter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WireTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenU2Path_thenShouldReturnPathAsSetOfCoordinates() {
        Wire wiring1 = new Wire(Stream.of("U2"));
        assertThat(wiring1.getWirePath().keySet(), is(Set.of(Coordinate.of(0, 1), Coordinate.of(0, 2))));
    }

    @Test
    public void givenL2Path_thenShouldReturnPathAsSetOfCoordinates() {
        Wire wiring1 = new Wire(Stream.of("L2"));
        assertThat(wiring1.getWirePath().keySet(), is(Set.of(Coordinate.of(-1, 0), Coordinate.of(-2, 0))));
    }

    @Test
    public void givenMultiDirectionalPath_thenShouldReturnPathAsSetOfCoordinates() {
        Wire wiring1 = new Wire(Stream.of("L2", "U1", "L1", "D4", "R1"));
        assertThat(wiring1.getWirePath().keySet(), is(
            Set.of(
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

    @Test
    public void givenTwoWiresThatDoNotIntersect_ShouldGiveCentreAsIntersectionPoint() {
        Wire wiring1 = new Wire(Stream.of("L1"));
        Wire wiring2 = new Wire(Stream.of("R1"));
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("There is no intersection between the two wire.");
        wiring1.getIntersectionPointWithLeastSteps(wiring2);
    }

    @Test
    public void givenTwoWiresThatIntersectOnce_ShouldGiveThatLocationAsIntersectionPoint() {
        Wire wiring1 = new Wire(Stream.of("L1", "U1", "R5"));
        Wire wiring2 = new Wire(Stream.of("R2", "U2"));
        assertThat(wiring1.getIntersectionPointWithLeastSteps(wiring2), is(Coordinate.of(2, 1)));
    }

    @Test
    public void givenTwoWiresThatIntersectMultipleTimes_ShouldGiveIntersectionPointWithLeastSteps() {
        Wire wiring1 = new Wire(Stream.of("L1", "U5", "R5"));
        Wire wiring2 = new Wire(Stream.of("U3", "L2", "D1", "R2", "D1", "L2"));
        assertThat(wiring1.getIntersectionPointWithLeastSteps(wiring2), is(Coordinate.of(-1, 3)));
    }

    @Test
    public void givenTwoWiresThatIntersectMultipleTimes1_ShouldGiveNumberOfStepsToClosestIntersectionPoint() {
        Wire wiring1 = new Wire(Stream.of("R75","D30","R83","U83","L12","D49","R71","U7","L72"));
        Wire wiring2 = new Wire(Stream.of("U62","R66","U55","R34","D71","R55","D58","R83"));
        assertThat(wiring1.getNumberOfStepsToIntersectionWithLeastSteps(wiring2), is(610));
    }

    @Test
    public void givenTwoWiresThatIntersectMultipleTimes2_ShouldGiveNumberOfStepsToClosestIntersectionPoint() {
        Wire wiring1 = new Wire(Stream.of("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"));
        Wire wiring2 = new Wire(Stream.of("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"));
        assertThat(wiring1.getNumberOfStepsToIntersectionWithLeastSteps(wiring2), is(410));
    }
}
