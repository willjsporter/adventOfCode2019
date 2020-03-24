package com.willjsporter.intcode;

import com.willjsporter.util.InputReader;
import com.willjsporter.util.SystemOutResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntcodeProgramTest {

    private InputReader inputReader = mock(InputReader.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule public SystemOutResource sysOut = new SystemOutResource();

    @Test
    public void givenOpcode1_andOutputPosition2_thenProgramShouldAddTheInputs_andPutResultInPosition2() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 0, 2), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 1, 2)));
    }

    @Test
    public void givenOpcode2_andOutputPosition1_thenProgramShouldMultiplyTheInputs_andPutResultInPosition1() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(2, 2, 3, 1), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(2, 3, 3, 1)));
    }

    @Test
    public void givenOpcode3_andInputParameter1_thenProgramShouldPutInputInPosition1() {
        when(inputReader.readInputAsInt()).thenReturn(19);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3, 1), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3, 19)));
    }

    @Test
    public void givenOpcode4_andOutputParameter1_thenProgramShouldOutputValueOfIndexEqualToValueAtPosition1_ThenHalt() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(4, 1), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(4, 1)));
        assertThat(sysOut.asString(), is("1\n"));
    }

    @Test
    public void givenOpcode99AtStartOfInput_thenProgramShouldHaltImmediately() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(99, 1, 2, 3, 4, 5, 6, 299), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(99, 1, 2, 3, 4, 5, 6, 299)));
    }

    @Test
    public void givenOpcode99InMiddleOfInput_thenProgramShouldHaltWhen99Reached() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 1, 1, 1, 99, 1, 6, 0, 3, 2, 3, 4, 5), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 1, 1, 99, 1, 6, 0, 3, 2, 3, 4, 5)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 3, 6, 2, 6, 299, 5, 99, 20, 30), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 3, 6, 2, 180, 9, 5, 99, 20, 30)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem2() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem3() {
        when(inputReader.readInputAsInt()).thenReturn(19);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 1, 2, 4, 99, 5, 4, 8, 99), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1, 1, 2, 4, 3, 19, 4, 8, 99)));
        assertThat(sysOut.asString(), is("99\n"));
    }

    @Test
    public void givenAnInvalidOpCode_theProgramShouldThrowAnException() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(12, 1, 2, 3), inputReader);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid opcode: Opcode must be either 1, 2, 3, 4 or 99");
        intcodeProgram.run();
    }

    @Test
    public void givenAnOpCodeWithParameterModes_theProgramShouldProcessTheInstructionBasedOnTheParamModes() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1002,4,3,4,33), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1002,4,3,4,99)));
    }

    @Test
    public void shouldBeAbleToHandleMultipleOpCodeWithParameterModes() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1101, 4, 3, 2, 104, 2, 1002, 2, 4, 7), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(1101, 4, 7, 2, 104, 2, 1002, 28, 4, 7)));
        assertThat(sysOut.asString(), is("2\n"));
    }

    @Test
    public void opcode8Stores1IfFirstAndSecondParametersAreEqual_PositionMode() {
        when(inputReader.readInputAsInt()).thenReturn(8);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,9,8,9,10,9,4,9,99,-1,8), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,9,8,9,10,9,4,9,99,1,8)));
        assertThat(sysOut.asString(), is("1\n"));
    }

    @Test
    public void opcode8Stores0IfFirstAndSecondParametersAreNotEqual_ImmediateMode() {
        when(inputReader.readInputAsInt()).thenReturn(6);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,3,1108,-1,8,3,4,3,99), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,3,1108,0,8,3,4,3,99)));
        assertThat(sysOut.asString(), is("0\n"));
    }

    @Test
    public void opcode7Stores1IfFirstParameterIsLessThanSecondParam_ImmediateMode() {
        when(inputReader.readInputAsInt()).thenReturn(2);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,3,1107,-1,8,3,4,3,99), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,3,1107,1,8,3,4,3,99)));
        assertThat(sysOut.asString(), is("1\n"));
    }

    @Test
    public void opcode7Stores0IfFirstParameterIsNotLessThanSecondParam_PositionMode() {
        when(inputReader.readInputAsInt()).thenReturn(9);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,9,7,9,10,9,4,9,99,-1,8), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,9,7,9,10,9,4,9,99,0,8)));
        assertThat(sysOut.asString(), is("0\n"));
    }

    @Test
    public void opcode5ChangesPointerToSecondParameterPositionIfFirstParamIsNot0_PositionMode() {
        when(inputReader.readInputAsInt()).thenReturn(9);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,12,5,12,15,2,13,14,13,4,13,99,-1,1,0,9), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,12,5,12,15,2,13,14,13,4,13,99,9,1,0,9)));
        assertThat(sysOut.asString(), is("1\n"));
    }

    @Test
    public void opcode5SkipsToNextOpcodeIfFirstParamIs0_PositionMode() {
        when(inputReader.readInputAsInt()).thenReturn(0);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,12,5,12,15,2,13,14,13,4,13,99,-1,1,0,9), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,12,5,12,15,2,13,14,13,4,13,99,0,0,0,9)));
        assertThat(sysOut.asString(), is("0\n"));
    }

    @Test
    public void opcode6SkipsToNextOpcodeIfFirstParamIsNot0_ImmediateMode() {
        when(inputReader.readInputAsInt()).thenReturn(1);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,3,1106,-1,9,1101,0,0,12,4,12,99,1), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,3,1106,1,9,1101,0,0,12,4,12,99,0)));
        assertThat(sysOut.asString(), is("0\n"));
    }

    @Test
    public void opcode6ChangesPointerToSecondParameterPositionIfFirstParamIs0_ImmediateMode() {
        when(inputReader.readInputAsInt()).thenReturn(0);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,3,1106,-1,9,1101,0,0,12,4,12,99,1), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,3,1106,0,9,1101,0,0,12,4,12,99,1)));
        assertThat(sysOut.asString(), is("1\n"));
    }

    @Test
    public void opcode6ChangesPointerToSecondParameterPositionIfFirstParamIs0_PositionMode() {
        when(inputReader.readInputAsInt()).thenReturn(0);
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9), inputReader);
        assertThat(intcodeProgram.run(), is(List.of(3,12,6,12,15,1,13,14,13,4,13,99,0,0,1,9)));
        assertThat(sysOut.asString(), is("0\n"));
    }

}

//3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9 (using position mode)
//    3,3,1105,-1,9,1101,0,0,12,4,12,99,1 (using immediate mode)
