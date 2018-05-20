import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.function.Function;

public class Main extends JFrame {

    boolean useT = false;
    double t;

    private final ComplexFunction function = ComplexFunction.GAMMA;
    private static final Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

    double x1 = -10;
    double x2 = 10;
    double y1 = -10;
    double y2 = 10;

    double t1 = 0;
    double t2 = 2*Math.PI;
    double tInc = 0.01;

    public static void main(String...args) {
        Main main = new Main();
    }

    public Main() {
        super("Complex Function Grapher");
        setVisible(true);
    }

    private Complex[][][] vals;
    public void paint(Graphics g) {
        vals = new Complex[getWidth()][getHeight()][useT? (int)((t2-t1)/(tInc)): 1];
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
                //System.out.println("( " + x + ", " + y + ") => " + "( " + c.a + ", " + c.b + ")");
                g.setColor(c.getColor());
                g.drawRect(i, j, 1, 1);
            }
        }
    }

}
