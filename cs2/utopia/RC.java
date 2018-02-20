package com.lutalica.utopia;

import java.awt.Color;
import java.util.Random;

public class RC {
	
	int R, G, B;
	Random rnd;
	
	public RC() {
		rnd = new Random();
	}
	
	///////////////////////////////////////////////////			VOID METHODS
	
	public void randomize() {
		R = rnd.nextInt(256);
		G = rnd.nextInt(256);
		B = rnd.nextInt(256);
	}
	
	public void increment() {
		R++;
		G++;
		B++;
	}
	
	///////////////////////////////////////////////////			RETURN METHODS
	
	public Color getColor() {
		return new Color(R, G, B);
	}
}
