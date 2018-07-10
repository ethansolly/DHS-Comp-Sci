package memes.mafs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class Recursion extends JFrame {

    private double xLeft = -10;
    private double xRight = 10;
    private double yBottom = -10;
    private double yTop = 10;

    private boolean havePoint = false;

    public static void main(String...args) {
        Recursion r = new Recursion("Recursion!");
    }

    public Recursion(String s) {
        super(s);
        setVisible(true);
    }

    public void paint(Graphics g) {

        //Logistic map
        for (double r = 0; r <= 5; r+=0.01) {
        //for (double x0 = xLeft; x0 < xRight; x0+=0.01) {

            final double R = r;

            Function<Double, Double> function = d -> R*Math.exp(d/Math.tan(d)-1);

            g.clearRect(0, 0, getWidth(), getHeight());

            //Here , (i,j) represents the pixel, (x,y) is the point of the function
            for (int i = 0; i < getWidth(); i++) {
                //Draw function
                double x = (xRight-xLeft)*((double) i)/getWidth() + xLeft;
                double y = function.apply(x);
                int j = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));
                g.drawRect(i, j, 1, 1);

                //Draw y = x
                x = (xRight-xLeft)*((double) i)/getWidth() + xLeft;
                y = x;
                j = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));
                g.drawRect(i, j, 1, 1);
            }

            double x0 = 0.2;
            int recI = (int)(getWidth()*(x0-xLeft)/(xRight-xLeft));;

            int maxIter = 100;

            int recJ1, recJ2;
            recJ1 = recJ2 = 0;

            for (int iter = 0; iter < maxIter; iter++) {
                //Start on y = x, draw vertically to function value
                double x = (xRight-xLeft)*((double) recI)/getWidth() + xLeft;
                double y = x;
                recJ1 = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));

                y = function.apply(x);
                recJ2 = getHeight()-(int)(getHeight()*(y-yBottom)/(yTop-yBottom));

                g.drawLine(recI, recJ1, recI, recJ2);

                //Draw from function horizontally to y=x

                x=y;
                int recINew = (int)(getWidth()*(x-xLeft)/(xRight-xLeft));
                g.drawLine(recI, recJ2, recINew, recJ2);

                recI = recINew;

            }

            g.setColor(Color.RED);
            g.fillOval(recI-10, recJ1-10, 20, 20);
            g.setColor(Color.BLACK);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
