package com.ethansolly.cs2labs;

// TextLab05st.java
// The Student Records Algorithm Program
// This is the student, starting version of the TextLab05 assignment.


import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

      
public class TextLab05st
{
	public static void main(String args[]) throws IOException
	{
		List studentArray = new List(100);
		studentArray.getList();
		studentArray.display("UNSORTED LIST OF STUDENTS");
		studentArray.pause();
		
		studentArray.gpaSort(); 
		studentArray.display("STUDENTS SORTED IN DESCENDING ORDER BY GPA");
		studentArray.pause();
		
		studentArray.ageSort(); 
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY AGE");
		studentArray.pause();	
		
		studentArray.idSort();  
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID#");
		studentArray.pause();	
		
		int studentID = getID();
		int index = studentArray.search(studentID);
//		
//		if (index == -1)
//		    System.out.println("There is no student with an ID# of "+studentID+".\n");
//		else
//		{
//			studentArray.displayStudent(index);   // displays the information for the found student
//			studentArray.delete(index);           // remove the same student from the array
//			studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID# WITHOUT STUDENT# "+studentID);
//			studentArray.pause();
//		}    
	}
	
	public static int getID()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);	 
		System.out.print("\nEnter the 6-digit ID of the student.  { 100000 - 999999 }  -->  ");
		return input.nextInt();
	}
}


class Person
{
	public String name;
	public int id;
	public int age;
	public double gpa;
   
	Person(String n, int ID, int a,double g)
	{
		name = n;
		id = ID;
		age = a;
		gpa = g;
	}
}


class List
{
	private Person student[];	// stores array elements
	private int capacity;		// actual array capacity
	private int size;			// number of elements in the array
   
	public List(int c)
	{
		capacity = c;
		size = 0;
		student = new Person[capacity];            
	}

	public void getList() throws IOException
	{
		FileReader inFile = new FileReader("students2.dat");	
		BufferedReader inStream = new BufferedReader(inFile);	     
		String s1,s2,s3,s4;
		int age, id;
		double gpa;						
		int index = 0;
		while( ((s1 = inStream.readLine()) != null) && 
			   ((s2 = inStream.readLine()) != null) && 
			   ((s3 = inStream.readLine()) != null) &&
			   ((s4 = inStream.readLine()) != null) )	
		{
			String name = s1;
			id = Integer.parseInt(s2);
			age = Integer.parseInt(s3);
			gpa = Double.parseDouble(s4);

			student[index] = new Person(name,id,age,gpa);       
			index++;
		}   
		inStream.close(); 
		size = index;   					
	}
	
	private String spaces(String name)
    {
    	int tab = 24 - name.length();
    	String temp = "";
    	for (int j = 1; j <= tab; j++)
    	    temp += " ";
    	return temp;    
    }
      
	public void display(String listInfo)
	{
		DecimalFormat output = new DecimalFormat("0.000");
		System.out.println("\nDISPLAYING "+ listInfo);
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");
		     
		for (int k = 0; k < size; k++)
			System.out.println(student[k].id + "          "+student[k].name + spaces(student[k].name) + student[k].age + "          " + output.format(student[k].gpa));
	}   
	
	public void pause() 
	{   
		Scanner input = new Scanner(System.in);	 
		String dummy;
		System.out.println("\nPress <Enter> to continue.");						
		dummy = input.nextLine();								
	}

	public void displayStudent(int index)
	{
		
	}   

	private void swap(int x, int y)
	{

	}  

	public void gpaSort()
	{

	}

	public void ageSort()
	{

	}
	
	public void idSort()
	{

	}
	
	public int search(int studentID)
	{
		int k = 0;
		boolean found = false;
		while (!(k >= capacity || found)) {
			if (student[k].id == studentID) {
				found = true;
			}
			else {	k++; }
		}
		return k;
	}		
	
	public void delete(int index)
	{
		// Precondition:  "index" stores the index of a student object that exists in the "student" array.
		// Postcondition: The student object at index "index" is removed from the "student" array.
		//                All other objects in the "student" array are unaffected.
		
		

	}	
}    

