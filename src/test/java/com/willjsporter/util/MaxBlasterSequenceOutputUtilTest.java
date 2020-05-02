package com.willjsporter.util;

import com.willjsporter.Coordinate;
import com.willjsporter.intcode.IntcodeBlasterOutputReader;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MaxBlasterSequenceOutputUtilTest {

    private IntcodeBlasterOutputReader intcodeBlasterOutputReaderMock = mock(IntcodeBlasterOutputReader.class);

    @Test
    public void tryAllOrdersAndGetMaxOutputShouldReturnMaxPossibleOutput() {
        when(intcodeBlasterOutputReaderMock.getBlasterOutputForGivenSequence(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
            .thenReturn(Coordinate.of(1234, 0));
        when(intcodeBlasterOutputReaderMock.getBlasterOutputForGivenSequence(4, 3, 2, 1, 0))
            .thenReturn(Coordinate.of(43210, 2));

        MaxBlasterSequenceOutputUtil testMaxBlasterSequenceOutputUtil = new MaxBlasterSequenceOutputUtil(intcodeBlasterOutputReaderMock);
        assertThat(testMaxBlasterSequenceOutputUtil.tryAllOrdersAndGetMaxOutput(), is(2));
    }
}
