package com.willjsporter;

import java.util.ArrayList;
import java.util.List;

public class IntcodeProgram {

    private List<Integer> programInput;

    public IntcodeProgram(List<Integer> programInput) {
        this.programInput = programInput;
    }


    public List<Integer> run() {
         List<Integer> outputList = new ArrayList<>(programInput);
         switch (programInput.get(0)) {
             case 1:
                 outputList.set(programInput.get(3), programInput.get(1) + programInput.get(2));
                 break;
             case 2:
                 outputList.set(programInput.get(3), programInput.get(1) * programInput.get(2));
                 break;
             case 99:
                 break;
             default:
                 throw new IllegalArgumentException("Invalid opcode: Opcode must be either 1, 2 or 99");
         }
         return outputList;
    }
}
