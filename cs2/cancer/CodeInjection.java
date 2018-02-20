package com.ethansolly.cancer;

import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CodeInjection {
	public static void main(String[] args) {
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			Scanner sc = new Scanner(System.in);
			String in = sc.nextLine();
			engine.eval("print('"+ in + "')");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
