package memes.mafs;

import memes.Utilz;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Function;

public class Thonk extends JFrame {

    private final double LIM = Double.MIN_VALUE;

    public static void main(String...args) {
        Thonk t = new Thonk();
    }

    public Thonk() {
        super("Thonking");
        setSize(500, 500);
        setVisible(true);
    }

    public void paint(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);

        g.setColor(Color.BLUE);

        Function<Double, Double> f = Math::exp;
        Rectangle space = new Rectangle(-5, -5, 10, 10);
        int iter = 0;
        int max = 10;
        while (iter < max) {
            Function<Double, Double> temp = f::apply;
            Utilz.drawFunction(g, temp, getSize(), space);

            f = d -> (temp.apply(d) - Mafs.derive(temp,Double.MIN_VALUE))/d;
            iter++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
