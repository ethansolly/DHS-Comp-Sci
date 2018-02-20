package com.ethansolly.cs2labs;

// TextLab03st.java
// This is the student starting version of the TextLab03 assignment.
// Testing <main> methods are provided for the 80-point and 100-point versions.
// This means that this version will not compile as provided.


import java.util.ArrayList;


public class TextLab03st
{
	/*public static void main(String args[])
	{
		System.out.println("\nTextLab03 80-POINT VERSION\n");
		
		Matrix m1 = new Matrix();
		m1.displayMatrix("Matrix m1 Default Display");
		System.out.println();
	
		Matrix m2 = new Matrix(3,5);
		m2.displayMatrix("Matrix m2 3 X 5 Display");
		System.out.println();
		int count = 100;
		for (int r = 1; r <= m2.getRows(); r++)
		{
			for (int c = 1; c <= m2.getCols(); c++)
			{
				m2.setValue(r,c,count);
				count++;
			}
		}		
		m2.displayMatrix("Matrix m2 3 X 5 Consecutive Integers Display");
		System.out.println();
		
		Matrix m3 = new Matrix(3,3,100);				
		m3.displayMatrix("Matrix m3 3 X 3 Initialized to 100 Display");
		System.out.println();
	}*/

	public static void main(String args[])
	{
		System.out.println("\nLAB13 100-POINT VERSION\n");
		
		Matrix m1 = new Matrix();
		m1.displayMatrix("Matrix m1 Default Display");
		System.out.println();
		
		Matrix m2 = new Matrix(3,5);
		int count = 100;
		for (int r = 1; r <= m2.getRows(); r++)
		{
			for (int c = 1; c <= m2.getCols(); c++)
			{
				m2.setValue(r,c,new Integer(count));
				count++;
			}
		}		
		m2.displayMatrix("Matrix m2 3 X 5 Consecutive Integers Display");
		System.out.println();
		
		m2.resize(4,4);
		m2.displayMatrix("Matrix m2 After 4 X 4 Resizing Display");
		System.out.println();
		
		Matrix m3 = new Matrix(3,3,new Integer(100));				
		m3.displayMatrix("Matrix m3 3 X 3 Initialized to 100 Display");
		System.out.println();
	}

	
}



class Matrix
{
	
	private ArrayList<Integer> list;	// one-dimensional array stores matrix values
	private int listSize;	// total number of elements in the matrix
	private int numRows;	// number of rows in the matrix
	private int numCols;	// number of cols in the matrix
		
	public Matrix() {
		list = new ArrayList<Integer>();
		listSize = numRows = numCols = 0;
	}
	
	public Matrix(int r, int c) {
		numRows = r;
		numCols = c;
		listSize = r * c;
		list = new ArrayList<Integer>(listSize);
		for (int i = 0; i <= listSize; i++) {
			list.add(i, 0);
		}
	}
	
	public Matrix(int r, int c, int value) {
		numRows = r;
		numCols = c;
		listSize = r * c;
		list = new ArrayList<Integer>(listSize);
		for (int i = 0; i <= getRows(); i++) {
			for (int j = 0; j <= getCols(); j++) {
				list.add(value);
			}
		}
	}
	
	public int getRows() {
		return numRows;
	}
	
	public int getCols() {
		return numCols;
	}
	
	public int getSize() {
		return listSize;
	}
	
	public int getValue(int r, int c) {
		return list.get((r-1)*getCols() + c);
	}
	
	public void setValue(int r, int c, int value) {
		list.add((r-1)*getCols() + c, value);
	}
	
	public void displayMatrix(String str) {
		System.out.println(str);
		if (getSize() == 0) {
			System.out.println("Matrix has no elements");
		}
		else {
			for (int r = 1; r <= getRows(); r++) {
				for(int c = 1; c <= getCols(); c++) {
					System.out.print(list.get((r-1)*getCols() + c) + " ");
				}
				System.out.println();
			}
		}
	}
	
	public void resize(int rows, int cols) {
		Matrix temp = new Matrix(rows, cols);
		for (int i = 1; i <= temp.getRows(); i++) {
			for (int j = 1; j <= temp.getCols(); j++) {
				temp.setValue(i,j,getValue(i,j));
			}
		}
		list = temp.list;
		numRows = rows;
		numCols = cols;
	}
}