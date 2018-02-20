package com.ethansolly.cs2labs;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


// GraphicsLab04st.java

// The GraphicsLab04 graded assignment is open ended.
// There is no provided student version for starting, nor are there
// any files with solutions for the different point versions.

// Check the Lab assignment document for additional details.

@SuppressWarnings("serial")
public class GraphicsLab04st extends Applet {

	private boolean adeel = true;
	
	public void paint(Graphics g) {
		Classroom klab = new Classroom("K103", "KLab", 100, 100);
		Teacher coffman = new Teacher("Ms. Coffman", "CompSci", klab);
		Student adil = new Student("Adil Rasiyani", new Classroom[] {klab});
		Student ben = new Student("Ben Akhmurziyev", new Classroom[] {klab});
		
		adil.setPalette(new Color[] {Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.BLACK});
		ben.setPalette(new Color[] {Color.BLACK, new Color(169, 163, 86), Color.RED, Color.PINK});
		coffman.setPalette(new Color[] {Color.BLACK, Color.BLUE, Color.GRAY, Color.YELLOW});
		
		klab.addStudent(adil);
		klab.addStudent(ben);
		klab.setTeacher(coffman);
		klab.loadClass(g);
		
		if (adeel) {
			adil.dialog();
			System.out.println(coffman.getName() + ">>\tEthan! Adeeeeeeeeeeel!");
			coffman.dialog();
			adeel = false;
		}
		else {
			ben.dialog();
		}
	}
	
}

////////////////////////////~~CLASSES

class RoomNumber {
	private char hall;
	private int floor;
	private int room;
	
	private String name;
	
	public RoomNumber(String RoomNumber) {
		name = RoomNumber;
		hall = RoomNumber.charAt(0);
		floor = Integer.parseInt("" + RoomNumber.charAt(1));
		room = Integer.parseInt(RoomNumber.substring(2, RoomNumber.length()));
	}
	
	public char getHall() {
		return hall;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getRoom() {
		return room;
	}
	
	public String getName() {
		return name;
	}
	
	
}

@SuppressWarnings("serial")
class Classroom extends Applet {
	private String className;
	private RoomNumber room;
	private ArrayList<Student> students = new ArrayList<Student>();
	private Teacher teacher;
	private int width;
	private int height;
	
	//CONSTRUCTORS
	
	public Classroom(RoomNumber roomNumber, String name, int newWidth, int newHeight) {
		room = roomNumber;
		className = name;
		width = newWidth;
		height = newHeight;
	}
	
	public Classroom(String roomNumber, String name, int newWidth, int newHeight) {
		room = new RoomNumber(roomNumber);
		className = name;
		width = newWidth;
		height = newHeight;
	}
	
	//GET
	
	public RoomNumber getRoom() { return room; }
	public String getRoomNumber() { return room.getName(); }
	public String getRoomName() { return className; }
	public Student[] getStudents() { return (Student[])students.toArray(); }
	public Teacher getTeacher() { return teacher; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	//SET/REMOVE/CHANGE
	
	public void setTeacher(Teacher t) {
		teacher = t;
	}
	
	public void addStudent(Student s) {
		students.add(s);
	}
	
	public void changeStudent(Student s, int index) {
		students.add(index, s);
	}
	
	public void removeStudent(Student s) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).equals(s)) {
				students.set(i, new Student("NULL"));
			}
		}
	}
	
	public void removeStudent(int index) {
		students.set(index, new Student("NULL"));
	}

	//LOADER
	
	public void loadClass(Graphics g) {
		
		for (int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			s.canNowTalk(true);
			s.draw(this, g, 100*i+10, 100);
		}
		teacher.canNowTalk(true);
		teacher.draw(this, g, 100, 50);
	}
	
	public void unloadClass(Graphics g) {
		for (int i = 0; i < students.size(); i++) {
			students.get(i).canNowTalk(false);
		}
		teacher.canNowTalk(false);
		g.fillRect(0, 0, width, height);
	}
	
}


////////////////////////////LIFE

class DNA {
	private boolean[][] chain;
	private int chainLength;
	
	public DNA(int length) {
		int rnd = 0;
		chain = new boolean[length][2];
		chainLength = length;
		for (int i = 0; i < length; i++) {
			rnd = (int)(Math.random()*2);
			boolean isPurine = (rnd==0);
			
			rnd = (int)(Math.random()*2);
			boolean isAOrC = (rnd==0);
			
			chain[i][0] = isPurine;
			chain[i][1] = isAOrC;
			
			//isPurine 	&& 	isAOrC 	=> A / 0
			//isPurine 	&& 	!isAOrC => G / 1
			//!isPurine && 	isAOrC 	=> C / 2
			//!isPurine && 	!isAOrC => T / 3
		}
	}

	public int getDNA(int baseIndex) {
		boolean isPurine = chain[baseIndex][0];
		boolean isAOrC = chain[baseIndex][1];
		return ((isPurine)? ((isAOrC) ? 0 : 1) : ((isAOrC)? 2 : 3) );
	}
	
	public int getChainLength() {
		return chainLength;
	}
	
}

class Animal {
	protected DNA dna;
	public Animal(int complexity) {
		dna = new DNA(complexity);
	}
}

class Human extends Animal {

	protected String projectBinary = System.getProperty("user.dir");
	protected int bindex = projectBinary.indexOf("\\bin");
	protected String project = projectBinary.substring(0, bindex);
	
	private String name;
	private int age;
	private boolean canTalkWith = false;
	private Color[] palette = new Color[4];
	
	
	public Human(String newName) {
		super(26);
		name = newName;
		age = (int)(Math.random()*101+1);
	}
	
	public Human(String newName, int newAge) {
		super(26);
		name = newName;
		age = newAge;
	}
	
	public void setPalette(Color[] colors) {
		palette = colors;
	}
	
	protected void greet() {
		System.out.print(getName() + ">>\t");
		interactSingle("GREET", 0);
	}
	
	protected void greetWithName() {
		System.out.print(getName() + ">>\t");
		interactSingle("GREET", 0);
		System.out.print(getName() + ">>\t");
		System.out.println("My name is " + name + "!");
	}
	
	protected void aboutFood() {
		System.out.print(getName() + ">>\tMy favorite food is ");
		interactSingle("FOOD", 1);
	}
	
	protected void aboutAge() {
		System.out.print(getName() + ">>\tI am " + getAge() + " years of age "
				+ "and can legally ");
		interactSingle("LEGAL", 2);
	}
	
	protected void aboutJob() {
		System.out.print(getName() + ">>\tI work as a ");
		interactSingle("JOB", 3);
	}
	
	protected void ask(String s) {
		System.out.print(getName() + ">>\t");
		interactSingle("ASK:" + s, 4);
	}
	
	protected void displayedAsk(String s) {
		System.out.print("You>>\t" + s + "\n" + getName() + ">>\t");
		interactSingle("ASK:" + s, 4);
	}
	
	protected void killYourself() {
		System.out.print(getName() + ">>\t");
		interactMulti("KYS", 0, 1);
	}
	
	@SuppressWarnings("resource")
	public void dialog() {
		if (canTalkWith) {
			boolean exitDialog = false;
			Scanner sc = new Scanner(System.in);
			boolean hasGreeted = false;
			while (!exitDialog) {
				boolean escape = false;
				String output = "";
				System.out.print("You>>\t");
				String inputStart = sc.nextLine();
				String[] inputUpper = inputStart.split(" ");
				String[] input = inputStart.toLowerCase().split(" ");
				for (int i = 0; i < input.length; i++) {
					output = "";
					switch(input[i]) {
					case "hello" : 
						if(!hasGreeted) {
							greetWithName();
							hasGreeted = true;
						}
						else {
							output = getName() + ">>\t...Hello...again?\n";	
						}
						escape = true;
						break;
					case "food" :
						aboutFood();
						escape = true;
						break;
					case "age" :
					case "old" :
						aboutAge();
						escape = true;
						break;
					case "work" :
					case "occupation" :
					case "job" :
						aboutJob();
						escape = true;
						break;
					case "question:" :
						String question = "";
						for (int j = i+1; j < inputUpper.length; j++) {
							question += inputUpper[j] + ((j!=inputUpper.length-1)? " " : "");
						}
						ask(question);
						escape = true;
						break;
					case "kill" :
						if (input[i+1].equals("yourself"))
							killYourself();
						escape = true;
						break;
					case "bye" :
					case "goodbye" :
						output = getName() + ">>\tSee you later!\n";
						escape = exitDialog = true;
						break;
					default :
						if (i == input.length-1) {
							output = getName() + ">>\tHuh?\n";
						}
						break;
					}
					System.out.print(output);
					if (escape) {
						break;
					}
				}
			}
		}
	}
	
	private void interactSingle(String type, int dnaIndex) {
		try {
			Scanner sc = new Scanner(new File(project + "\\dialog.dat"));
			String line = sc.nextLine();
			while(!line.equals("[" + type + "]") && sc.hasNextLine()) {
				line = sc.nextLine();
			}
			if (sc.hasNextLine()) {
				for (int i = 0; i < dna.getDNA(dnaIndex) + 1; i++) {
					line = sc.nextLine();
				}
			}
			System.out.println(line);
			sc.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	
	private void interactMulti(String type, int dnaStartIndex, int dnaEndIndex) {
		try {
			Scanner sc = new Scanner(new File(project + "\\dialog.dat"));
			String line = sc.nextLine();
			while(!line.equals("[" + type + "]") && sc.hasNextLine()) {
				line = sc.nextLine();
			}
			line = sc.nextLine();
			int index = 0;
			for (int i = 0; i < dnaEndIndex - dnaStartIndex + 1; i++) {
				index+=dna.getDNA(i)*Math.pow(4, i);
			}
			if (sc.hasNextLine()) {
				for (int i = 0; i < index; i++) {
					line = sc.nextLine();
				}
			}
			System.out.println(line);
			sc.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}

	public void canNowTalk(boolean b) {
		canTalkWith = b;
	}
	
	public void invertCanTalk() {
		canTalkWith = !canTalkWith;
	}
	
	public void draw(Classroom c, Graphics g, int x, int y) {
		g.setColor(palette[1]);
		int[] xVals = new int[] {
				x, 
				x + 6, 	
				x + 6, 	
				x + 8, 
				x + 8, 
				x + 14, 
				x + 14, 
				x + 10, 
				x + 10, 
				x + 4, 
				x + 4, 
				x, 
				x
		};
		int[] yVals = new int[] {
				y, 
				y, 
				y-6, 
				y-6, 
				y,  
				y,  
				y-2,  
				y-2,  
				y-8, 
				y-8, 
				y-2, 
				y-2, 
				y
		};
		g.fillPolygon(xVals, yVals, 13);
		
		g.setColor(palette[2]);
		g.fillRect(x, y-10, 14, 2);
		
		g.setColor(palette[3]);
		g.fillRect(x, y-8, 2, 2);
		g.fillRect(x+12, y-8, 2, 2);
		g.fillRect(x+6, y-12, 2, 2);
		
		g.setColor(Color.BLACK);
		g.drawString(name, x, y-16);
	}
	
}

class Student extends Human {

	private Classroom[] classes;
	private int currentClassIndex = 0;
	
	public Student(String newName) {
		super(newName);
	}
	
	public Student(String newName, int newAge) {
		super(newName, newAge);
	}
	
	public Student(String newName, Classroom[] newClasses) {
		super(newName);
		classes = newClasses;
	}
	
	public Student(String newName, Classroom[] newClasses, int newAge) {
		super(newName, newAge);
		classes = newClasses;
	}
	
	public void gotoClass(int newClassIndex, Graphics g) {
		classes[currentClassIndex].unloadClass(g);
		classes[newClassIndex].loadClass(g);
	}
	
	@Override
	public void aboutJob() {
		System.out.println(getName() + ">>\tI'm a student!");
	}
	
}

class Teacher extends Human {
	
	@SuppressWarnings("unused")
	private Classroom domain;
	private String sub;
	
	public Teacher (String newName, String parSub, Classroom home) {
		super(newName);
		domain = home;
		sub = parSub;
	}
	
	public Teacher (String newName, String parSub, Classroom home, int newAge) {
		super(newName, newAge);
		domain = home;
		sub = parSub;
	}
	
	@Override
	protected void aboutJob() {
		System.out.println(getName() + ">>\tI'm a teacher!");
	}
	
	protected String getSub() {
		return sub;
	}
	
	@Override
	protected void ask(String s) {
		try {
			Scanner sc = new Scanner(new File("E:\\workspaces\\school_workspace\\dltst\\resources\\GraphicsLab04st\\dialog.dat"));
			String line = sc.nextLine();
			while(!line.equals("[" + getSub() + ":ASK:" + s + "]") && sc.hasNextLine()) {
				line = sc.nextLine();
			}
			if (sc.hasNextLine()) {
				for (int i = 0; i <= dna.getDNA(4); i++)
					line = sc.nextLine();
			}
			else if (getName().equals("Ms. Coffman")){
				int rand = (int)(Math.random()*4+1);
				switch (rand) {
				case 0 :
					line = "That's a contest question!";
					break;
				case 1:
					line = "...That's not on the AP.";
					break;
				case 2:
					line = "USUAL TWO OPTIONS!";
					break;
				case 3:	
					line = "No. Why? BECAUSE!";
					break;
				}
			}
			else {
				line = "I haven't the foggiest idea what you're talking aboot.";
			}
			System.out.println(line);
			sc.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	@Override
	protected void displayedAsk(String s) {
		System.out.print("You>>\t" + s + "\n" + getName() + ">>\t");
		ask(s);
	}
}