package com.willjsporter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class OrbitCalculator {

    private Map<String, String> directOrbitTracker;

    public OrbitCalculator(Stream<String> orbitStream) {
        this.directOrbitTracker = new HashMap<>();
        orbitStream
            .map(line -> line.split("\\)"))
            .forEach(splitLine -> this.directOrbitTracker.put(splitLine[1], splitLine[0]));
    }

    public Map<String, String> getDirectOrbitTracker() {
        return directOrbitTracker;
    }

    public int countDirectAndIndirectOrbits() {
        return this.directOrbitTracker
            .keySet().stream()
            .mapToInt(this::getNumberOfOrbitsForSingleObject)
            .sum();
    }

    private int getNumberOfOrbitsForSingleObject(String objectName) {
            int orbitsForEntry = 0;
            while (directOrbitTracker.get(objectName) != null) {
                orbitsForEntry += 1;
                objectName = directOrbitTracker.get(objectName);
            }
            return orbitsForEntry;
    }
}
