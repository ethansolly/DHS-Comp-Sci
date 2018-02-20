package labs.Lab32a;// Lab32ast.java
// The student version of the Lab32a assignment.


import java.awt.*;
import java.awt.event.*;


public class Lab32ast
{
	public static void main(String args[])
	{
		Windows win = new Windows();
		win.setSize(1000,750);
		win.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		win.show();
	}
}


class Windows extends Frame
{
	


}


