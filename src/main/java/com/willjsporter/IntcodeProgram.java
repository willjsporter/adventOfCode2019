package com.willjsporter;

import com.willjsporter.operator.Operator;
import com.willjsporter.util.InputReader;

import java.util.ArrayList;
import java.util.List;

import static com.willjsporter.operator.OperatorEnum.ADD;
import static com.willjsporter.operator.OperatorEnum.MULTIPLY;

public class IntcodeProgram {

    private List<Integer> programInput;
    private int inputPosition;
    private InputReader inputReader;

    public IntcodeProgram(List<Integer> programInput, InputReader inputReader) {
        this.programInput = new ArrayList<>(programInput);
        this.inputReader = inputReader;
        this.inputPosition = 0;

    }

    public List<Integer> run() {
        while(this.inputPosition < programInput.size()) {
            if(programInput.get(inputPosition) == 99) { break; }
            iterateProgram(this.inputPosition);
        }
        return programInput;
    }

    private void iterateProgram(int inputPosition) {
        OpcodeDecoder opcodeDecoder = new OpcodeDecoder(programInput.get(inputPosition));
        switch (opcodeDecoder.getOpcode()) {
             case 1:
                 executeOpcode(ADD, inputPosition, opcodeDecoder);
                 this.inputPosition += 4;
                 break;
             case 2:
                 executeOpcode(MULTIPLY, inputPosition, opcodeDecoder);
                 this.inputPosition += 4;
                 break;
             case 3:
                 programInput.set(programInput.get(inputPosition + 1), this.inputReader.readInputAsInt());
                 this.inputPosition += 2;
                 break;
             case 4:
                 int numberToOutput = opcodeDecoder.getParam1Mode() == 1 ? programInput.get(inputPosition + 1) : programInput.get(programInput.get(inputPosition + 1));
                 System.out.println(numberToOutput);
                 this.inputPosition += 2;
                 break;
             default:
                 throw new IllegalArgumentException("Invalid opcode: Opcode must be either 1, 2, 3, 4 or 99");
        }
    }

    private void executeOpcode(Operator operator, int inputPosition, OpcodeDecoder opcodeDecoder) {
        int param1 = opcodeDecoder.getParam1Mode() == 1 ? programInput.get(inputPosition + 1) : programInput.get(programInput.get(inputPosition + 1));
        int param2 = opcodeDecoder.getParam2Mode() == 1 ? programInput.get(inputPosition + 2) : programInput.get(programInput.get(inputPosition + 2));
        programInput.set(
            programInput.get(inputPosition + 3),
            operator.calculate(
                param1,
                param2
            ));
    }
}
