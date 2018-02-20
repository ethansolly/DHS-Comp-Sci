package memes;

import java.applet.Applet;
import java.awt.*;
import java.util.Scanner;

public class Kuami extends Applet {

    public static final int SIZE = 100;

    public void init() {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
    }

    public static void drawLetter (Graphics g, char c, char v, Point p) {
        switch (c) {
            case 'm':
                switch (v) {
                    case 'i':
                        g.drawLine(p.x + SIZE/2, p.y, p.x + SIZE/2, p.y + SIZE/3);
                        g.drawOval(p.x + SIZE/4, p.y + SIZE/4, SIZE/2, SIZE/2);
                        g.drawArc(p.x + SIZE/3, p.y + SIZE/3, SIZE/3, SIZE/3, 90, 180);
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'n':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'p':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 't':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'k':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case '\'':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 's':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'r':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'y':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
            case 'l':
                switch (v) {
                    case 'i':
                        break;
                    case 'I':
                        break;
                    case 'u':
                        break;
                    case 'U':
                        break;
                    case 'a':
                        break;
                    case 'A':
                        break;
                }
                break;
        }
    }
}
