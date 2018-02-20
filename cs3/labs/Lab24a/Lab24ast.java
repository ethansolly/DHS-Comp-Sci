package labs.Lab24a;// Lab24ast.java
// This is the student version of the lab 24a assignment.


import java.awt.*;
import java.awt.event.*;


public class Lab24ast
{
	public static void main(String args[])
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(900,700);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.setVisible(true);
	}
}


class GfxApp extends Frame
{
	public void paint(Graphics g) 
	{
	    RegPoly[] polys = new RegPoly[6];
	    for(int i = 0; i < 6; i++) {
	        polys[i] = new RegPoly(120*(i+1), 120*(i+1), 60, i+3);
	        polys[i].drawPoly(g);
        }
	}
}
   

/** A polygon class, which encapsulates all the necessary attributes and
/*  actions to draw regular polygons of a requested number of sides.
 */

class RegPoly
{

	/**
	 * CLASS ATTRIBUTES
	 */

	//Radius of the circle
	private int radius;
	//Center x-coordinate
	private int x;
	//Center y-coordinate
	private int y;
	//Number of points or sides of the polygon
	private int pointQuantity;
	//Array of x-coordinates of the points
	private int[] pointXCoords;
	//Array of y-coordinates of the points
	private int[] pointYCoords;

	/**
	 * CLASS METHODS
	 */

	public RegPoly(int centerX, int centerY, int radius, int points) {
		x = centerX;
		y = centerY;
		this.radius = radius;
		pointQuantity = points;

		pointXCoords = new int[points];
		pointYCoords = new int[points];

		double angle = 2*Math.PI/points;

		for (int i = 0; i < points; i++) {
		    pointXCoords[i] = (int)(radius*Math.cos(angle*(i))) + x;
		    pointYCoords[i] = (int)(radius*Math.sin(angle*(i))) + y;
        }
	}

	public void drawPoly(Graphics g) {
	    g.setColor(Color.BLUE);
	    g.fillPolygon(pointXCoords, pointYCoords, pointQuantity);
    }

}


