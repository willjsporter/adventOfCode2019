package com.willjsporter.intcode;

import com.willjsporter.Coordinate;
import com.willjsporter.util.FileStreamerUtil;
import com.willjsporter.util.InputReader;

import java.io.*;
import java.util.List;

public class IntcodeBlasterOutputReader {

    private List<Integer> programInput;
    private ByteArrayOutputStream output;

    public IntcodeBlasterOutputReader (String filepath) {
        this.programInput = FileStreamerUtil.streamFileInputAsIntegers(filepath);
        this.output = new ByteArrayOutputStream();
    }

    public IntcodeBlasterOutputReader (List<Integer> programInput) {
        this.programInput = programInput;
        this.output = new ByteArrayOutputStream();
    }


    public Coordinate getBlasterOutputForGivenSequence(int ...blasterSequence) {
        startStoringFileOutput();

        int lastAmplifierReading = 0;
        for(int phase : blasterSequence) {
            IntcodeProgram intcodeProgram = new IntcodeProgram(
                programInput,
                new InputReader(new BufferedReader(new InputStreamReader(System.in))),
                phase,
                lastAmplifierReading
            );

            intcodeProgram.run();
            lastAmplifierReading = Integer.parseInt(output.toString().trim());
            output.reset();
        }

        stopStoringFileOutput();

        int sequenceNumber = blasterSequence[0] * 10000 + blasterSequence[1] * 1000 + blasterSequence[2] * 100 + blasterSequence[3] * 10 + blasterSequence[4];
        return Coordinate.of(sequenceNumber, lastAmplifierReading);
    }

    private void stopStoringFileOutput() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    private void startStoringFileOutput() {
        System.setOut(new PrintStream(output));
    }

    //Change Standard Out to Byte Array Output Stream; Give program input as List of integers;
    //loop the program for the sequence and store the value each time to loop it back in.
    //return final value from program
}
//        System.out.println("Output of: " + lastBlasterReading + " for sequence: " + blasterSequence[0] + blasterSequence[1] + blasterSequence[2] + blasterSequence[3] + blasterSequence[4]);
