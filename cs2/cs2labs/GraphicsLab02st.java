package com.ethansolly.cs2labs;

// GraphicsLab02st.java
// This is the student, starting version of the GraphicsLab02 assignment.


import java.awt.*;
import java.applet.*;


@SuppressWarnings("serial")
public class GraphicsLab02st extends Applet
{
	public void paint(Graphics g)
	{
		
		GraphicsLab02st k = new GraphicsLab02st();
		
		int width = 980;
		int height = 630;
		
		double x1 = 10;
		double y1 = 10;
		double x2 = 990;
		double y2 = 20;
		
		g.drawRect(10,10,width,height);	
		
		//top right
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1+=9.9;
			y2+=6.4;
		}

		//bottom right
		
		x1 = 10;
		y1 = 640;
		x2 = 990;
		y2 = 630;

		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1+=9.9;
			y2-=6.4;
		}
		
		//bottom left
		
		x1 = 990;
		y1 = 640;
		x2 = 10;
		y2 = 630;
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1-=9.9;
			y2-=6.4;
		}
		
		//top left
		
		x1 = 10;
		y1 = 640;
		x2 = 20;
		y2 = 10;
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x2+=9.9;
			y1-=6.4;
		}
		
		///////////////////////////////////
		
		x1 = 0;
		y1 = 0;
		x2 = 990;
		y2 = 20;
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1+=9.9;
			y2+=6.4;
		}
		
		x1 = 10;
		y1 = 640;
		x2 = 990;
		y2 = 630;

		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1+=9.9;
			y2-=6.4;
		}
		
		x1 = 990;
		y1 = 640;
		x2 = 10;
		y2 = 630;
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x1-=9.9;
			y2-=6.4;
		}
		
		x1 = 10;
		y1 = 640;
		x2 = 20;
		y2 = 10;
		
		for (int i = 0; i < 100; i++) {
			k.draw(x1, y1, x2, y2, g);
			x2+=9.9;
			y1-=6.4;
		}
		
		
	}
	
	public void draw(double x1, double y1, double x2, double y2, Graphics g) {
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}

	
}

