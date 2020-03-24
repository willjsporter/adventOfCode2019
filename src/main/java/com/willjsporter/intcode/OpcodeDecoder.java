package com.willjsporter.intcode;

public class OpcodeDecoder {

    private int opcode;
    private int param1Mode;
    private int param2Mode;
    private int param3Mode;

    public OpcodeDecoder(int opcodeAndParams) {
        this.opcode = opcodeAndParams % 100;
        this.param1Mode = (opcodeAndParams / 100) % 10;
        this.param2Mode = (opcodeAndParams / 1000) % 10;
        this.param3Mode = (opcodeAndParams / 10000) % 10;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public int getParam1Mode() {
        return this.param1Mode;
    }

    public int getParam2Mode() {
        return this.param2Mode;
    }

    public int getParam3Mode() {
        return this.param3Mode;
    }
}
