package com.willjsporter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IntcodeProgram {

    private List<Integer> programInput;

    public IntcodeProgram(List<Integer> programInput) {
        this.programInput = programInput;
    }


    public List<Integer> run() {
         List<Integer> outputList = new ArrayList<>(programInput);
         outputList.set(programInput.get(3), programInput.get(1) + programInput.get(2));
         return outputList;
    }
}
