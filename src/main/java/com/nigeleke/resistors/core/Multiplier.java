package com.nigeleke.resistors.core;

public enum Multiplier {

    Silver  (1e-2),
    Gold    (1e-1),
    Black   (1e0),
    Brown   (1e1),
    Red     (1e2),
    Orange  (1e3),
    Yellow  (1e4),
    Green   (1e5),
    Blue    (1e6),
    Violet  (1e7),
    Grey    (1e8),
    White   (1e9);

    public final double value;

    Multiplier(double value) {
        this.value = value;
    }
}
