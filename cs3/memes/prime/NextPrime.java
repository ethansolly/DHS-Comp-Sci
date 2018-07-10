package memes.prime;

import memes.Utilz;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class NextPrime extends JFrame {
    public static void main(String...args) {
        NextPrime np = new NextPrime();
    }

    private BigInteger[] vals;

    public NextPrime() {

        setSize(Utilz.screen);

        vals = new BigInteger[getWidth()];

        //Calculate vals
        for (int x = 0; x < getWidth(); x++) {
            BigInteger prod = BigInteger.ONE;

            //Sieve of Eratosthenes
            boolean[] primes = new boolean[x + 1]; //integers from 0 to x
            Arrays.fill(primes, true);

            for (int i = 2; i <= x; i++) {
                if (primes[i]) {
                    for (int j = i * i; j <= x; j += i) {
                        primes[j] = false;
                    }
                }
            }

            for (int i = 2; i <= x; i++) {
                if (primes[i]) {
                    prod = prod.multiply(BigInteger.valueOf((long) Math.pow(i, Math.floor(Math.log(x) / Math.log(i)))));
                }
            }

            vals[x] = prod;

        }


        setVisible(true);
    }

    public void paint(Graphics g) {
        //Display vals
        for (int x = 1; x < getWidth(); x++) {
            BigInteger max = vals[vals.length-1];
            double ratio = max.divide(vals[x]).doubleValue();
            int y = (int)((1-ratio)*getHeight());
            g.drawRect(x, y, 1, 1);
        }
    }



}
