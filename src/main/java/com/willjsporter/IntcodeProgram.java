package com.willjsporter;

import java.util.ArrayList;
import java.util.List;

import static com.willjsporter.OperatorEnum.ADD;
import static com.willjsporter.OperatorEnum.MULTIPLY;

public class IntcodeProgram {

    private List<Integer> programInput;

    public IntcodeProgram(List<Integer> programInput) {
        this.programInput = new ArrayList<>(programInput);
    }

    public List<Integer> run() {
        int inputPosition = 0;

        while(inputPosition < programInput.size()) {
            if(programInput.get(inputPosition) == 99) { break; }
            iterateProgram(inputPosition);
            inputPosition += 4;
        }
        return programInput;
    }

    private List<Integer> iterateProgram(int inputPosition) {
         switch (programInput.get(inputPosition)) {
             case 1:
                 executeOpcode(ADD, inputPosition);
                 break;
             case 2:
                 executeOpcode(MULTIPLY, inputPosition);
                 break;
             default:
                 throw new IllegalArgumentException("Invalid opcode: Opcode must be either 1, 2 or 99");
         }
         return programInput;
    }

    private Integer executeOpcode(Operator operator, int inputPosition) {
        return programInput.set(
            programInput.get(inputPosition + 3),
            operator.calculate(
                programInput.get(programInput.get(inputPosition + 1)),
                programInput.get(programInput.get(inputPosition + 2))
            ));
    }
}
