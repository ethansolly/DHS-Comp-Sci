package com.ethansolly.cs2labs;

import java.applet.Applet;

// GraphicsLab01st.java
// Student starting version of the GraphicsLab01 assignment.
// Resave this program as GraphicsLab01v80 for the 80 point version.
// Repeat this process as you progress to the 90 and 100 point versions.

import java.awt.Graphics;


@SuppressWarnings("serial")
public class GraphicsLab01st extends Applet {

	public void paint(Graphics g) {
		
		//Draws a cube.
		g.drawRect(200, 100, 300, 300); //Front Face
		g.drawRect(100, 50,  300, 300); //Back Face
		g.drawLine(100, 50 , 200, 100); //
		g.drawLine(400, 50,  500, 100);
		g.drawLine(400, 350, 500, 400);
		g.drawLine(100, 350, 200, 400);
		
		
		//Draws a sphere within the cube.
		g.drawOval(150, 75, 300,300); //Main circle
		g.drawOval(250, 75, 115,300); //Tall Arcs
		g.drawOval(150, 175,300,100); //Wide Arcs
		
		//Draws a triangle with
		g.drawLine(300, 700, 900, 700); //Base
		g.drawLine(300, 700, 600, 400); //Rising Slope
		g.drawLine(600, 400, 900, 700); //Falling Slope
		g.drawLine(600, 400, 600, 700); //Tall Altitude
		g.drawLine(300, 700, 750, 550); //Rising Altitude
		g.drawLine(450, 550, 900, 700); //Falling Altitude

		
	}

}


