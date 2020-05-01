package com.willjsporter.util;

import com.willjsporter.intcode.IntcodeBlasterOutputReader;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaxBlasterSequenceOutputUtilIntegrationTest {

    @Test
    public void shouldChainBlasterOutputsTogether_ByInputtingOutputOfPreviousAndNextBlasterNumber_1() {
        IntcodeBlasterOutputReader intcodeBlasterOutputReader = new IntcodeBlasterOutputReader("src/test/resources/intcodeBlasterOutputTestInput_1.txt");
        MaxBlasterSequenceOutputUtil maxBlasterSequenceOutputUtil = new MaxBlasterSequenceOutputUtil(intcodeBlasterOutputReader);
        assertThat(maxBlasterSequenceOutputUtil.tryAllOrdersAndGetMaxOutput(), is(43210));
    }

    @Test
    public void shouldChainBlasterOutputsTogether_ByInputtingOutputOfPreviousAndNextBlasterNumber_2() {
        IntcodeBlasterOutputReader intcodeBlasterOutputReader = new IntcodeBlasterOutputReader("src/test/resources/intcodeBlasterOutputTestInput_2.txt");
        MaxBlasterSequenceOutputUtil maxBlasterSequenceOutputUtil = new MaxBlasterSequenceOutputUtil(intcodeBlasterOutputReader);
        assertThat(maxBlasterSequenceOutputUtil.tryAllOrdersAndGetMaxOutput(), is(54321));
    }

    @Test
    public void shouldChainBlasterOutputsTogether_ByInputtingOutputOfPreviousAndNextBlasterNumber_3() {
        IntcodeBlasterOutputReader intcodeBlasterOutputReader = new IntcodeBlasterOutputReader("src/test/resources/intcodeBlasterOutputTestInput_3.txt");
        MaxBlasterSequenceOutputUtil maxBlasterSequenceOutputUtil = new MaxBlasterSequenceOutputUtil(intcodeBlasterOutputReader);
        assertThat(maxBlasterSequenceOutputUtil.tryAllOrdersAndGetMaxOutput(), is(65210));
    }
}
