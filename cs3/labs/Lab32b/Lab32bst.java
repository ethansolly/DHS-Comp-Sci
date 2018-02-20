package labs.Lab32b;// Lab32bst.java
// The student version of the Lab32b assignment.


import java.awt.*;
import java.awt.event.*;


public class Lab32bst
{
	public static void main(String args[])
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(1024,768);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.show();
	}
}


class GfxApp extends Frame
{
	
	public void paint (Graphics g)
	{
		drawSquare1(g,getWidth(),getHeight(), getWidth(),getHeight(), 0);
	}
	
	public void drawSquare1(Graphics g, int maxX, int maxY, int width, int height, int num)
	{
		/**
		 * 	   2 1
		 * 		0
		 *     3 4
		 */
		if (width >= 2 && height >= 2 && maxX - width >= 0 && maxY - height >= 0 && maxX <= 1024 && maxY <= 768) {
			g.fillRect(maxX/3, maxY/3, width/3, height/3);
			if (num != 1) //draw 3
				drawSquare1(g, maxX / 2, maxY, width/2, height/2, 3);
		}
	}
	
	
	private void delay(double n)
	{
		for (double k = 1; k < n; k+=0.001);
	}
			
}


