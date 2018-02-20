package com.ethansolly.cantrip;

import java.applet.Applet;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by Ethan Sollenberger on 4/5/2017.
 */
public class Brotwurst extends Applet {

    private final double PI = Math.PI;
    private double theta = 0;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) screenSize.getWidth();
    private int height = (int) screenSize.getHeight();

    boolean move = false;

    public void init() {
        setSize(width, height);
    }

    public void paint(Graphics g) {
        /*
        long p = 1;
        int x = width/2;
        int y = height/2;

        while (true) {
            if (primeTest(p))
                rotate(PI/2);
            g.drawLine(x, y, (int) (x + Math.cos(theta)), (int) (y + Math.sin(theta)));
            x += Math.cos(theta);
            y += Math.sin(theta);
            if (move) g.copyArea(0, 0, width, height, -(int) Math.cos(theta), -(int) Math.sin(theta));
            p++;
        }
        */

        int max = 1023;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = (col - width/2.0)*4.0/width;
                double c_im = (row - height/2.0)*4.0/width;
                double x = 0, y = 0;
                int iteration = 0;
                while (x*x+y*y <= 4 && iteration < max) {
                    double x_new = x*x - y*y + c_re;
                    y = 2*x*y + c_im;
                    x = x_new;
                    iteration++;
                }
                if (iteration < max) {
                    int color = 255 - mand(new Complex(c_re, c_im));
                    g.setColor(new Color(color, color, color));
                    g.drawRect(col, row, 1, 1);
                }
                else {
                    g.setColor(Color.BLACK);
                    g.drawRect(col, row, 1, 1);
                }
            }
        }

    }

    public void rotate(double delta) {
        theta = (theta + delta) % (2*PI);
    }

    public int mand(Complex z0) {
        Complex z = z0;
        for (int i = 0; i < 255; i++) {
            if (z.abs() > 2.0) return i;
            z = z.times(z).plus(z0);
        }
        return 255;
    }

    public boolean primeTest(long i) {
        if (i == 1 || i == 0) return false;
        if (i == 2 || i == 3) return true;
        if (i % 2 == 0) return false;
        if (i % 3 == 0) return false;

        long k = 5;
        long w = 2;

        while (k*k <= i) {
            if (i % k == 0)
                return false;
            k += w;
            w = 6 - w;
        }

        return true;
    }

    public boolean fibTest(long n) {
        long test = 5 * n * n + 4;
        return isPerfectSquare(test);
    }

    public boolean isPerfectSquare(long n) {
        if (n < 0)
            return false;

        long tst = (long)(Math.sqrt(n) + 0.5);
        return tst*tst == n;
    }

    public boolean isPerfectTriangle(long n) {
        long test = 8 * n + 1;
        return isPerfectSquare(test);
    }
}
