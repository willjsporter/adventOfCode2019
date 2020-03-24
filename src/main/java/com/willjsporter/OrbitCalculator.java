package com.willjsporter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    public int calculateOrbitalTransfers(String body1, String body2) {
        Set<String> orbitsOfBody1 = getSetOfAllOrbitsForSingleObject(body1);
        Set<String> orbitsOfBody2 = getSetOfAllOrbitsForSingleObject(body2);
        Set<String> combinedOrbits = new HashSet<>();

        addSetElementsFromXNotInYToZ(orbitsOfBody1, orbitsOfBody2, combinedOrbits);
        addSetElementsFromXNotInYToZ(orbitsOfBody2, orbitsOfBody1, combinedOrbits);
        return combinedOrbits.size();
    }

    private void addSetElementsFromXNotInYToZ(Set<String> setX, Set<String> setY, Set<String> setZ) {
        setX.forEach(orbit -> {
            if(!setY.contains(orbit)) {
                setZ.add(orbit);
            }
        });
    }

    private int getNumberOfOrbitsForSingleObject(String objectName) {
        int orbitsForEntry = 0;
        while (directOrbitTracker.get(objectName) != null) {
            orbitsForEntry += 1;
            objectName = directOrbitTracker.get(objectName);
        }
        return orbitsForEntry;
    }

    private Set<String> getSetOfAllOrbitsForSingleObject(String objectName) {
        Set<String> orbitsSet = new HashSet<>();
        while (directOrbitTracker.get(objectName) != null) {
            objectName = directOrbitTracker.get(objectName);
            orbitsSet.add(objectName);
        }
        return orbitsSet;
    }

}
