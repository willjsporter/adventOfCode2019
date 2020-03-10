package com.willjsporter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Wire {

    private Map<Coordinate, Integer> wireLocations = new HashMap<>();
    private Coordinate lastCoordinate;
    private int stepsTaken;

    public Wire(Stream<String> wirePlacementInstructions) {
        this.stepsTaken = 0;
        this.lastCoordinate = Coordinate.of(0, 0);
        convertInstructionsToCoordinates(wirePlacementInstructions);
    }

    public Map<Coordinate, Integer> getWirePath() {
        return this.wireLocations;
    }

    public Coordinate getIntersectionPointWithLeastSteps(Wire secondWire) {
        return getIntersectionWithLeastSteps(secondWire)
            .map(Map.Entry::getKey)
            .orElseThrow(() -> new RuntimeException("There is no intersection between the two wire."));
    }

    public Integer getNumberOfStepsToIntersectionWithLeastSteps(Wire secondWire) {
        return getIntersectionWithLeastSteps(secondWire)
            .map(entry -> entry.getValue() + secondWire.wireLocations.get(entry.getKey()))
            .orElseThrow(() -> new RuntimeException("There is no intersection between the two wire."));
    }

    private Optional<Map.Entry<Coordinate, Integer>> getIntersectionWithLeastSteps(Wire secondWire) {
        return this.wireLocations
            .entrySet()
            .stream()
            .filter(coord -> secondWire.wireLocations.get(coord.getKey()) != null)
            .min(Comparator.comparingInt(entry -> entry.getValue() + secondWire.wireLocations.get(entry.getKey())));
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
            this.stepsTaken ++;
            wireLocations.put(this.lastCoordinate, this.stepsTaken);
        });
    }
}
