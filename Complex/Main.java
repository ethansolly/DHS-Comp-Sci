import memes.Utilz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class Main extends JFrame {

    boolean useT = false;
    boolean makePic = false;
    double t;

    private ComplexFunction function;

    double x1 = -1;
    double x2 =  2;
    double y1 = -1.5;
    double y2 =  1.5;

    double t1 = 0;
    double t2 = Math.PI;
    double tInc = 0.01;

    public static void main(String...args) {
        ComplexFunction cf = ComplexFunction.iter( (z, c) -> c.plus(z.times(z.neg().plus(c))), 1000, "Exponential Map");;
        Main main = new Main(cf);
    }

    public Main(ComplexFunction cf) {
        super();
        function = cf;
        setTitle("Complex Function Grapher : " + function.getName());
        setSize(600, 600);
        //setUndecorated(true);
        //setAlwaysOnTop(true);
        setVisible(true);
        if (makePic) {
            BufferedImage bImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            paintAll(bImg.getGraphics());
            try {
                ImageIO.write(bImg, "png", new File("C:\\Users\\ethansolly\\Documents\\GitHub\\DHS-Comp-Sci\\Complex\\" + function.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        if (useT) {
            for (t = t1; t <= t2; t += tInc) {
                graph(g);
            }
        }
        else {
            graph(g);
        }
        System.out.println("Done!");
    }

    public void graph(Graphics g) {
        for (int i = 0; i < getWidth(); i += 1) {
            for (int j = 0; j < getHeight(); j += 1) {
                double x = x2 * ((double) i / getWidth()) + x1 * (1.0 - (double) i / getWidth());
                double y = y2 * ((double) j / getHeight()) + y1 * (1.0 - (double) j / getHeight());
                Complex c = function.apply(new Complex(x, -y));
                g.setColor(c.getColor());
                g.drawRect(i, j, 1, 1);
            }
        }
    }

}
