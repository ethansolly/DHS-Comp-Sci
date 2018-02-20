package labs.Lab24b;// Lab24bst.java
// This is the student version of the lab24b assignment.


import java.awt.*;
import java.awt.event.*;


public class Lab24bst
{
	public static void main(String args[])
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(900,700);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.show();
	}
}

class GfxApp extends Frame
{
	public void paint(Graphics g) 
	{
		Circle c = new Circle(30, 16, 16, 10);
		int R = 0;
		int G = 0;
		int B = 0;

		int RMod = 1;
		int GMod = 1;
		int BMod = 1;

		while (true) {
			g.setColor(new Color(R%256, G%256, B%256));
			c.drawCircle(g);
			c.setScreen(this.getWidth(), this.getHeight());

			if (checkColor(RMod)) {
				RMod*=2;
				RMod = -RMod;
			}
			if (checkColor(GMod)) {
				GMod*=2;
				GMod = -GMod;
			}
			if (checkColor(BMod)) {
				BMod*=2;
				BMod = -BMod;
			}

			R+=RMod;
			G+=GMod;
			B+=BMod;

		}
	}

	public boolean checkColor(int color) {
		return color % 256 == 0;
	}
}
     
class Circle
{
	private int x;
	private int y;
	private int incX;
	private int incY;
	private int size;
	private int timeDelay;
	private int scrWidth;
	private int scrHeight;

	public Circle(int size, int incX, int incY, int timeDelay) {
		this.size = size;
		this.incX = incX;
		this.incY = incY;
		this.timeDelay = timeDelay;
	}

	private void delay(long n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void drawCircle(Graphics g) {
		g.fillOval(x, y, size, size);
		x += incX;
		y += incY;
		delay(timeDelay);
		if (hitEdge()) newData();
	}

	public boolean hitEdge() {
		return (x < 0 || x > scrWidth-size || y < 0 || y > scrHeight-size);
	}

	public void newData() {
		incX = (int) ((Math.random()*8 + 5) * Math.signum(incX));
		incY = (int) ((Math.random()*8 + 5) * Math.signum(incY));
		if (x < 0 || x > scrWidth-2*size)
			incX *= -1;
		if (y < 0 || y > scrHeight-2*size)
			incY *= -1;
	}

	public void setScreen(int width, int height) {
		scrWidth = width;
		scrHeight = height;
	}

}