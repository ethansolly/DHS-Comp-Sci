package labs.Lab33a;///////////////////////////////////////////////////////////////////
// GfxNode.java
// Written by Leon Schram 05-27-03 
// This class allows creating graphical linked lists to simulate various
// linked list configurations
/////////////////////////////////////////////////////////////////////


import java.awt.*;


public class GfxNode
{
	private int x;					// x coordinate of node
	private int y;					// y coordinate of node
	private final int width;		// width of the gfx node
	private final int height;		// heigth of the gfx node
	private final int time;			// delay time between displays
	
	
	// GfxNode constructor instantiates an object and
	// stores its Top-Left coordinate (tlx,tly) information, as
	// well as the length and width of the node object.  A node object
	// with two fields is drawn at the specified coordinate.
	public GfxNode(Graphics g, int tlx, int tly, char ltr, int clr, int dt) 
	{
		x = tlx;
		y = tly;
		width = 30;
		height = 15;
		time = dt;
		drawPointer(g,ltr,1,clr);
		drawNode(g,clr);
	}
	
	
	// Method drawNode is a private helper method to draw linked list nodes.
	// This method draws a node with two fields at a location of the
	// Top-Left coordinate information in the current object.
	private void drawNode(Graphics g, int clr)
	{
		g.setColor(getColor(clr));
		g.drawRect(x,y,width,height);
		g.drawLine(x+height,y,x+height,y+height);
		delay(time);
	}


	// Method getColor a private helper method to make it easier to use colors
	// in a graphics program.	
	private Color getColor(int clr)
	{
		Color temp = Color.white;
		switch (clr)
		{
			case 0:  temp = Color.black;	break;
			case 1:  temp = Color.red; 		break;
			case 2:  temp = Color.green; 	break;
			case 3:  temp = Color.blue; 	break;
			case 4:  temp = Color.orange; 	break;
			case 5:  temp = Color.cyan; 	break;
			case 6:  temp = Color.magenta; 	break;
			case 7:  temp = Color.yellow; 	break;
			case 8:  temp = Color.pink; 	break;
			case 9:  temp = Color.white;	break;
		}
		return temp;
	}
	
	
	// Method getX returns the top-left X-coordinate of a linked list node.
	public int getx()
	{ 
		return x; 
	}
	
	
	// Method getY returns the top-left Y-coordinate of a linked list node.
	public int gety()
	{
		return y;
	}


	// Method drawPointer draws a vertical pointer down to an existing node.
	// The first pointer to a node uses OffSet value 1 and the second
	// pointer to the same node uses OffSet value 2.  The result is that
	// the second pointer is moved farther to the right.
	public void drawPointer(Graphics g, char ltr, int offSet, int clr) 
	{
		if (offSet == 1)
			offSet = 8;
		else
			offSet = 18;
		int x1 = x + offSet;
		int y1 = y - 20;
		int x2 = x1;
		int y2 = y - 2;
		g.setColor(getColor(clr));
		g.drawLine(x1,y1,x2,y2);
		g.drawLine(x2,y2,x2-3,y2-3);
		g.drawLine(x2,y2,x2+3,y2-3);
		drawLetter(g,ltr,x+offSet-4,y-32);
		delay(time);
	}


	// Method enterData draws a letter in the Data field of the GfxNode.
	public void enterData(Graphics g, char ltr, int clr) 
	{
		g.setColor(getColor(clr));
		drawLetter(g,ltr,x+3,y+3);	
		delay(time);
	}


	// Method drawLink draws a link from the current sourceNode to the
	// endNode in the specified color (clr).	
	public void drawLink(Graphics g, GfxNode endNode, int clr) 
	{
		int x1,x2;
		int y1 = this.gety()  + height/2;
		int y2 = endNode.gety() + height/2;
		g.setColor(getColor(clr));
		if (this.getx() < endNode.getx())
		{
			x1 = this.getx() + 22;
			x2 = endNode.getx() - 2;
			g.drawLine(x1,y1,x2,y2);
			g.drawLine(x2,y2,x2-3,y2-3);
			g.drawLine(x2,y2,x2-3,y2+3);
		}
		else
		{
			x1 = endNode.getx() + 8;
			x2 = this.getx() + width + 2;
			g.drawLine(x1,y1,x2,y2);
			g.drawLine(x1,y1,x1-3,y1-3);
			g.drawLine(x1,y1,x1-3,y1+3);
		}
		delay(time);
 	}
	
	
	// Method drawNull draws a diagonal g.drawLine in the Next
	// field of a list node, to indicate a NULL value.
	public void drawNull(Graphics g, int clr)
	{
		g.setColor(getColor(clr));
		g.drawLine(x+height+1,y+1,x+width-1,y+height-1);
		delay(time);
	}
	
	
	//  Method drawLetter upper-case Letter characters.  The characters
	//  are drawn in a 9x9 pixel box.
	//  The (x,y) parameters indicate the coordinate of the top-left corner
	//  of the box.  Only capital letters and numbers are drawn.
	public void drawLetter(Graphics g, char ltr, int x, int y) 
	{
		switch (ltr)
		{
			case 'A' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+4,x+8,y+4);
				break;
			case 'B' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+5,y);
				g.drawLine(x,y+8,x+5,y+8);
				g.drawLine(x,y+4,x+5,y+4);
				g.drawLine(x+5,y,x+8,y+2);
				g.drawLine(x+5,y+8,x+8,y+6);
				g.drawLine(x+5,y+4,x+8,y+2);
				g.drawLine(x+5,y+4,x+8,y+6);
				break;
			case 'C' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				break;
			case 'D' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+4,y);
				g.drawLine(x,y+8,x+4,y+8);
				g.drawLine(x+4,y,x+8,y+4);
				g.drawLine(x+4,y+8,x+8,y+4);
				break;
			case 'E' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x,y+4,x+6,y+4);
				break;
			case 'F' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+4,x+6,y+4);
				break;
			case 'G' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+6,y);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x+8,y+8,x+8,y+4);
				g.drawLine(x+8,y+4,x+4,y+4);
				break;
			case 'H' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y+4,x+8,y+4);
				break;
			case 'I' :
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x+4,y,x+4,y+8);
				break;
			case 'J' :
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x,y+8,x,y+4);
				break;
			case 'K' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+1,y+4);
				g.drawLine(x+8,y+8,x+1,y+4);
				break;
			case 'L' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y+8,x+8,y+8);
				break;
			case 'M' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y,x+5,y+5);
				g.drawLine(x+8,y+1,x+4,y+4+1);
				break;
			case 'N' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y,x+9-1,y+8);
				break;
			case 'O' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				break;
			case 'P' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+4,x+8,y+4);
				g.drawLine(x+8,y,x+8,y+4);
				break;
			case 'Q' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x+3,y+5,x+8,y+10);
				break;
			case 'R' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+4,x+8,y+4);
				g.drawLine(x+8,y,x+8,y+4);
				g.drawLine(x,y+4,x+9,y+8);
				break;
			case 'S' :
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+4,x+8,y+4);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x,y,x,y+4);
				g.drawLine(x+8,y+4,x+8,y+8);
				break;
			case 'T' :
				g.drawLine(x,y,x+8,y);
				g.drawLine(x+4,y,x+4,y+8);
				break;
			case 'U' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+8);
				g.drawLine(x,y+8,x+8,y+8);
				break;
			case 'V' :
				g.drawLine(x,y,x+4,y+8);
				g.drawLine(x+8,y,x+4,y+8);
				break;
			case 'W' :
				g.drawLine(x,y,x,y+8);
				g.drawLine(x+8,y,x+8,y+9-1);
				g.drawLine(x,y+8,x+4,y+4);
				g.drawLine(x+8,y+8,x+4,y+4);
				break;
			case 'x' :
				g.drawLine(x,y,x+9,y+8);
				g.drawLine(x,y+8,x+8,y);
				break;
			case 'y' :
				g.drawLine(x,y,x+5-1,y+4);
				g.drawLine(x+8,y,x+4,y+4);
				g.drawLine(x+4,y+4,x+4,y+8);
				break;
			case 'Z' :
				g.drawLine(x,y,x+8,y);
				g.drawLine(x,y+8,x+8,y+8);
				g.drawLine(x+8,y,x,y+8);
				break;
			default :
				g.fillRect(x,y,8,8);
		}
      
	}
	
	
	// Method delay allows viewing the sequence in which the linked lists are drawn/
	public void delay(double n)
	{
		for (double k = 1; k < n; k+=0.00001);
	}

	
}


