// GraphicsLab08st.java
// The student, starting version of the GraphicsLab08 assignment.


import java.awt.*;
import java.applet.*;


public class GraphicsLab08st extends Applet
{	
	
	public void paint (Graphics g)
	{
		drawSquare1(g,1024,768);
	}
	
	public void drawSquare1(Graphics g, int maxX, int maxY)
	{
		int midX = maxX/2;
		int midY = maxY/2;
		int startWidth = maxX/4;
		int startHeight = maxY/4;
		int tlX = midX - (startWidth/2);
		int tlY = midY - (startHeight/2);
		g.fillRect(tlX,tlY,startWidth,startHeight);
	}
	
	public void delay(long n)
	{
		n *= 1000;
		long startDelay = System.nanoTime();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.nanoTime();
	}
			
}


