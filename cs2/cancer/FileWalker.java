package com.ethansolly.cancer;
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class FileWalker {
 
	
    public void walk(String path, FileWriter w) {
        try {
            File root = new File(path);
            File[] list = root.listFiles();
             
            if (list == null) return;
     
            for (File f : list) {
                if (f.isDirectory() ) {
                    walk(f.getAbsolutePath(), w);
                    w.write(f.getAbsoluteFile() + "\n");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        FileWalker fw = new FileWalker();
        File out = new File("out.dat");
        FileWriter writer = new FileWriter(out);
        writer.flush();
        fw.walk("C:\\", writer);
        writer.close();
        Scanner sc = new Scanner(out);
        for (long i = 1; i < Math.random()*out.length(); i++) {
            sc.nextLine();
        }
        File plant = new File(sc.nextLine());
        if (plant.isDirectory()) {
            File plantFile = new File(plant + "\\lol.txt");
            System.out.println(plantFile.getAbsolutePath());
            writer = new FileWriter(plantFile);
            writer.write("YOU FOUND MY MEMES!!!");
            writer.close();
        }
        sc.close();
    }
     
}