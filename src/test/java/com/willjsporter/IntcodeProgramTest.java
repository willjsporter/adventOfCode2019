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
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 4, 2));
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 6, 2)));
    }

    @Test
    public void givenOpcode2_andOutputPosition1_thenProgramShouldMultiplyTheInputs_andPutResultInPosition1() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(2, 11, 3, 1));
        assertThat(intcodeProgram.run(), is(List.of(2, 33, 3, 1)));
    }

    @Test
    public void givenOpcode99_thenProgramShouldHalt() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(99, 1, 2, 3, 4, 5, 6, 299));
        assertThat(intcodeProgram.run(), is(List.of(99, 1, 2, 3, 4, 5, 6, 299)));
    }

    @Test
    public void givenAnInvalidOpCode_theProgramShouldThrowAnException() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(4, 1, 2, 3));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid opcode: Opcode must be either 1, 2 or 99");
        intcodeProgram.run();
    }
}
