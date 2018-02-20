package com.ethansolly.cantrip;

import java.applet.Applet;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by Ethan Sollenberger on 4/4/2017.
 */
public class PrimeWalker extends Applet {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

    boolean move = false;

    public void init() {
        setSize(width, height);
    }

    public void paint(Graphics g) {
        int p = 0;
        int o = 0;
        int x = width/2;
        int y = height/2;
        while (true) {
            if (primeTest(p)) {
                //System.out.println(p);
                o++;
            }
            switch (o % 4) {
                case 0:
                    g.drawLine(x, y, x, y+1);
                    if (move) g.copyArea(0, 0, width, height, 0, -1);
                    y++;
                    break;
                case 1:
                    g.drawLine(x, y, x+1, y);
                    if (move) g.copyArea(0, 0, width, height, -1, 0);
                    x++;
                    break;
                case 2:
                    g.drawLine(x, y, x, y-1);
                    if (move) g.copyArea(0, 0, width, height, 0, 1);
                    y--;
                    break;
                case 3:
                    g.drawLine(x, y, x-1, y);
                    if (move) g.copyArea(0, 0, width, height, 1, 0);
                    x--;
                    break;
            }
            p++;
        }
    }

    public boolean primeTest(int i) {
        if (i == 1 || i == 0) return false;
        if (i == 2) return true;
        for (int k = 2; k < i; k++) {
            if (i % k == 0) return false;
        }
        return true;
    }

}
