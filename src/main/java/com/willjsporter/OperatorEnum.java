package com.willjsporter;

public enum OperatorEnum implements Operator {
    ADD {
        @Override
        public int calculate(int a, int b) {
            return a + b;
        }
    },
    MULTIPLY {
        @Override
        public int calculate(int a, int b) {
            return a * b;
        }
    }
}

