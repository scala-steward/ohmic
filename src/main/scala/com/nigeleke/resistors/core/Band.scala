package com.nigeleke.resistors.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Band {

    Black(0, BandColour.Black),
    Brown(1, BandColour.Brown),
    Red(2, BandColour.Red),
    Orange(3, BandColour.Orange),
    Yellow(4, BandColour.Yellow),
    Green(5, BandColour.Green),
    Blue(6, BandColour.Blue),
    Violet(7, BandColour.Violet),
    Grey(8, BandColour.Grey),
    White(9, BandColour.White);

    private static final Map<Integer, Band> valueToBandMap = new HashMap<>();

    static {
        Arrays.stream(Band.values()).forEach(b -> valueToBandMap.put(b.value, b));
    }

    public final int value;
    public final BandColour colour;

    Band(int value, BandColour colour) {
        this.value = value;
        this.colour = colour;
    }

    public static final Band from(char c) {
        return Arrays.stream(Band.values()).filter(v -> v.value == (c - '0')).findFirst().get();
    }
}
