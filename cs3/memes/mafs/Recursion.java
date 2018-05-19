package memes.mafs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class Recursion extends JFrame {

    private double xLeft = 0;
    private double xRight = 1;
    private double yBottom = 0;
    private double yTop = 1;

    private boolean havePoint = false;

    public static void main(String...args) {
        Recursion r = new Recursion("Recursion!");
    }

    public Recursion(String s) {
        super(s);
        setVisible(true);
    }

    public void paint(Graphics g) {

        //if (havePoint) {
        for (double r = 0; r <= 4; r+=0.01) {

            final double R = r;

            Function<Double, Double> function = d -> R*d*(1-d);

            g.clearRect(0, 0, getWidth(), getHeight());

            //Here , (i,j) represents the pixel, (x,y) is the point of the function
            for (int i = 0; i < getWidth(); i++) {
                //Draw function
                double x = (xRight-xLeft)*((double) i)/getWidth() + xLeft;
                double y = function.apply(x);
                int j = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));
                g.drawRect(i, j, 0, 0);

                //Draw y = x
                x = (xRight-xLeft)*((double) i)/getWidth() + xLeft;
                y = x;
                j = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));
                g.drawRect(i, j, 0, 0);
            }

            double x0 = 0.2;
            int recI = (int)(getWidth()*(x0-xLeft)/(xRight-xLeft));;

            int maxIter = 100;

            for (int iter = 0; iter < maxIter; iter++) {
                //Start on y = x, draw vertically to function value
                double x = (xRight-xLeft)*((double) recI)/getWidth() + xLeft;
                double y = x;
                int recJ1 = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));

                y = function.apply(x);
                int recJ2 = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));

                g.drawLine(recI, recJ1, recI, recJ2);

                //Draw from function horizontally to y=x

                x=y;
                int recINew = (int)(getWidth()*(x-xLeft)/(xRight-xLeft));
                g.drawLine(recI, recJ2, recINew, recJ2);

                recI = recINew;

            }

            try {
                Thread.sleep(100);
                if (r==4)
                    Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
