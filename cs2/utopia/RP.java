package com.lutalica.utopia;

import java.util.Random;

public class RP {
	public int borderX, borderY, width, height, x, y;
	public int rndX1, rndX2, rndY1, rndY2;
	private Random rnd = new Random();
	
	public RP(int parBorderX, int parBorderY, int parWidth, int parHeight, int parX, int parY) {
		borderX = parBorderX;
		borderY = parBorderY;
		width = parWidth;
		height = parHeight;
	}
	
	public RP() {
		borderX = borderY = width = height = 0;
	}

	public void randForQuad(int quadrant) {
		rndX1 = rnd.nextInt(x)+borderX;
		rndY1 = rnd.nextInt(y)+borderY;
		rndX2 = rnd.nextInt(x)+borderX;
		rndY2 = rnd.nextInt(y)+borderY;
	}
	
}
