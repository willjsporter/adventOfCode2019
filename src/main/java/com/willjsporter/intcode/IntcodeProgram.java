package com.willjsporter.intcode;

import com.willjsporter.intcode.operator.Operator;
import com.willjsporter.util.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.willjsporter.intcode.operator.OperatorEnum.*;

public class IntcodeProgram {

    private List<Integer> programInput;
    private List<Integer> prespecifiedInput;
    private int programPosition;
    private int inputPosition;
    private InputReader inputReader;

    public IntcodeProgram(List<Integer> programInput, InputReader inputReader, Integer ...prespecifiedInput) {
        this.programInput = new ArrayList<>(programInput);
        this.inputReader = inputReader;
        this.programPosition = 0;
        this.inputPosition = 0;
        this.prespecifiedInput = Arrays.asList(prespecifiedInput);
    }

    public List<Integer> run() {
        while(this.programPosition < programInput.size()) {
            if(programInput.get(programPosition) == 99) { break; }
            iterateProgram(this.programPosition);
        }
        return programInput;
    }

    private void iterateProgram(int programPosition) {
        OpcodeDecoder opcodeDecoder = new OpcodeDecoder(programInput.get(programPosition));
        switch (opcodeDecoder.getOpcode()) {
            case 1:
                executeOpcode(ADD, programPosition, opcodeDecoder);
                break;
            case 2:
                executeOpcode(MULTIPLY, programPosition, opcodeDecoder);
                break;
            case 3:
                storeInput(programPosition);
                break;
            case 4:
                System.out.println(paramCalculator(programPosition, opcodeDecoder.getParam1Mode(), 1));
                this.programPosition += 2;
                break;
            case 5:
                jumpPointer(programPosition, opcodeDecoder, false);
                break;
            case 6:
                jumpPointer(programPosition, opcodeDecoder, true);
                break;
            case 7:
                executeOpcode(LESS_THAN, programPosition, opcodeDecoder);
                break;
            case 8:
                executeOpcode(EQUALS, programPosition, opcodeDecoder);
                break;
            default:
                throw new IllegalArgumentException("Invalid opcode: Opcode must be either 1, 2, 3, 4 or 99");
        }
    }

    private void storeInput(int programPosition) {
        Integer valueToStore = this.prespecifiedInput.size() > 0
            ? prespecifiedInput.get(this.inputPosition)
            : this.inputReader.readInputAsInt();
        programInput.set(programInput.get(programPosition + 1), valueToStore);
        this.inputPosition += 1;
        this.programPosition += 2;
    }

    private void jumpPointer(int inputPosition, OpcodeDecoder opcodeDecoder, boolean isOpcode6) {
        int param1 = paramCalculator(inputPosition, opcodeDecoder.getParam1Mode(), 1);
        int param2 = paramCalculator(inputPosition, opcodeDecoder.getParam2Mode(), 2);
        boolean pointerShouldJump = (param1 == 0) == isOpcode6;

        this.programPosition = pointerShouldJump
            ? param2
            : inputPosition + 3;
    }

    private void executeOpcode(Operator operator, int inputPosition, OpcodeDecoder opcodeDecoder) {
        int param1 = paramCalculator(inputPosition, opcodeDecoder.getParam1Mode(), 1);
        int param2 = paramCalculator(inputPosition, opcodeDecoder.getParam2Mode(), 2);
        programInput.set(
            programInput.get(inputPosition + 3),
            operator.calculate(
                param1,
                param2
            ));
        this.programPosition += 4;
    }

    private Integer paramCalculator(int inputPosition, int param1Mode, int paramNumber) {
        return param1Mode == 1 ? programInput.get(inputPosition + paramNumber) : programInput.get(programInput.get(inputPosition + paramNumber));
    }
}
