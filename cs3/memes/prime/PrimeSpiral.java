package memes.prime;


import memes.Utilz;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class PrimeSpiral extends JFrame {

    public boolean[] isPrime;
    public boolean[] doesSwitch;

    public static void main(String...args) {
        PrimeSpiral ps = new PrimeSpiral();
    }

    public PrimeSpiral() {
        super("Prime Spiral");
        setSize(Utilz.screen.height, Utilz.screen.height);
        setVisible(true);
    }

    public void paint(Graphics g) {

        //Primes

        isPrime = new boolean[getWidth()*getHeight()+1];

        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(isPrime.length); i++) {
            if (isPrime[i]) {
                //iterate through multiples of primes
                for (int j = i*i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        //Switches

        doesSwitch = new boolean[getWidth()*getHeight()];

        Arrays.fill(doesSwitch, false);

        for (int i = 2; i < doesSwitch.length; i++) {
            //This is an inversion of the function for switching numbers
            //(Quarter-squares + 1, see A033638).
            //
            // This inversion is 1 if i is such a number
            // and zero otherwise.
            int inversion = (int)(Math.ceil(2*Math.sqrt(i)) - Math.ceil(2*Math.sqrt(i-1)));
            doesSwitch[i] = inversion == 1;
        }

        int n = 1;

        g.clearRect(0, 0, getWidth(), getHeight());

        //Graphics
        int x = getWidth()/2;
        int y = getHeight()/2;

        //0 = right, 1 = up, 2 = left, 3 = down
        int direction = 0;

        while ((x >= 0 && x < getWidth()) && (y >= 0 && y <= getHeight())) {
            //Mark if prime
            g.setColor((isPrime[n]) ? Color.BLACK : Color.WHITE);
            g.drawRect(x, y, 1, 1);

            if (doesSwitch[n]) {
                direction++;
                direction %= 4;
            }
            //Punnnny
            switch (direction) {
                case 0:
                    x++;
                    break;
                case 1:
                    y--;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    y++;
                    break;
            }
            n++;
        }
    }
}