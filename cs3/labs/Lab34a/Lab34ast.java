package labs.Lab34a;// Lab34ast.java
// The Student Records Doubly Linked List Program
// Student Version 


import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

      
public class Lab34ast
{
	public static void main(String args[]) throws IOException
	{
		DoubleList studentList = new DoubleList();
		studentList.getList();
		studentList.displayAll();
		studentList.pause();
		
		studentList.displayHonorRoll();
		studentList.pause();
		
		studentList.displayAcademicProbation();
		studentList.pause();
		
//		int studentID = getID();
//		Student2Node nodeRef = studentList.search(studentID);
//		
//		if (nodeRef == null)
//		    System.out.println("There is no student with an ID# of "+studentID+".\n");
//		else
//		{
//			studentList.displayStudent(nodeRef);   // displays the information for the found student
//			studentList.pause();			
//			studentList.delete(nodeRef);           // remove the same student from the List
//			studentList.displayAll();
//			studentList.pause();
//		}    
	}
	
	public static int getID()
	{
		Scanner input = new Scanner(System.in);	 
		System.out.print("\nEnter the 6-digit ID of the student.  { 100000 - 999999 }  -->  ");
		return input.nextInt();
	}
}


class Student2Node
{
	private String name;
	private int id;
	private int age;
	private double gpa;
	private Student2Node forward;	
	private Student2Node back;	
	
	public Student2Node (String n, int ID, int a, double g, Student2Node f, Student2Node b)
	{
		name    = n;
		id      = ID;
		age     = a;
		gpa     = g;
		forward = f;
		back    = b;
	}
	
	public String getName ()					{ return name;		}
	public int getID ()							{ return id;		}
	public int getAge ()						{ return age;		}
	public double getGPA ()						{ return gpa;		}
	public Student2Node getForward ()			{ return forward;	}
	public Student2Node getBack ()				{ return back;		}
	
	public void setName (String n)				{ name    = n;		}
	public void setID (int ID)			 		{ id      = ID;		}
	public void setAgee (int a)					{ age     = a;		}
	public void setGOA (double g)				{ gpa     = g;		}
	public void setForward (Student2Node f)		{ forward = f; 		}
	public void setBack (Student2Node b)		{ back    = b;		}
}



class DoubleList
{
	Student2Node front, back;
	DecimalFormat output;
   
	public DoubleList()
	{
		front = back = null;       
		output  = new DecimalFormat("0.000");		   
	}

	public void getList() throws IOException
	{
		FileReader inFile = new FileReader("cs3\\labs\\Lab34a\\students2.dat");
		BufferedReader inStream = new BufferedReader(inFile);	     
		String s1,s2,s3,s4;
		int age, id;
		double gpa;						
		while( ((s1 = inStream.readLine()) != null) && 
			   ((s2 = inStream.readLine()) != null) && 
			   ((s3 = inStream.readLine()) != null) &&
			   ((s4 = inStream.readLine()) != null) )	
		{
			String name = s1;
			id = Integer.parseInt(s2);
			age = Integer.parseInt(s3);
			gpa = Double.parseDouble(s4);

			Student2Node newStudent = new Student2Node(name,id,age,gpa,null,null); 
			insert(newStudent);
		}   
		inStream.close(); 
					
	}
      
    private void insert(Student2Node newStudent)
    {
		if (front == null) {
			front = back = newStudent;
			newStudent.setForward(null);
			newStudent.setBack(null);
		}

		else if (newStudent.getGPA() >= front.getGPA()) {
			front.setBack(newStudent);
			newStudent.setForward(front);
			newStudent.setBack(null);
			front = newStudent;
		}

		else {
			Student2Node temp = front;
			boolean done = false;
			while (!done && temp != null) {
				if (newStudent.getGPA() >= temp.getGPA()) {
					temp.getBack().setForward(newStudent);
					newStudent.setBack(temp.getBack());
					temp.setBack(newStudent);
					newStudent.setForward(temp);
					done = true;
				} else {
					temp = temp.getForward();
				}
			}
			if (!done) {
				back.setForward(newStudent);
				newStudent.setBack(back);
				back = newStudent;
			}
		}

    }
        	
	public void displayAll()
	{
		System.out.println("\nDISPLAYING ALL STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");

		Student2Node temp = front;
		while (temp != null) {
			System.out.println(temp.getID() + "\t" + temp.getName() + "\t" + temp.getAge() + "\t" + temp.getGPA());
			temp = temp.getForward();
		}

	}	
	
	public void displayHonorRoll()
	{
		System.out.println("\nDISPLAYING HONOR ROLL STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");

		Student2Node temp = front;
		while (temp != null && temp.getGPA() >= 3.5) {
			System.out.println(temp.getID() + "\t" + temp.getName() + "\t" + temp.getAge() + "\t" + temp.getGPA());
			temp = temp.getForward();
		}

	}	   
		
	public void displayAcademicProbation()
	{
		System.out.println("\nDISPLAYING ACADEMIC PROBATION STUDENTS");
		System.out.println("\nStudent ID#     Student Name            Age         GPA");
		System.out.println("============================================================");

		Student2Node temp = back;
		while (temp != null && temp.getGPA() < 2.0) {
			System.out.println(temp.getID() + "\t" + temp.getName() + "\t" + temp.getAge() + "\t" + temp.getGPA());
			temp = temp.getBack();
		}

	}	
	
	public void pause() 
	{   
		Scanner input = new Scanner(System.in);	 
		String dummy;
		System.out.println("\nPress <Enter> to continue.");						
		dummy = input.nextLine();								
	}

	public void displayStudent(Student2Node p)
	{

	}   

//	public Student2Node search(int studentID)
//	{
		// returns a reference to the proper Student2Node if a student with the matching ID is found
		// returns null otherwise
		
	

//	}		
	
	public void delete(Student2Node p)
	{


	}	
}    

