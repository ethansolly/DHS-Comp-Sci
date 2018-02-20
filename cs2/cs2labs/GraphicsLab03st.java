package com.ethansolly.cs2labs;

//GraphicsLab03st.java
//Student Version

import java.awt.*;
import java.applet.*;
import java.util.*;

import com.lutalica.utopia.RC;
import com.lutalica.utopia.RP;
																
@SuppressWarnings("serial")
public class GraphicsLab03st extends Applet
{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	RC rand = new RC();
	Random randomNum = new Random();
	RP points = new RP();
	
	public void init() {
		setSize(width, height);
	}
	
	public void paint(Graphics g) 	
	{
		
		int radius;
		
		int rndX1, rndX2, rndY1, rndY2;
		
		int[] xPoints, yPoints;
		
		// Draw Grid
		g.drawRect(10,10,780,580);
		g.drawLine(400,10,400,590);
		g.drawLine(10,300,790,300);
		
		points = new RP(10, 10, 780, 580, 400, 300);
		// Draw Random Lines
		
		for (int i = 0; i < 100; i++) {
			rand.randomize();
			g.setColor(rand.getColor());
			g.drawLine(points.rndX1, points.rndY1, points.rndX2, points.rndY2);
		}
		
		// Draw Random Squares

		
		for (int i = 0; i < 100; i++) {
			rand.randomize();
			g.setColor(rand.getColor());
			
			g.fillRect(points.rndX1, points.rndY1, 50, 50);
		}
		
		
		// Draw Random Circles

		for (int i = 0; i < 100; i++) {
			
			radius = randomNum.nextInt(200);
			rndX1 = randomNum.nextInt(390-radius)+10;
			rndY1 = randomNum.nextInt(290-radius)+300;
			
			rand.randomize();
			g.setColor(rand.getColor());
			
			g.drawOval(rndX1, rndY1, radius, radius);
		}
		
		// Draw 3-D Box

		g.setColor(Color.GREEN);
		
		xPoints = new int[] {600, 625, 625, 600};
		yPoints = new int[] {400, 425, 475, 450};
		
		g.fillPolygon(xPoints, yPoints, 4);
		
		///////////////////////////////////////////
		
		g.setColor(Color.YELLOW);
		
		xPoints = new int[] {600, 650, 650, 625};
		yPoints = new int[] {400, 400, 425, 425};
		
		g.fillPolygon(xPoints, yPoints, 4);
		
		///////////////////////////////////////////
		
		g.setColor(Color.BLUE);
		
		xPoints = new int[] {650, 675, 650};
		yPoints = new int[] {400, 425, 425};
		
		g.fillPolygon(xPoints, yPoints, 3);
		
		//////////////////////////////////////////
		
		g.setColor(Color.RED);
		
		xPoints = new int[] {625, 675, 675, 625};
		yPoints = new int[] {425, 425, 475, 475};
		
		g.fillPolygon(xPoints, yPoints, 4);
		
		///////////////////////////////////////////

		while (true) {
			
			for (int i = 0; i < 100000; i++) {
				rndX1 = randomNum.nextInt(width-790)+790;
				rndY1 = randomNum.nextInt(590)+10;
				rndX2 = randomNum.nextInt(width-790)+790;
				rndY2 = randomNum.nextInt(590)+10;
				
				rand.randomize();
				g.setColor(rand.getColor());
				
				g.drawLine(rndX1,rndY1,rndX2,rndY2);
			}
			
			for (int i = 0; i < 100000; i++) {
				rndX1 = randomNum.nextInt(width-790)+790;
				rndY1 = randomNum.nextInt(height-590)+590;
				rndX2 = randomNum.nextInt(width-790)+790;
				rndY2 = randomNum.nextInt(height-590)+590;
				
				rand.randomize();
				g.setColor(rand.getColor());
				
				g.drawLine(rndX1,rndY1,rndX2,rndY2);
			}
		
			for (int i = 0; i < 100000; i++) {
				rndX1 = randomNum.nextInt(790)+10;
				rndY1 = randomNum.nextInt(height-590)+590;
				rndX2 = randomNum.nextInt(790)+10;
				rndY2 = randomNum.nextInt(height-590)+590;
				
				rand.randomize();
				g.setColor(rand.getColor());
				
				g.drawLine(rndX1,rndY1,rndX2,rndY2);
			}
			
			for (int i = 0; i < 100000; i++) {
				rndX1 = randomNum.nextInt(790)+10;
				rndY1 = randomNum.nextInt(590)+10;
				rndX2 = randomNum.nextInt(790)+10;
				rndY2 = randomNum.nextInt(590)+10;
				
				rand.randomize();
				g.setColor(rand.getColor());
				
				g.drawLine(rndX1,rndY1,rndX2,rndY2);
			}
			
		}
	}
}  