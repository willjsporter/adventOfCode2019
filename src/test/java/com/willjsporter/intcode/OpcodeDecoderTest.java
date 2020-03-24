package com.willjsporter.intcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OpcodeDecoderTest {

    @Test
    public void opcodeDecoderShouldDetermineOpcodeAndParameterModesForAllParameters() {
        OpcodeDecoder opcodeDecoder = new OpcodeDecoder(1002);
        assertThat(opcodeDecoder.getOpcode(), is(2));
        assertThat(opcodeDecoder.getParam1Mode(), is(0));
        assertThat(opcodeDecoder.getParam2Mode(), is(1));
        assertThat(opcodeDecoder.getParam3Mode(), is(0));
    }

    @Test
    public void opcodeDecoderShouldReturnParameterModesOfZeroIfNoneSpecified() {
        OpcodeDecoder opcodeDecoder = new OpcodeDecoder(1);
        assertThat(opcodeDecoder.getOpcode(), is(1));
        assertThat(opcodeDecoder.getParam1Mode(), is(0));
        assertThat(opcodeDecoder.getParam2Mode(), is(0));
        assertThat(opcodeDecoder.getParam3Mode(), is(0));
    }

}
