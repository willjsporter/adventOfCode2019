package com.willjsporter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Wire {

    private List<Coordinate> wireLocations = new ArrayList<>();

    public Wire(Stream<String> wirePlacementInstructions) {
        convertInstructionsToCoordinates(wirePlacementInstructions);
    }

    public List<Coordinate> getWirePath() {
        return this.wireLocations;
    }

    public Coordinate getMostCentralIntersection(Wire secondWire) {
        return this.wireLocations
            .stream()
            .filter(coord -> secondWire.wireLocations.contains(coord))
            .sorted()
            .findFirst()
            .orElse(Coordinate.of(0, 0));
    }

    private void convertInstructionsToCoordinates( Stream<String> wirePlacementInstructions) {
        wirePlacementInstructions.forEach(this::convertSingleInstructionToCoordinates);
    }

    private void convertSingleInstructionToCoordinates(String instruction) {
        switch(instruction.substring(0, 1)) {
            case "R":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> wireLocations.add(getLastWireLocation().add(Coordinate.of(1, 0))));
                break;
            case "L":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> wireLocations.add(getLastWireLocation().add(Coordinate.of(-1, 0))));
                break;
            case "U":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> wireLocations.add(getLastWireLocation().add(Coordinate.of(0,1))));
                break;
            case "D":
                IntStream.range(0, Integer.parseInt(instruction.substring(1))).forEach(a -> wireLocations.add(getLastWireLocation().add(Coordinate.of(0,-1))));
                break;
            default: break;
        }
    }

    private Coordinate getLastWireLocation() {
        if(wireLocations.size() > 0) {
            return wireLocations.get(wireLocations.size() -1);
        } else return Coordinate.of(0, 0);
    }
}
