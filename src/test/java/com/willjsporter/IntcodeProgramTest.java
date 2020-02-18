package com.willjsporter;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntcodeProgramTest {

    @Test
    public void givenOpcode1_andOutputPosition2_thenProgramshouldAddTheInputs_andPutResultInPosition2() {
        IntcodeProgram intcodeProgram = new IntcodeProgram(List.of(1, 2, 4, 2));
        assertThat(intcodeProgram.run(), is(List.of(1, 2, 6, 2)));
    }

}
