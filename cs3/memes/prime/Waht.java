package memes.prime;

import memes.mafs.Mafs;
import memes.perdono.Utilz;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Random;
import java.util.function.Function;

public class Waht extends JFrame {

    private BigDecimal x1, x2, y1, y2;

    public static void main(String...args) {
        Waht waht = new Waht();
    }

    public Waht() {

        addMouseListener(new WahtMouseListener());

        //VARIABLE INIT
        x1 = new BigDecimal(-2.5);
        x2 = new BigDecimal( 1.0);
        y1 = new BigDecimal(-1.0);
        y2 = new BigDecimal( 1.0);

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
                while ((x.pow(2).add(y.pow(2)).subtract(new BigDecimal(4)).doubleValue() < 0) && iter < 10) {

                    System.out.println(x + " " + y);

                    BigDecimal xTemp = x.pow(2).subtract(y.pow(2)).add(x0);
                    BigDecimal yTemp = new BigDecimal(2).multiply(x).multiply(y).add(y0);

                    if (x.equals(xTemp) && y.equals(yTemp)) {
                        iter = 1000;
                        break;
                    }

                    x = xTemp;
                    y = yTemp;

                    iter++;
                }

                //Avoid floating point errors
                if (iter < 1000) {
                    double log_zn = Math.log(x.pow(2).add(y.pow(2)).doubleValue())/2.0;
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


    public class WahtMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            BigDecimal dx = x2.subtract(x1);
            BigDecimal dy = y2.subtract(y1);

            BigDecimal x0 = x2.multiply(new BigDecimal((double) e.getX() / getWidth())).add(x1.multiply(new BigDecimal(1.0 - (double) e.getX() / getWidth())));
            BigDecimal y0 = y2.multiply(new BigDecimal((double) e.getY() / getHeight())).add(y1.multiply(new BigDecimal(1.0 - (double) e.getY() / getHeight())));

            x1 = new BigDecimal(x0.toString()).subtract(dx.divide(new BigDecimal(2)));
            x2 = new BigDecimal(x0.toString()).add(dx.divide(new BigDecimal(2)));

            y1 = new BigDecimal(y0.toString()).subtract(dy.divide(new BigDecimal(2)));
            y2 = new BigDecimal(y0.toString()).add(dy.divide(new BigDecimal(2)));

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
