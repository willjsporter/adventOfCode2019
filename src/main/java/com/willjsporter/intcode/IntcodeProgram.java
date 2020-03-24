package com.willjsporter.intcode;

import com.willjsporter.intcode.operator.Operator;
import com.willjsporter.util.InputReader;

import java.util.ArrayList;
import java.util.List;

import static com.willjsporter.intcode.operator.OperatorEnum.*;

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
                break;
            case 2:
                executeOpcode(MULTIPLY, inputPosition, opcodeDecoder);
                break;
            case 3:
                programInput.set(programInput.get(inputPosition + 1), this.inputReader.readInputAsInt());
                this.inputPosition += 2;
                break;
            case 4:
                System.out.println(paramCalculator(inputPosition, opcodeDecoder.getParam1Mode(), 1));
                this.inputPosition += 2;
                break;
            case 5:
                jumpPointer(inputPosition, opcodeDecoder, false);
                break;
            case 6:
                jumpPointer(inputPosition, opcodeDecoder, true);
                break;
            case 7:
                executeOpcode(LESS_THAN, inputPosition, opcodeDecoder);
                break;
            case 8:
                executeOpcode(EQUALS, inputPosition, opcodeDecoder);
                break;
            default:
                throw new IllegalArgumentException("Invalid opcode: Opcode must be either 1, 2, 3, 4 or 99");
        }
    }

    private void jumpPointer(int inputPosition, OpcodeDecoder opcodeDecoder, boolean isOpcode6) {
        int param1 = paramCalculator(inputPosition, opcodeDecoder.getParam1Mode(), 1);
        int param2 = paramCalculator(inputPosition, opcodeDecoder.getParam2Mode(), 2);
        boolean pointerShouldJump = (param1 == 0) == isOpcode6;

        this.inputPosition = pointerShouldJump
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
        this.inputPosition += 4;
    }

    private Integer paramCalculator(int inputPosition, int param1Mode, int paramNumber) {
        return param1Mode == 1 ? programInput.get(inputPosition + paramNumber) : programInput.get(programInput.get(inputPosition + paramNumber));
    }
}
