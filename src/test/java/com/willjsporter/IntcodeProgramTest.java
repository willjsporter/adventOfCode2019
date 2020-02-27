package com.willjsporter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntcodeProgramTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenOpcode1_andOutputPosition2_thenProgramShouldAddTheInputs_andPutResultInPosition2() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 0, 2));
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 1, 2)));
    }

    @Test
    public void givenOpcode2_andOutputPosition1_thenProgramShouldMultiplyTheInputs_andPutResultInPosition1() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(2, 2, 3, 1));
        assertThat(intcodeProgram.run(), is(List.of(2, 3, 3, 1)));
    }

    @Test
    public void givenOpcode99AtStartOfInput_thenProgramShouldHaltImmediately() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(99, 1, 2, 3, 4, 5, 6, 299));
        assertThat(intcodeProgram.run(), is(List.of(99, 1, 2, 3, 4, 5, 6, 299)));
    }

    @Test
    public void givenOpcode99InMiddleOfInput_thenProgramShouldHaltWhen99Reached() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 1, 1, 1, 99, 1, 6, 0, 3, 2, 3, 4, 5));
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 1, 1, 99, 1, 6, 0, 3, 2, 3, 4, 5)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 3, 6, 2, 6, 299, 5, 99, 20, 30));
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 3, 6, 2, 180, 9, 5, 99, 20, 30)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem2() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50));
        assertThat(intcodeProgram.run(), is(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)));
    }

    @Test
    public void givenMultipleOpcodes_thenProgramShouldIterateThroughThem3() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 1, 1, 4, 99, 5, 6, 0, 99));
        assertThat(intcodeProgram.run(), is(List.of(30, 1, 1, 4, 2, 5, 6, 0, 99)));
    }

    @Test
    public void givenAnInvalidOpCode_theProgramShouldThrowAnException() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(4, 1, 2, 3));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid opcode: Opcode must be either 1, 2 or 99");
        intcodeProgram.run();
    }
}
