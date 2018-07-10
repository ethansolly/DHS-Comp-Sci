package memes.mafs;

import memes.Utilz;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;

public class Sequences {
    public static void main(String...args) {
        draw();
    }

    public static void draw() {
        Dwarf urist = new Dwarf();
    }


    //////////////////////////////////////////////RECAMAN////////////////////////////////////

    //Generates the Recaman sequence up to length
    public static List<Integer> recaman(int length) {
        ArrayList<Integer> ret = new ArrayList<>();
        int n = 1;
        ret.add(1); //a1 = 1
        n++;
        while (n != length+2) {
            int a_m = ret.get(ret.size()-1);
            if (a_m - n > 0 && !ret.contains(a_m - n))
                ret.add(a_m - n);
            else
                ret.add(a_m + n);
            n++;
        }
        return ret;
    }

    //Prints the Recaman sequence up to length (unending if length = -1)
    public static void printRecaman(int length) {
        ArrayList<Integer> ret = new ArrayList<>();
        int n = 1;
        ret.add(1); //a1 = 1
        n++;
        while (n != length+2) {
            int a_m = ret.get(ret.size()-1);
            System.out.println(a_m);
            if (a_m - n > 0 && !ret.contains(a_m - n))
                ret.add(a_m - n);
            else
                ret.add(a_m + n);
            n++;
        }
    }

    //Draws circles detailing the path of the recaman sequence, assuming that one unit length on the number line is p pixels
    public static void drawRecaman(Graphics g, double p, int xOffset, int height, int length, int delay) {
        //List<Integer> recaman = recaman(length);
        //System.out.println(recaman.toString());
        //int x;
        ArrayList<Integer> ret = new ArrayList<>();
        int n = 1;
        ret.add(1); //a1 = 1
        n++;
        while (n != length+2) {

            int a_i = ret.get(ret.size()-1);
            int a_j;
            if (a_i - n > 0 && !ret.contains(a_i - n)) {
                ret.add(a_i - n);
                a_j = a_i - n;
            }
            else {
                ret.add(a_i + n);
                a_j = a_i + n;
            }
            n++;

            int diff = Math.abs(a_j - a_i);
            boolean under = n%2 == 0;
            if (under)
                g.drawArc((int)(xOffset + Math.min(a_i, a_j)*p), (int)(height-diff * p/2), (int)(diff * p), (int)(diff * p), 0, -180);
            else
                g.drawArc((int)(xOffset + Math.min(a_i, a_j)*p), (int)(height-diff * p/2), (int)(diff * p), (int)(diff * p), 0, +180);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    ///////////////////////////// A229037 /////////////////////////////////////

    public static List<Integer> A229037(int length) {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(1); //a0 = 1
        ret.add(1); //a1 = 1
        for (int j = 2; j < length; j++) {
            int a = 1; //What we'll put in the sequence
            int k = 1; //The arithmetic progressor: a(j), a(j-k) and a(j-2k) cannot be in arithmetic progression
            while (j - 2*k >= 0) {
                if (2*ret.get(j-k)-ret.get(j-2*k) == a) { //if in progression
                    //increase a
                    a++;
                    //go back to start
                    k = 1;
                }
                else {
                    //Keep going
                    k++;
                }
            }
            ret.add(a);
        }
        return ret;
    }

    ///////////////////////////// A268811 ///////////////////

    public static List<Integer> A268811 (int length) {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(1); //a0 = 1
        ret.add(1); //a1 = 1
        for (int j = 2; j < length; j++) {
            int a = 1; //What we'll put in the sequence
            int k = 1; //The geometric progressor: a(j), a(j-k) and a(j-2k) cannot be in geometric progression
            while (j - 2*k >= 0) {
                if ((double)ret.get(j-k)/ret.get(j-2*k) == (double)a/ret.get(j-k)) { //if in progression
                    //increase a
                    a++;
                    //go back to start
                    k = 1;
                }
                else {
                    //Keep going
                    k++;
                }
            }
            ret.add(a);
        }
        return ret;
    }

    ////////////////////////////S000001/////////////////

    public static List<Integer> S000001 (int length, int iter) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            int l = i*i;
            int numBases = 0;
            for (int b = 2; b <= iter; b++) {
                //b = base
                //n = index of digit of square
                int sum = 0;
                for (int n = 0; n < Math.log(l)/Math.log(b); n++) {
                    int digit = (int)(l % Math.pow(b, n+1)/Math.pow(b,n));
                    sum += digit*digit;
                }
                if (sum == i)
                    numBases++;
            }
            ret.add(numBases);
        }
        return ret;
    }

    public static void printS000001(int length, int iter) {
        for (int i = 1; i < length; i++) {
            System.out.println(i + ":");
            int l = i*i;
            int numBases = 0;
            for (int b = 2; b <= iter; b++) {
                //b = base
                //n = index of digit of square
                int sum = 0;
                for (int n = 0; n < Math.log(l)/Math.log(b); n++) {
                    int digit = (int)(l % Math.pow(b, n+1)/Math.pow(b,n));
                    sum += digit*digit;
                }
                if (sum == i) {
                    System.out.println("\t" + b);
                    numBases++;
                }
            }
            System.out.println(i + ":" + numBases);
        }
    }

    public static void getNthS000001(int i, int iter) {
        int l = i*i;
        int numBases = 0;
        for (int b = 2; b <= iter; b++) {
            //b = base
            //n = index of digit of square
            int sum = 0;
            for (int n = 0; n < Math.log(l)/Math.log(b); n++) {
                int digit = (int)(l % Math.pow(b, n+1)/Math.pow(b,n));
                sum += digit*digit;
            }
            if (sum == i) {
                //System.out.println(b);
                numBases++;
            }
        }
        System.out.println(numBases);
    }


    //Collatz

    public static void drawCollatz(Graphics g, double p, int xOffset, int height, int max, int delay) {
        int x0 = 1;
        while (x0 != max) {
            int x = x0; //x0
            int i = 0;
            while (x != 1) {
                int xTemp;
                if (x % 2 == 0) //x is even
                    xTemp = x/2;
                else
                    xTemp = 3*x + 1;

                int diff = Math.abs(xTemp - x);
                boolean under = i%2 == 0;
                Rectangle rect = new Rectangle((int)(xOffset + Math.min(x, xTemp)*p), (int)(height-diff * p/2), (int)(diff * p), (int)(diff * p));
                if (under)
                    g.drawArc(rect.x, rect.y, rect.width, rect.height, 0, -180);
                else
                    g.drawArc(rect.x, rect.y, rect.width, rect.height, 0, +180);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                x = xTemp;
                i++;
            }
            x0++;
        }
    }

}


class Dwarf extends JFrame {

    public Dwarf() {
        super("Dwarf");
        setSize(900, 900);
        setVisible(true);
    }


    //private Color[] palette = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

    public void paint(Graphics g) {
        int p = 5;
        Sequences.drawRecaman(g, p, 20, getHeight()/2, -1, 1);
    }
}