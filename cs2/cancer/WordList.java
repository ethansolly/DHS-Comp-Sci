package com.ethansolly.cancer;

import java.util.ArrayList;

public class WordList {
	
	private ArrayList myList;
	
	public static void main(String[] args) {
		WordList wl = new WordList("apples", "pen", "ant", "turnip", "fish");
		System.out.println(wl.numWordsOfLength(6));
		
		wl.removeWordsOfLength(3);
		System.out.println(wl.getList());
	}
	
	public WordList(String...words) {
		myList = new ArrayList();
		for (String s : words)
			myList.add(s);
	}
	
	public int numWordsOfLength(int len) {
		int out = 0;
		for (Object s : myList) 
			if (((String) s).length() == len) 
				out++;
		return out;
	}
	
	public void removeWordsOfLength(int len) {
		for (int i = 0; i < myList.size(); i++) 
			if (((String) myList.get(i)).length() == len) {
				myList.remove(i);
				i--;
			}
	}
	
	public ArrayList getList() {
		return myList;
	}
}
