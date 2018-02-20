package com.ethansolly.cs2labs;

// GraphicsLab07st.java
// This is the Student Version of the GraphicsLab07 assignment.


import java.awt.*;
import java.applet.*;



public class GraphicsLab07st extends Applet
{	
	public void paint(Graphics g) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		int s = 200;
		int x = 0;
		int y = 100;
		while (x < width) {
			drawSquare(g, s, x, y);
			x += s + 10;
			s /= 2;
		}
	}
	
	public void drawSquare(Graphics g, int s, int x, int y) {
		g.drawRect(x, y, x+s, y+s);
	}
}







