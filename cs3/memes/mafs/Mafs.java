package memes.mafs;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Function;

public class Mafs {

    private static final double minErr = Math.pow(10, -7);

    public static void main(String...args) {
        Function<Double, Double> funct = d -> d*d;
        System.out.println(derive(funct).apply(4.0));
    }

    public static Function<Double, Double> derive(Function<Double, Double> f) {
        return d -> {
            double f0 = f.apply(d);
            double dx = 0.0001;
            double dy = f.apply(d + dx) - f0;

            double oldDy = dy;
            while (true) {
                dx /= 10;
                dy = f.apply(d + dx) - f0;
                if (Math.abs(dy - oldDy) < minErr)
                    break;
                else
                    oldDy = dy;
            }

            return dy/dx;
        };
    }

    public static void draw(Rectangle r, Graphics g, Function<Double, Double> function) {
        for (double i = r.x; i < r.x + r.width; i+=minErr) {
            double y = function.apply(i);
            g.drawRect((int)i, (int)(r.height-y), 1, 1);
        }
    }
}
