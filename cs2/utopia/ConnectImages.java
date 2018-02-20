package com.lutalica.utopia;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**Class to merge two images into one, either by width or by height.
 * @author Lutalica
 *
 */
public class ConnectImages {
	
	/**
	 * @param im1 First image (left)
	 * @param im2 Second image  (right)
	 * @param outFile Name of the output file
	 */
	public static void connectByWidth(String im1, String im2, String outFile) {
		try {
			BufferedImage image = ImageIO.read(new File(im1));
			BufferedImage overlay = ImageIO.read(new File(im2));
			
			int w = image.getWidth() + overlay.getWidth();
			int h = Math.max(image.getHeight(),overlay.getHeight());
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, image.getWidth(), 0, null);
			
			ImageIO.write(combined, "JPG" , new File(outFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @param im1 First image (top)
	 * @param im2 Second image (bottom)
	 * @param outFile Name of the output file
	 */
	public static void connectByHeight(String im1, String im2, String outFile) {
		try {
			BufferedImage image = ImageIO.read(new File(im1));
			BufferedImage overlay = ImageIO.read(new File(im2));
			
			int w = Math.max(image.getWidth(),overlay.getWidth());
			int h = image.getHeight() + overlay.getHeight();
			BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			
			Graphics g = combined.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(overlay, 0, image.getHeight(), null);
			
			ImageIO.write(combined, "JPG", new File(outFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
