package com.ethansolly.cantrip;

import java.awt.*;

/**
 * Created by Ethan Sollenberger on 4/5/2017.
 */
public class Gradient {

    private Color c1, c2;

    public Gradient (Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Color get (int index) {
        int p = index;
        int w = p * 2 - 1;
        int w1 = (w/1+1) / 2;
        int w2 = 1 - w1;
        Color rgb = new Color(Math.round(c1.getRed() * w1 + c2.getRed() * w2),
                Math.round(c1.getGreen() * w1 + c2.getGreen() * w2),
                Math.round(c1.getBlue() * w1 + c2.getBlue() * w2));
        return rgb;
    }
}
