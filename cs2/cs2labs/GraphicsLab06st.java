package com.ethansolly.cs2labs;

// GraphicsLab06st.java
// This is the Student Version of the Lab11GRFX06 assignment.

import java.awt.*;
import java.applet.*;
import java.util.*;


@SuppressWarnings("serial")
public class GraphicsLab06st extends Applet {
	
	int circleCount;
	
	public void init() {
		Scanner sc = new Scanner(System.in);
		circleCount = sc.nextInt();
		sc.close();
	}
	
	@SuppressWarnings("unused")
	public void paint(Graphics g) {
		setSize(1000, 500);
		Circles circles = new Circles(g, circleCount);
	}
}


class Circles {
	private int circleCount;
	private Random rnd;
	private Color randomColor;
	@SuppressWarnings("unused")
	private int colorRow;
	
	private int redCount, greenCount, blueCount;
	
	public Circles(Graphics g,int c) {
		rnd = new Random();
		circleCount = c;
		redCount = 1;
		greenCount = 1;
		blueCount = 1;
	
		drawSquares(g);
		for (int k = 0; k < circleCount; k++)
			drawRandomCircle(g);
	}	
				
	public void drawSquares(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(10,100,30,30);
		g.setColor(Color.green);
		g.fillRect(10,250,30,30);
		g.setColor(Color.blue);
		g.fillRect(10,400,30,30);
	}
	
	public void drawRandomCircle(Graphics g) {
		getRandomColor();
		g.setColor(randomColor);
		if (randomColor.getRed() >= randomColor.getGreen()) {
			if (randomColor.getRed() >= randomColor.getBlue()) {
				if (redCount > 17) {
					g.fillOval(10+(redCount-17)*40, 140, 30, 30);
					redCount++;
				}
				else {
					g.fillOval(30+redCount*40, 100, 30, 30);
					redCount++;
				}
			}
			else {
				if (blueCount > 17) {
					g.fillOval(10+(blueCount-17)*40, 440, 30, 30);
					blueCount++;
				}
				else {
					g.fillOval(30+blueCount*40, 400, 30, 30);
					blueCount++;
				}
			}
		}
		else if (randomColor.getGreen() >= randomColor.getBlue()) {
			if (greenCount > 17) {
				g.fillOval(10+(greenCount-17)*40, 290, 30, 30);
				greenCount++;
			}
			else {
				g.fillOval(30+greenCount*40, 250, 30, 30);
				greenCount++;
			}
		}
		else {
			if (blueCount > 17) {
				g.fillOval(10+(blueCount-17)*40, 440, 30, 30);
				blueCount++;
			}
			else {
				g.fillOval(30+blueCount*40, 400, 30, 30);
				blueCount++;
			}
		}
	}
	
	public void getRandomColor() {
		int red = rnd.nextInt(256);
		int green = rnd.nextInt(256);
		int blue = rnd.nextInt(256);
		randomColor = new Color(red, green, blue);
	}
}


