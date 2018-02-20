package com.ethansolly.cancer;

import com.lutalica.utopia.ChangeWallpaper;
import com.lutalica.utopia.ConnectImages;

public class FavianSimulator2016 {
	public static void main(String...args) {
		String path = "resources/images/";
		String im1 = ChangeWallpaper.getID("602946"); //riches
		String im2 = ChangeWallpaper.getID("705937"); //ethab
		ConnectImages.connectByWidth(im1, im2, path + "out.jpg");
		ChangeWallpaper.wallpaperFromPicture(path + "out.jpg");
		System.out.println("NICE MEEM");
	}
}
