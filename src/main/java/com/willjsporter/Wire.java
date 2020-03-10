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
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> {
                    this.lastCoordinate = getLastCoordinate().add(Coordinate.of(1, 0));
                    wireLocations.add(this.lastCoordinate);
                });
                break;
            case "L":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> {
                    this.lastCoordinate = getLastCoordinate().add(Coordinate.of(-1, 0));
                    wireLocations.add(this.lastCoordinate);
                });
                break;
            case "U":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> {
                    this.lastCoordinate = getLastCoordinate().add(Coordinate.of(0, 1));
                    wireLocations.add(this.lastCoordinate);
                });
                break;
            case "D":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> {
                    this.lastCoordinate = getLastCoordinate().add(Coordinate.of(0, -1));
                    wireLocations.add(this.lastCoordinate);
                });
            default: break;
        }
    }
}
