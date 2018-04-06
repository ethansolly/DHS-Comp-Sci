package memes.prime;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import com.sun.jmx.remote.internal.ArrayQueue;
import memes.prime.Aaaaa.Alphabet;

import static memes.prime.Aaaaa.Alphabet.*;

public class Aaaaa extends Applet {

    ArrayList<Alphabet> state = new ArrayList<>();

    enum Alphabet {
        x,
        f,
        p,
        m,
        l,
        r
    }

    public void init() {

        int n = 8;

        //Axiom
        List axiom = Arrays.asList(m, m, m, x);
        state.addAll(axiom);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < state.size(); j++) {
                Alphabet a = state.get(j);
                List replace;
                switch (a) {
                    case x:
                        state.remove(j);

                        replace = Arrays.asList(f, m, f, p, p, l, l, x, r, m, x, r, m, f, l, m, f, x, r, p, x);

                        state.addAll(j, replace);
                        j+=replace.size();
                        break;
                    case f:
                        state.remove(j);

                        replace = Arrays.asList(f, f);

                        state.addAll(j, replace);
                        j+=replace.size();
                        break;
                }
            }
            System.out.println(state);

            System.out.println("State " + i + " complete!");
        }
    }

    public void paint(Graphics g) {

        g.setColor(new Color(1, 50, 32));

        double x = getWidth()/2;
        double y = getHeight()/2;


        double angle = 25.0*Math.PI/180.0;

        double mag = 2;

        Stack<Double> savedX = new Stack<>();
        Stack<Double> savedY = new Stack<>();
        Stack<Double> savedAngles = new Stack<>();

        for (Alphabet a: state) {
            switch (a.name()) {
                case "f" :
                    double newX = x + mag * Math.cos(angle);
                    double newY = y - mag * Math.sin(angle);
                    g.drawLine((int)x, (int)y, (int)newX, (int)newY);
                    x = newX; y = newY;
                    break;
                case "m" :
                    angle += 25.0*Math.PI/180.0;
                    break;
                case "p" :
                    angle -= 25.0*Math.PI/180.0;
                    break;
                case "l" :
                    savedX.push(x);
                    savedY.push(y);
                    savedAngles.push(angle);
                    break;
                case "r" :
                    x = savedX.pop();
                    y = savedY.pop();
                    angle = savedAngles.pop();
                    break;
            }
        }

    }
}

class DoublePoint {

    double x, y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void set(DoublePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
