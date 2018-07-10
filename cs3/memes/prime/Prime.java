package memes.prime;

import memes.Utilz;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Lutalica on 10/19/2017. Dab.
 */
public class Prime extends Applet {
    private Point current;
    private int num = 0;
    private int primeIndex = 0;
    private int direction = 0; //0 = right, 1 = left, 2 = down, 3 = down-right...
    private int minX = 0;
    private int maxX = 0;
    private int minY = 0;
    private int maxY = 0;

    private final GraphicsConfiguration gConfig = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDefaultConfiguration();

    private int to = 0;

    public void init() {
        setSize(Utilz.screen);
        current = new Point(0, 0);

        Scanner sc = new Scanner(System.in);
        //System.out.print("To what integer will we travel to? =>");
        //to = sc.nextInt();

        /*System.out.println("Generating image");
        BufferedImage bi = getGraphicsConfiguration().createCompatibleImage(maxX - minX + 1, maxY - minY + 1, Transparency.TRANSLUCENT);

        num = 0;
        while (num <= to)
            prime(bi.createGraphics());

        try {
            ImageIO.write(bi, "png", new File("E:\\IdeaProjects\\cs3\\prime.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Generated image!"); */

        //Setup for applet
        current = new Point(getWidth()/2, getHeight()/2);

    }

    public void paint(Graphics g) {
        num = 0;
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, Utilz.screen.width, Utilz.screen.height);
        //g.setColor(Color.WHITE);
        while (num < 10000000)
            prime(g);
        System.out.println("Done");

    }

    public void prime(Graphics g) {
        //Draw point
        double nextX = current.x;
        double nextY = current.y;


        /**
         //Trees!
         nextX += Math.sin(direction);
         nextY += Math.cos(direction);
         */

         //Oooh!
         direction %= 4;
         switch (direction) {
         case 0:
         nextX++;
         break;
         case 1:
         nextY--;
         break;
         case 2:
         nextX--;
         break;
         case 3:
         nextY++;
         break;
         }

        /**
         //Epilepsy.exe
         direction %= 4;
         switch (direction) {
         case 0:
         nextX+=primeIndex;
         break;
         case 1:
         nextY-=primeIndex;
         break;
         case 2:
         nextX-=primeIndex;
         break;
         case 3:
         nextY+=primeIndex;
         break;
         }
         */

        /**
        direction %= 8;
        switch (direction) {
            case 0:
                nextX++;
                break;
            case 1:
                nextX++;
                nextY--;
                break;
            case 2:
                nextY--;
                break;
            case 3:
                nextX--;
                nextY--;
                break;
            case 4:
                nextX--;
                break;
            case 5:
                nextX--;
                nextY++;
                break;
            case 6:
                nextY++;
                break;
            case 7:
                nextX++;
                nextY++;
                break;
        }
        */


        Point next = new Point((int)nextX, (int)nextY);
        draw(g, current, next);


        /**
         //Wrap
         if (nextX > getWidth() || nextX < 0)
         nextX %= getWidth();
         if (nextY > getWidth() || nextY < 0)
         nextY %= getWidth();
         */
        current.setLocation(nextX, nextY);
        minX = (int) Math.min(minX, nextX);
        minY = (int) Math.min(minY, nextY);
        maxX = (int) Math.max(maxX, nextX);
        maxY = (int) Math.max(maxY, nextY);


        //Increment num
        num++;
        //System.out.println(num);
        if (isPrime(num)) {
            direction++;
            primeIndex++;
        }

        double factor = 1000000.0;
        //g.setColor(new Color((int)(Color.BLUE.getRGB()*(1-(num % factor)/factor) + Color.RED.getRGB()*((num % factor)/(factor)))));

    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    private void draw(Graphics g, Point p1, Point p2) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
