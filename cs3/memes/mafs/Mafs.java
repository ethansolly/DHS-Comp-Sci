package memes.mafs;

import java.awt.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Function;

public class Mafs {

    private static final double minErr = Math.pow(10, -7);

    public static void main(String...args) {
        System.out.println(digitalRoot(2035));
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

    public static double derive(Function<Double, Double> f, double x) {
        double f0 = f.apply(x);
        double dx = 0.0001;
        double dy = f.apply(x + dx) - f0;

        double oldDy = dy;
        int iter = 0;
        while (iter < 1000) {
            dx /= 10;
            dy = f.apply(x + dx) - f0;
            if (Math.abs(dy - oldDy) < minErr)
                break;
            else
                oldDy = dy;
            iter++;
        }

        return dy/dx;
    }

    public static int digitalSum(int n) {
        if (n < 10) {
            return n;
        }
        else {
            int sum = 0;
            while(n > 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }
    }

    public static int digitalRoot(int n) {
        return (int)(n - 9.0*Math.floor((double)(n-1)/9));
    }
}
