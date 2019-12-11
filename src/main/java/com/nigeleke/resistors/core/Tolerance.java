package com.nigeleke.resistors.core;

public enum Tolerance {
    Brown(1),
    Red(2),
    Gold(5),
    Silver(10);

    public final int value;

    Tolerance(int value) {
        this.value = value;
    }
}
