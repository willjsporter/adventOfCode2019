package com.willjsporter.util;

import org.junit.rules.ExternalResource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//taken from: https://www.dontpanicblog.co.uk/2017/05/12/test-system-out-with-junit/

public class SystemOutResource extends ExternalResource {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Override
    protected void before() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void after() {
        System.setOut(sysOut);
    }

    public String asString() {
        return outContent.toString();
    }
}

