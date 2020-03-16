package com.willjsporter.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputReaderTest {

    private BufferedReader reader = mock(BufferedReader.class);

    @Test
    public void inputReaderShouldBeAbleToReadAndReturnInputAsAnInteger() throws IOException {
        when(reader
            .readLine())
            .thenReturn("2");
        InputReader inputReader = new InputReader(reader);
        assertThat(inputReader.readInputAsInt(), is(2));
    }


}
