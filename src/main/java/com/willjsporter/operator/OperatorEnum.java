package com.willjsporter.operator;

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
    },
    LESS_THAN {
        @Override
        public int calculate(int a, int b) {
            return a < b ? 1 : 0;
        }
    },
    EQUALS {
        @Override
        public int calculate(int a, int b) {
            return a == b ? 1 : 0;
        }
    }
}

