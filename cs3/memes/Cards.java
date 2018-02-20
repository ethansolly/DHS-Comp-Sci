package memes;

import memes.perdono.Utilz;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.util.function.Function;

public class Cards extends JFrame {

    public static final int SIZE = 5;
    public static final double ERROR = Math.pow(10, -6);

    public static void main(String[] args) {
        Cards cards = new Cards();
    }

    public Cards() {
        Dimension screen = Utilz.screen;
        setSize(screen);
        setVisible(true);
    }

    public void paint(Graphics g) {

        //Iterate through possible points
        for (int j = 0; j <= SIZE; j++) {
            double pr = pr(j, m -> 1.0/(SIZE + 1 - m));
            System.out.println(j + ": " + pr);
            g.fillOval(j * getWidth() / SIZE, (int) ((1.0 - 2 * pr) * getHeight()) - 10, 20, 20);
        }
    }

    public static double[] runTrials() {
        final int TRIALS = 1000000;
        int[] scores = new int[SIZE];
        for(int i = 0; i < TRIALS; i++) {
            int N = SIZE;
            int right, choice;
            int score = 0;
            while (N > 0) {
                Random rand = new Random();
                right = rand.nextInt(N);
                choice = rand.nextInt(N);
                if (right == choice)
                    score++;
                N--;
            }
            scores[score]++;
        }
        double[] probs = new double[SIZE];
        for (int i = 0; i < SIZE; i++)
            probs[i] = scores[i]/TRIALS;
        return probs;
    }

    public static double pr(double k, Function<Integer, Double> trialProb) {
        return pr(k, trialProb, SIZE);
    }

    //K is the number of successes
    //Size is the number of items (52 for original problem)
    public static double pr(double k, Function<Integer, Double> trialProb, double size) {
        Complex C = Complex.cis(2*Math.PI/(size+1));
        Complex sum = Complex.ZERO;

        for (int l = 0; l <= size; l++) {
            Complex Cl = C.pow(l);
            Complex Clk = C.pow(-l*k);
            Complex prod = Complex.ONE;

            for (int m = 1; m <= size; m++) {
                double pm = trialProb.apply(m);
                prod = prod.times(Cl.minus(1).times(pm).plus(1));
            }

            sum = sum.plus(Clk.times(prod));
        }

        sum = sum.divide(size+1);
        return sum.mag();
    }
}

class Complex {

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    private double re;
    private double im;

    //STATIC

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex cis(double angle) {
        return new Complex(Math.cos(angle), Math.sin(angle));
    }

    public static Complex rcis(double radius, double angle) {
        return cis(angle).times(radius);
    }

    //OBJECT

    public double arg() {
        return Math.atan2(im, re);
    }

    public double mag() {
        return Math.sqrt(re*re + im*im);
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public String toString() {
        return re + " " + im + "i";
    }

    //Add

    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex plus(double d) {
        return new Complex(re + d, im);
    }

    public Complex plusI(double d) {
        return new Complex(re, im + d);
    }

    //Sub

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex minus(double d) {
        return new Complex(re - d, im);
    }

    public Complex minusI(double d) {
        return new Complex(re, im - d);
    }

    //NEG

    public Complex negative() {
        return times(-1);
    }

    //Times

    public Complex times(Complex c) {
        return new Complex(re*c.re - im*c.im, im*c.re + re*c.im);
    }

    public Complex times(double d) {
        return new Complex(re*d, im*d);
    }

    public Complex timesI(double d) {
        return new Complex(-im*d, re*d);
    }

    //Divide

    public Complex divide(Complex c) {
        double denom = c.re*c.re + c.im*c.im;
        return new Complex((re*c.re + im*c.im)/denom , (im*c.re - re*c.im)/denom);
    }

    public Complex divide(double d) {
        return new Complex(re/d, im/d);
    }

    public Complex divideI(double d) {
        return new Complex(im/d, -re/d);
    }

    //Recip

    public Complex reciprocal() {
        return ONE.divide(this);
    }

    //Powers and Roots

    public Complex pow(double d) {
        return rcis(Math.pow(mag(), d),d*arg());
    }

    public Complex sqrt() {
        return cis(arg()/2.0).times(Math.sqrt(mag()));
    }

}