package com.ethansolly.cantrip;

import java.io.*;
import java.util.Scanner;

/**
 * Created by 705937 on 4/6/2017.
 */
public class World {
    public static void main(String[] args) {
        Person adil = new Person();
        System.out.println(adil.amGoodAt());
    }
}

enum Base {
    A, T, G, C
}

class DNA {

    private static final Base[] INTBASE = { Base.A, Base.T, Base.G, Base.C };

    private Base[] code;

    public DNA(int complexity) {
        code = new Base[complexity];
        for (int i = 0; i < complexity; i++) {
            int rand = (int)(Math.random()*4);
            code[i] = INTBASE[rand];
        }
    }

    public Base getBase(int index) {
        return code[index];
    }

    public Base[] getSome(int start, int end) {
        Base[] ret = new Base[end-start];
        for (int i = 0; i < end-start; i++)
            ret[i] = code[i + start];
        return ret;
    }
}

class Organism {

    private DNA dna;

    public Organism(int complexity) {
        dna = new DNA(complexity);
    }
}

class Person extends Organism {

    private String name;
    private String prof;

    public Person() {
        super(30);
    }

    public String amGoodAt() {
        if (prof == null) {
            try {
                Scanner sc = new Scanner(new File("resources/World/worlddata.dat"));
                int range = goTo(sc, "PROF");
                prof = get(sc, range);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return prof;
    }

    private int goTo(Scanner sc, String loc) {
        String s = "null";
        while (sc.hasNextLine() && !(s = sc.nextLine()).startsWith("@" + loc));
        return Integer.parseInt(s.split("~")[1]);
    }

    private String get(Scanner sc, int range) {
        int rand = (int)(Math.random()*range);
        for (int i = 1; i < rand && sc.hasNextLine(); i++) sc.nextLine();
        return sc.nextLine();
    }

}