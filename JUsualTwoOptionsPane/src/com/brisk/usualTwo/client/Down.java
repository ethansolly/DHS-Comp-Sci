package com.brisk.usualTwo.client;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Lutalica
 * @version 1.0
 */
public class Down {
	/**Download a file!!!
	 * @param url The url to download the file from
	 * @param downloadPath The folder to download the file to
	 */
	public static void load(URL url, String downloadPath) {
		try {
			   InputStream in = new BufferedInputStream(url.openStream());
			   ByteArrayOutputStream out = new ByteArrayOutputStream();
			   byte[] buffer = new byte[1024];
			   int n = 0;
			   while (-1!=(n=in.read(buffer)))
				   out.write(buffer, 0, n);
			   out.close();
			   in.close();
			   byte[] response = out.toByteArray();
			   
			   FileOutputStream fus  = new FileOutputStream(downloadPath + url.getFile());
			   fus.write(response);
			   fus.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * @param args Needed for main method
	 */
	public static void main(String...args) {
		
	}
}
