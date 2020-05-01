package com.willjsporter.intcode;

import com.willjsporter.Coordinate;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntcodeBlasterOutputReaderTest {

    @Test
    public void getBlasterOutputForGivenSequenceShouldReturnCoordinateOfBlasterSequenceAndLastOutputtedValue() {
        List<Integer> programInputThatOnlyOutputs2ThenHalts = List.of(104, 2);
        //I didn't want to bring in a load of dependency injection so I chose a simple intcode program that will just print the number 2 then end.
        //Also made a separate constructor to read a list rather than a file. Not ideal but allows for simple testing.

        IntcodeBlasterOutputReader intcodeBlasterOutputReader = new IntcodeBlasterOutputReader(programInputThatOnlyOutputs2ThenHalts);
        assertThat(intcodeBlasterOutputReader.getBlasterOutputForGivenSequence(1,0,4,3,2), CoreMatchers.is(Coordinate.of(10432, 2)));
        assertThat(intcodeBlasterOutputReader.getBlasterOutputForGivenSequence(0,1,2,3,4), is(Coordinate.of(1234, 2)));
    }
}
