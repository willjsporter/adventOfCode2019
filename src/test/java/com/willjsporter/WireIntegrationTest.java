package com.willjsporter;

import com.willjsporter.util.FileStreamerUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WireIntegrationTest {

    @Test
    public void givenInstructionsFromFile_shouldReadThem_thenPopulateThemToWireLocation() {
        List<Stream<String>> wirePaths = FileStreamerUtil.streamFileInputAsDirections("src/test/resources/directionsTestInput.txt");
        Wire firstWire = new Wire(wirePaths.get(0));
        Wire secondWire = new Wire(wirePaths.get(1));

        assertThat(firstWire.getWirePath(), is(
            List.of(
                Coordinate.of(0, 1),
                Coordinate.of(-1, 1),
                Coordinate.of(-2, 1),
                Coordinate.of(-3, 1),
                Coordinate.of(-3, 0),
                Coordinate.of(-3, -1),
                Coordinate.of(-3, -2),
                Coordinate.of(-3, -3),
                Coordinate.of(-3, -4),
                Coordinate.of(-2, -4),
                Coordinate.of(-2, -3),
                Coordinate.of(-2, -2)
            )));
        assertThat(secondWire.getWirePath(), is(
            List.of(
                Coordinate.of(0, -1),
                Coordinate.of(0, -2),
                Coordinate.of(0, -3),
                Coordinate.of(-1, -3),
                Coordinate.of(-2, -3),
                Coordinate.of(-3, -3),
                Coordinate.of(-4, -3),
                Coordinate.of(-4, -2),
                Coordinate.of(-3, -2),
                Coordinate.of(-2, -2),
                Coordinate.of(-1, -2),
                Coordinate.of(0, -2),
                Coordinate.of(1, -2)
            )));
    }

    @Test
    public void givenInstructionsForTwoWiresFromFile_withMultipleIntersections_shouldBeAbleToDetermineClosestIntersectionPointToCentre() {
        List<Stream<String>> wirePaths = FileStreamerUtil.streamFileInputAsDirections("src/test/resources/directionsTestInput.txt");
        Wire firstWire = new Wire(wirePaths.get(0));
        Wire secondWire = new Wire(wirePaths.get(1));

        assertThat(firstWire.getMostCentralIntersection(secondWire),
            is(Coordinate.of(-2, -2)));
    }


}
