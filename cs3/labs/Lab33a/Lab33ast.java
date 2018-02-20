package labs.Lab33a;// Lab33ast.java
// This is the student Version of the Lab33a assignment.


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Lab33ast
{
	public static void main(String args[])
	{
		GfxApp gfx = new GfxApp();
		gfx.setSize(1000,750);
		gfx.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		gfx.show();
	}
}


class GfxApp extends Frame
{
	
	private int td = 1300;		// time delay to slow down graphics display
	
	public void paint (Graphics g)
	{
		g.setFont(new Font("ARIAL",Font.BOLD,28));
		g.drawString("LAB 33A 80/100 POINT VERSION",300,50); 
		g.setFont(new Font("ARIAL",Font.BOLD,20));
		g.drawString("DRAWING A LINKED LIST AS A STACK",50,215); 
		g.drawString("DRAWING A LINKED LIST AS A QUEUE",50,415);   	// for 100 point version only
		drawStack(g);
		drawQueue(g);												// for 100 point version only
	}
	
	public void drawStack(Graphics g)
	{
		//p = new node...
		GfxNode node1 = new GfxNode(g,700,200,'P',0,td);
		node1.enterData(g,'A',0);
		node1.delay(td);
		//next = null
		node1.drawNull(g,0);
		node1.delay(td);
		//temp = p
		node1.drawPointer(g,'T',2,0);
		node1.delay(td);

		//p = new node...
		node1.drawPointer(g,'P',1,9);
		node1.delay(td);
		GfxNode node2 = new GfxNode(g,600,200,'P',0,td);
		node2.enterData(g,'B',0);
		node1.delay(td);
		//next = node1
		node2.drawLink(g, node1, 0);
		node1.delay(td);
		//temp = p
		node1.drawPointer(g,'T',2,9);
		node2.drawPointer(g,'T',2,0);
		node1.delay(td);

		//p = new node...
		node2.drawPointer(g,'P',1,9);
		node1.delay(td);
		GfxNode node3 = new GfxNode(g,500,200,'P',0,td);
		node3.enterData(g,'C',0);
		node1.delay(td);
		//next = node1
		node3.drawLink(g, node2, 0);
		node1.delay(td);
		//temp = p
		node2.drawPointer(g,'T',2,9);
		node3.drawPointer(g,'T',2,0);
		node1.delay(td);


	}
	

	public void drawQueue(Graphics g)
	{
		//p = new node...
		GfxNode node1 = new GfxNode(g,500,400,'F',0,td);
		node1.enterData(g,'A',0);
		//next = null
		node1.drawNull(g,0);
		//temp = p
		node1.drawPointer(g,'T',2,0);

		//p = new node...
		GfxNode node2 = new GfxNode(g,600,400,'P',0,td);
		node2.enterData(g,'B',0);
		node1.delay(td);
		//next = null
		node2.drawNull(g, 0);
		node1.delay(td);
		//temp.next = node2
		node1.drawNull(g,9);
		node1.delay(td);
		node1.drawLink(g,node2,0);
		node1.delay(td);
		//temp = p
		node1.drawPointer(g,'T',2,9);
		node2.drawPointer(g,'T',2,0);
		node1.delay(td);

		//p = new node...
		node2.drawPointer(g,'P',1,9);
		node1.delay(td);
		GfxNode node3 = new GfxNode(g,700,400,'P',0,td);
		node3.enterData(g,'C',0);
		node1.delay(td);
		//next = null
		node3.drawNull(g, 0);
		node1.delay(td);
		//temp.next = node2
		node2.drawNull(g,9);
		node1.delay(td);
		node2.drawLink(g,node3,0);
		node1.delay(td);
		//temp = p
		node2.drawPointer(g,'T',2,9);
		node3.drawPointer(g,'T',2,0);
		node1.delay(td);
	}

}