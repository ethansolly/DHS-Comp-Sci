import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.function.Function;

public class Main extends Applet implements MouseListener {

    boolean useT = false;
    double t;

    private final ComplexFunction function = new ComplexFunction(c -> c.pow(-1));
    private static final Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

    double x1 = -3;
    double x2 = 3;
    double y1 = -3;
    double y2 = 3;

    double t1 = -10;
    double t2 = 10;
    double tInc = 0.01;

    public void init() {
        addMouseListener(this);
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
                //System.out.println("( " + x + ", " + y + ") => " + "( " + c.a + ", " + c.b + ")");
                g.setColor(c.getColor());
                g.drawRect(i, j, 1, 1);
            }
        }
    }

    private boolean pressed = false;
    private int dx;
    private int dy;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        dx = e.getX();
        dy = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dx -= e.getX();
        dy -= e.getY();

        x1+=dx*(x2-x1)/getWidth();
        x2+=dx*(x2-x1)/getWidth();

        y1+=dy*(y2-y1)/getHeight();
        y2+=dy*(y2-y1)/getHeight();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
