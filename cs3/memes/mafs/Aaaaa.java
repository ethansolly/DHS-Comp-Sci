package memes.mafs;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import memes.Utilz;
import memes.mafs.Aaaaa.Alphabet;

import static memes.mafs.Aaaaa.Alphabet.*;

public class Aaaaa extends JFrame {


    private ArrayList<Alphabet> state = new ArrayList<>();

    private double X;
    private double Y;

    enum Alphabet {
        w,
        x,
        y,
        z,

        f,
        g,
        fl,
        fr,

        p,
        m,

        l,
        r,

        cr,
        co,
        cy,
        cg,
        cc,
        ci,
        cp,
        cb,
        cw
    }

    LSystem lSystem;

    public static void main(String...args) {
        Aaaaa aaaaa = new Aaaaa();
    }

    public Aaaaa() {

        setSize(Utilz.screen);

        lSystem = LSystem.BROT;

        int n = 9;

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

        setVisible(true);
    }

    public void paint(Graphics g) {

        //for (int t = 1; t <= 240; t++) {

            g.clearRect(0, 0, getWidth(), getHeight());

            X = 0;
            Y = getHeight()/2;

            ArrayList<Alphabet> stateClone = new ArrayList<>(state);

            double baseAngle = lSystem.getBaseAngle();
            double angle = 0;

            double mag = 2;

            Stack<Double> savedX = new Stack<>();
            Stack<Double> savedY = new Stack<>();
            Stack<Double> savedAngles = new Stack<>();

            for (Alphabet a : stateClone) {
                switch (a.name()) {
                    case "g":
                    case "f":
                        double newX = X + mag * Math.cos(angle);
                        double newY = Y - mag * Math.sin(angle);
                        g.drawLine((int) X, (int) Y, (int) newX, (int) newY);
                        X = newX;
                        Y = newY;
                        break;
                    case "m":
                        angle -= baseAngle;
                        break;
                    case "p":
                        angle += baseAngle;
                        break;
                    case "l":
                        savedX.push(X);
                        savedY.push(Y);
                        savedAngles.push(angle);
                        break;
                    case "r":
                        X = savedX.pop();
                        Y = savedY.pop();
                        angle = savedAngles.pop();
                        break;
                    case "cr":
                        g.setColor(Color.RED);
                        break;
                    case "co":
                        g.setColor(Color.ORANGE);
                        break;
                    case "cy":
                        g.setColor(Color.YELLOW);
                        break;
                    case "cg":
                        g.setColor(Color.GREEN);
                        break;
                    case "cc":
                        g.setColor(Color.CYAN);
                        break;
                    case "ci":
                        g.setColor(Color.BLUE);
                        break;
                    case "cw":
                        g.setColor(Color.WHITE);
                        break;
                    case "cb":
                        g.setColor(Color.BLACK);
                        break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        //}

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
            Arrays.asList(f),
            SIERPINSKY_MAP,
            Math.PI/3.0
    );

    private static HashMap KOCH_MAP = new HashMap<>();
    public static final LSystem KOCH = new LSystem(
            Arrays.asList(f),
            KOCH_MAP,
            Math.PI/2.0
    );

    private static HashMap CROSS_MAP = new HashMap<>();
    public static final LSystem CROSS = new LSystem(
            Arrays.asList(x,y,x,y,x,y,x,p,x,y,x,y,x,y,x,p,x,y,x,y,x,y,x,p,x,y,x,y,x,y,x),
            CROSS_MAP,
            Math.PI/2.0
    );

    private static HashMap LACE_MAP = new HashMap<>();
    public static final LSystem LACE = new LSystem(
            Arrays.asList(w),
            LACE_MAP,
            Math.PI/6.0
    );

    private static HashMap ERICA_MAP = new HashMap<>();
    public static final LSystem ERICA = new LSystem(
            Arrays.asList(x, y),
            ERICA_MAP,
            Math.PI/22.0
    );

    private static HashMap ISLAND_MAP = new HashMap<>();
    public static final LSystem ISLAND = new LSystem(
            Arrays.asList(f, p, f, p, f, p, f),
            ISLAND_MAP,
            Math.PI/2.0
    );
    
    private static HashMap BROT_MAP = new HashMap<>();
    public static final LSystem BROT = new LSystem(
            Arrays.asList(f, g),
            BROT_MAP,
            Math.PI/4.0
    );

    private static HashMap FLY_MAP = new HashMap<>();
    public static final LSystem FLY = new LSystem(
            Arrays.asList(fl),
            FLY_MAP,
            Math.PI/2.0
    );


    static {
        HEIGHWAY_DRAGON_MAP.put(x, Arrays.asList(x, p, y, f, p));
        HEIGHWAY_DRAGON_MAP.put(y, Arrays.asList(m, f, x, m, y));

        KEVS_TREE_MAP.put(f, Arrays.asList(f, f, m, l, m, f, p, f, p, f, r, p, l, p, f, m, f, m, f, r));

        SIERPINSKY_MAP.put(f, Arrays.asList(g, m, f, m, g));
        SIERPINSKY_MAP.put(g, Arrays.asList(f, p, g, p, f));

        KOCH_MAP.put(f, Arrays.asList(f,p,f,m,f,m,f,p,f));

        CROSS_MAP.put(f, Arrays.asList());
        CROSS_MAP.put(x, Arrays.asList(f,x,p,f,x,p,f,x,f,y,m,f,y,m));
        CROSS_MAP.put(y, Arrays.asList(p,f,x,p,f,x,f,y,m,f,y,m,f,y));

        LACE_MAP.put(w, Arrays.asList(p,p,p,x,m,m,f,m,m,z,f,x,p));
        LACE_MAP.put(x, Arrays.asList(m,m,m,w,p,p,f,p,p,y,f,w,m));
        LACE_MAP.put(y, Arrays.asList(p,z,f,x,m,m,f,m,m,z,p,p,p));
        LACE_MAP.put(z, Arrays.asList(m,y,f,w,p,p,f,p,p,y,m,m,m));

        ERICA_MAP.put(x, Arrays.asList(x, f));
        ERICA_MAP.put(y, Arrays.asList(x, l, m, m, m, m, f, y, r, p, f, y));

        ISLAND_MAP.put(f, Arrays.asList(cb, f, p, cw, g, m, cb, f, f, p, f, p, f, f, p, f, cw, g,p, cb, f,f,m, cw, g,p, cb, f,f,m,f,m,f,f,m,f, cw, g,m,cb,f,f,f));
        ISLAND_MAP.put(g, Arrays.asList(cb, g, g, g, g, g, g));
        
        BROT_MAP.put(f, Arrays.asList(f, l, m, g, f, r, l, p, f, g, r, f));
        BROT_MAP.put(g, Arrays.asList(g, l, p, f, g, r, l, m, g, f, r, g));


        FLY_MAP.put(fl, Arrays.asList(fl, f, p, fr, f, fr, p, f, fl, m, f, m, fl));
        FLY_MAP.put(fr, Arrays.asList(p, f, p, fr, f, m, fl, f, fl, m, f, fr));

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
