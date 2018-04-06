package memes.prime;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.LinkedList;

public class Fern extends JApplet {

    private LinkedList<Double> xStack = new LinkedList<>();
    private LinkedList<Double> yStack = new LinkedList<>();

    public void init() {
        double x = 0;
        double y = 0;

        xStack.add(x);
        yStack.add(y);


        int ITER = 1000;

        for (int i = 0; i < ITER; i++) {

            double tempX = x;
            double tempY = y;

            double rand = Math.random();

            if (rand < 0.01) {
                //f1, 1%
                x = 0;
                y = 0.16*tempY;
            }
            else if (rand >= 0.01 && rand < 0.86) {
                //f2, 85%
                x = 0.85*tempX + 0.04*tempY;
                y = -0.04*tempX + 0.85*tempY + 1.6;
            }
            else if (rand >= 0.86 && rand < 0.93) {
                //f3, 7%
                x = 0.2*tempX - 0.26*tempY;
                y = 0.23*tempX + 0.22*tempY + 1.6;
            }
            else {
                //f4, 7%
                x = -0.15*tempX + 0.28*tempY;
                y = 0.26*tempX + 0.24*tempY + 0.44;
            }

            xStack.add(x);
            yStack.add(y);
        }
    }

    public void paint(Graphics g) {
        LinkedList<Double> tempXStack = (LinkedList<Double>) xStack.clone();
        LinkedList<Double> tempYStack = (LinkedList<Double>) yStack.clone();
        double x = tempXStack.pop();
        double y = tempYStack.pop();

        double mag = 100;

        while (!tempXStack.isEmpty() && !tempYStack.isEmpty()) {
            double tempX = mag*tempXStack.pop();
            double tempY = mag*tempYStack.pop();
            g.drawLine((int)(x+getWidth()/2), (int)(y), (int)(tempX+getWidth()/2), (int)(tempY));
            x = tempX;
            y = tempY;
        }
    }

}

