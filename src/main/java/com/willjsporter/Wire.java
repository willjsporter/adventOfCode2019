package com.willjsporter;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Wire {

    private Set<Coordinate> wireLocations = new HashSet<>();
    private Coordinate lastCoordinate;

    public Wire(Stream<String> wirePlacementInstructions) {
        this.lastCoordinate = Coordinate.of(0, 0);
        convertInstructionsToCoordinates(wirePlacementInstructions);
    }

    public Set<Coordinate> getWirePath() {
        return this.wireLocations;
    }

    public Coordinate getMostCentralIntersection(Wire secondWire) {
        return this.wireLocations
            .stream()
            .filter(coord -> secondWire.wireLocations.contains(coord))
            .sorted()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("There is no intersection between the two wire."));
    }

    private Coordinate getLastCoordinate() {
        return lastCoordinate;
    }

    private void convertInstructionsToCoordinates(Stream<String> wirePlacementInstructions) {
        wirePlacementInstructions.forEach(this::convertSingleInstructionToCoordinates);
    }

    private void convertSingleInstructionToCoordinates(String instruction) {
        switch(instruction.substring(0, 1)) {
            case "R":
                addNCoordinatesWithGivenIncrement(instruction, 1, 0);
                break;
            case "L":
                addNCoordinatesWithGivenIncrement(instruction, -1, 0);
                break;
            case "U":
                addNCoordinatesWithGivenIncrement(instruction, 0, 1);
                break;
            case "D":
                addNCoordinatesWithGivenIncrement(instruction, 0, -1);
            default: break;
        }
    }

    private void addNCoordinatesWithGivenIncrement(String instruction, int xIncrement, int yIncrement) {
        IntStream.range(0, Integer.parseInt(instruction.substring(1)))
            .forEach(numberInRange -> {
            this.lastCoordinate = getLastCoordinate().add(Coordinate.of(xIncrement, yIncrement));
            wireLocations.add(this.lastCoordinate);
        });
    }
}
