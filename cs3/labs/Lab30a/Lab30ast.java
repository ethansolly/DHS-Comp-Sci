package labs.Lab30a;// Lab30ast.java
// The Screen Saver Program
// Student Version



import memes.perdono.Utilz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;



public class Lab30ast
{    
	public static void main(String args[])  
	{
		GfxApp gfx = new GfxApp();
		gfx.addWindowListener(new WindowAdapter() {public void
			windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.show();
	}
}




class GfxApp extends Frame
{
	
	private int circleCount, circleSize;
	private Queue<Coord> coordQueue;

	public GfxApp()
	{
		circleCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Circles"));
		circleSize  = Integer.parseInt(JOptionPane.showInputDialog("Enter the size of Circles"));
		coordQueue = new LinkedList<>();


		//Wallpaper
		setSize(Utilz.screen);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setVisible(true);

	}


	class Coord
	{
		private int xPos;
		private int yPos;

		public Coord(int x, int y) 
		{
			xPos = x;
			yPos = y;
		}
	}
   	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		int incX = 5;
		int incY = 5;
		int diameter = circleSize;
		int timeDelay = 10;
		Circle c = new Circle(g,diameter,incX,incY,timeDelay, getWidth(), getHeight());
		while (true)
		{
			coordQueue.add(new Coord(c.getTlX(), c.getTlY()));
			c.drawCircle(g);
			c.hitEdge();
			c.newData();

			if (coordQueue.size() > circleCount) {
				Coord coord = coordQueue.remove();
				c.eraseCircle(g, coord.xPos, coord.yPos);
			}
		}

	} 
}
      
      

class Circle
{
	private int tlX;		// top-left X coordinate
	private int tlY;		// top-left Y coordinate
	private int incX;		// increment movement of X coordinate
	private int incY;		// increment movement of Y coordinate
	private boolean addX;	// flag to determine add/subtract of increment for X
	private boolean addY;	// flag to determine add/subtract of increment for Y
	private int size;		// diameter of the circle
	private int timeDelay;	// time delay until next circle is drawn

	private int xMax;
	private int yMax;

	public Circle(Graphics g, int s, int x, int y, int td, int xMax, int yMax)
	{
		incX = x;
		incY = y;
		size = s;
		addX = true;
		addY = false;
		tlX = xMax/2;
		tlY = yMax/2;
		this.xMax = xMax;
		this.yMax = yMax;
		timeDelay = td;
	}
   
	public void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();	
	}

	public void drawCircle(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(tlX,tlY,size,size);
		delay(timeDelay);
		if (addX)
			tlX+=incX;
		else
			tlX-=incX;
		if (addY)
			tlY+=incY;
		else
			tlY-=incY;
	}
   
   	 
	public void newData()
	{
		incX = (int) Math.round(Math.random() * 7 + 5);
		incY = (int) Math.round(Math.random() * 7 + 5);
	}

	public void hitEdge()
	{
		boolean flag = false;
		if (tlX < incX)
		{
			addX = true;
			flag = true;
		}
		if (tlX > xMax - (30 + incX))
		{
			addX = false;
			flag = true;
		}
		if (tlY < incY) // The +30 is due to the fact that the title bar covers the top 30 pixels of the window
		{
			addY = true;
			flag = true;
		}
		if (tlY > yMax - (30 + incY))
		{
			addY = false;
			flag = true;
		}
		if (flag)
			newData();
	}

	//ADDED METHODS
	public void eraseCircle(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, size, size);
	}

	public int getTlX() {
		return tlX;
	}

	public int getTlY() {
		return tlY;
	}

}











