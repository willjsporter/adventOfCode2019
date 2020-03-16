package com.willjsporter.util;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {

    private BufferedReader reader;

    public InputReader(BufferedReader reader) {
        this.reader = reader;
    }

    public int readInputAsInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
