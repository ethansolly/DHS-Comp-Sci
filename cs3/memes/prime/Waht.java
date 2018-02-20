package memes.prime;

import memes.mafs.Mafs;
import memes.perdono.Utilz;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.util.function.Function;

public class Waht extends Applet {



    public void paint(Graphics g) {
        Function<Double, Double> function = d -> d*d;
        Mafs.draw(getBounds(), g, function);
    }
}
