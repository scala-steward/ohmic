package com.nigeleke.resistors.core;

public enum Multiplier {

    Black(1),
    Brown(10),
    Red(100),
    Orange(1000),
    Yellow(10000),
    Green(100000),
    Blue(1000000),
    Violet(10000000);

    public final double value;

    Multiplier(double value) {
        this.value = value;
    }
}
