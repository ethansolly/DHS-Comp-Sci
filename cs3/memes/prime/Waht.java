package memes.prime;

import memes.mafs.Mafs;
import memes.perdono.Utilz;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Random;
import java.util.function.Function;

public class Waht extends JFrame {

    private BigDecimal x1, x2, y1, y2;

    public static void main(String...args) {
        Waht waht = new Waht();
    }

    public Waht() {

        //VARIABLE INIT
        x1 = new BigDecimal(0.001643721971152d);
        x2 = new BigDecimal(0.001643721971154d);
        y1 = new BigDecimal(0.822467633298875d);
        y2 = new BigDecimal(0.822467633298877d);

        //WINDOW INIT
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
    }

    public void paint(Graphics g) {

        Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                BigDecimal x0 = x2.multiply(new BigDecimal((double)i/getWidth())).add(x1.multiply(new BigDecimal(1.0-(double)i/getWidth())));
                BigDecimal y0 = y2.multiply(new BigDecimal((double)j/getHeight())).add(y1.multiply(new BigDecimal(1.0-(double)j/getHeight())));


                //Make copies of x0 and y0
                BigDecimal x = new BigDecimal(x0.toString());
                BigDecimal y = new BigDecimal(y0.toString());

                double iter = 0;
                while ((x.pow(2).add(y.pow(2)).compareTo(new BigDecimal(4)) < 0) && iter < 1000) {
                    double xTemp = x*x - y*y + x0;
                    double yTemp = 2*x*y + y0;

                    if (x == xTemp && y == yTemp) {
                        iter = 1000;
                        break;
                    }


                    x = xTemp;
                    y = yTemp;

                    iter++;
                }

                //Avoid floating point errors
                if (iter < 1000) {
                    double log_zn = Math.log(x*x + y*y)/2;
                    double nu = Math.log(log_zn/Math.log(2))/Math.log(2);
                    iter = iter + 1 - nu;
                }

                Color c1 = palette[ (int)Math.floor(iter)%palette.length];
                Color c2 = palette[((int)Math.floor(iter)+1)%palette.length];

                double frac = iter % 1;
                double R = lerp(c1.getRed(), c2.getRed(), frac);
                double G = lerp(c1.getGreen(), c2.getGreen(), frac);
                double B = lerp(c1.getBlue(), c2.getBlue(), frac);

                g.setColor(new Color((int)R, (int)G, (int)B));

                g.drawRect(i, j, 1, 1);

            }
        }
    }

    /** Returns a double linearly interpolated between v0 and v1
     *  for t between 0 and 1.
     */
    public double lerp(double v0, double v1, double t) {
        return (1-t)*v0 + t*v1;
    }


}
