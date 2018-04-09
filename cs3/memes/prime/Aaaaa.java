package memes.prime;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import com.sun.jmx.remote.internal.ArrayQueue;
import memes.prime.Aaaaa.Alphabet;

import static memes.prime.Aaaaa.Alphabet.*;

public class Aaaaa extends JApplet implements MouseListener {


    private ArrayList<Alphabet> state = new ArrayList<>();

    private double X;
    private double Y;
    private int dx;
    private int dy;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        dx = e.getX();
        dy = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dx -= e.getX();
        dy -= e.getY();

        X-=dx;
        Y-=dy;

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    enum Alphabet {
        x,
        y,
        f,
        g,
        p,
        m,
        l,
        r,
        c0,
        c1,
        c2,
    }

    LSystem lSystem;

    public void init() {

        X = getWidth()/2.0;
        Y = getHeight()/2.0;

        lSystem = LSystem.SIERPINSKI;

        addMouseListener(this);

        int n = 6;

        //Axiom
        List axiom = lSystem.getAxiom();
        state.addAll(axiom);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < state.size(); j++) {
                Alphabet a = state.get(j);
                if (lSystem.hasRule(a)) {
                    state.remove(j);
                    List replace = lSystem.getRule(a);
                    state.addAll(j, replace);
                    j += replace.size();
                }
            }

            System.out.println("State " + i + " complete!");
        }
    }

    public void paint(Graphics g) {

        double baseAngle = lSystem.getBaseAngle();
        double angle = 0;

        double mag = 10;

        Stack<Double> savedX = new Stack<>();
        Stack<Double> savedY = new Stack<>();
        Stack<Double> savedAngles = new Stack<>();

        for (Alphabet a: state) {
            switch (a.name()) {
                case "g" :
                case "f" :
                    double newX = X + mag * Math.cos(angle);
                    double newY = Y - mag * Math.sin(angle);
                    g.drawLine((int)X, (int)Y, (int)newX, (int)newY);
                    X = newX; Y = newY;
                    break;
                case "m" :
                    angle -= baseAngle;
                    break;
                case "p" :
                    angle += baseAngle;
                    break;
                case "l" :
                    savedX.push(X);
                    savedY.push(Y);
                    savedAngles.push(angle);
                    break;
                case "r" :
                    X = savedX.pop();
                    Y = savedY.pop();
                    angle = savedAngles.pop();
                    break;
                case "c0" :
                    g.setColor(new Color(150, 75, 0));
                    break;
                case "c1" :
                    g.setColor(new Color(1, 50, 32));
                    break;
                case "c2" :
                    g.setColor(Color.GREEN);
                    break;
            }
        }

    }
}

class LSystem {

    private static HashMap HEIGHWAY_DRAGON_MAP = new HashMap<>();
    public static final LSystem HEIGHWAY_DRAGON = new LSystem(
            Arrays.asList(f, x),
            HEIGHWAY_DRAGON_MAP,
            Math.PI/2.0
    );

    private static HashMap KEVS_TREE_MAP = new HashMap<>();
    public static final LSystem KEVS_TREE = new LSystem(
            Arrays.asList(f),
            KEVS_TREE_MAP,
            22.0*Math.PI/180.0
    );

    private static HashMap SIERPINSKY_MAP = new HashMap<>();
    public static final LSystem SIERPINSKI = new LSystem(
            Arrays.asList(f, m, g, m, g),
            SIERPINSKY_MAP,
            2.0*Math.PI/3.0
    );


    static {
        HEIGHWAY_DRAGON_MAP.put(x, Arrays.asList(x, p, y, f, p));
        HEIGHWAY_DRAGON_MAP.put(y, Arrays.asList(m, f, x, m, y));

        KEVS_TREE_MAP.put(f, Arrays.asList(c0, f, f, m, l, c1, m, f, p, f, p, f, r, p, l, c2, p, f, m, f, m, f, r));

        SIERPINSKY_MAP.put(f, Arrays.asList(f,m,g,p,f,p,g,m,f));
        SIERPINSKY_MAP.put(g, Arrays.asList(g,g));
    }



    private List axiom;
    private Map<Alphabet, List> rules;
    private double baseAngle;

    public LSystem(List axiom, Map<Alphabet, List> rules, double baseAngle) {
        this.axiom = axiom;
        this.rules = rules;
        this.baseAngle = baseAngle;
    }

    public List getAxiom() {
        return axiom;
    }

    public Map<Alphabet, List> getRules() {
        return rules;
    }

    public List getRule(Alphabet a) {
        return rules.get(a);
    }

    public double getBaseAngle() {
        return baseAngle;
    }

    public boolean hasRule(Alphabet a) {
        return rules.containsKey(a);
    }

}