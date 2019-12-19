package com.nigeleke.resistors.core;

import javafx.scene.paint.Color;

public enum BandColour {
    Black(Color.BLACK),
    Brown(Color.BROWN),
    Red(Color.RED),
    Orange(Color.ORANGE),
    Yellow(Color.YELLOW),
    Green(Color.GREEN),
    Blue(Color.BLUE),
    Violet(Color.VIOLET),
    Grey(Color.GREY),
    White(Color.WHITE),
    Gold(Color.GOLD),
    Silver(Color.SILVER);

    private Color colour;

    BandColour(Color colour) {
        this.colour = colour;
    }
}
