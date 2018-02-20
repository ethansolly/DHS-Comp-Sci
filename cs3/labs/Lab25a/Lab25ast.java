package labs.Lab25a;// Lab25ast
// This is the student version of the Lab25 assignment.
// Lab 25 is a very open-ended lab assignment.  This student version intentionally provides
// little help.  It is your job to demonstrate knowledge of inheritance and composition with this assignment.


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Lab25ast
{
	
	public static void main(String args[])
	{
		System.out.println("\nLAB25a 80/100-POINT VERSION\n");
		
		Computer c = new Computer("Ethan's computer", Computer.OS.WINDOWS);
		c.start();
	}	
	
}

class Computer extends Tech {

	private String name;
	private ArrayList<String> users;
	private OS os;
	private boolean isBooting;

	private Tech motherboard = new Tech() {

		private Tech cpu;
		private Tech ram;

		@Override
		public void start() {
			cpu.start();
			ram.start();
		}

		@Override
		public void work(Function f) {
			cpu.work(f);
			ram.work(f);
		}

		@Override
		public void stop() {
			ram.stop();
			cpu.stop();
		}
	};

	public enum OS {
		WINDOWS,
		MACOSX,
		LINUX,
		UNIX,
		MCP
	}

	public Computer(String name, OS os) {



		this.name = name;
		this.os = os;
		users = new ArrayList<>();
		users.add("ADMIN");
	}


	@Override
	public void start() {

		motherboard.start();

		System.out.println("BOOT -- Press B for BIOS, Press C to continue");
		char c = new Scanner(System.in).next().charAt(0);
		if (c == 'b') bios();
		else {
			isBooting = true;
			load(10);
			isBooting = false;
			System.out.println();
			System.out.println("Running " + os.name() + " version " + Math.random());
			System.out.println("Signed in as " + users.get(0));
		}
	}

	@Override
	public void work(Function f) {}

	@Override
	public void stop() {}

	public void bios() {
		isBooting = false;
		System.out.println("Starting BIOS");
		JFrame jf = new JFrame("BIOS");
		jf.add(new JCheckBox(""));
		jf.setVisible(true);
	}

	public void load(int num) {
		for (int i = 0; i < num && isBooting; i++) {
			System.out.print('*');
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					File users = new File("labs/Lab25a/users");
					FileWriter fw = new FileWriter(users);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

class Tech implements ITechnology {

	@Override
	public void start() {}

	@Override
	public void work(Function f) {}

	@Override
	public void stop() {}
}

interface ITechnology {
	boolean isIrritating = true;
	void start();
	void work(Function f);
	void stop();
}