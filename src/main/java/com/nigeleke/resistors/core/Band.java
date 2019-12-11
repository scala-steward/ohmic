package com.nigeleke.resistors.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Band {

    Black(0),
    Brown(1),
    Red(2),
    Orange(3),
    Yellow(4),
    Green(5),
    Blue(6),
    Violet(7),
    Grey(8),
    White(9);

    private static final Map<Integer, Band> valueToBandMap = new HashMap<>();

    static {
        Arrays.stream(Band.values()).forEach(b -> valueToBandMap.put(b.value, b));
    }

    public final int value;

    Band(int value) {
        this.value = value;
    }

    public static final Band from(char c) {
        return Arrays.stream(Band.values()).filter(v -> v.value == (c - '0')).findFirst().get();
    }
}
