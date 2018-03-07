/*
 * Program Description: Util class that reads in from an input file, and builds 
 * an array of Student objects. Also read and write serialized studentgrade objects.
  */

package util;

import java.io.*;
import java.util.*;

import database.*;
import adapter.*;

public class Util implements Globalconstantable{
	
	//instance variables
	private String fname = null;
	//private boolean DEBUG = true; //using the Globalconstantable instead
		
	//default constructor
	public Util(){}
	//other constructor
	public Util(String fname){
		this.fname = fname;
	}
	
	//getters and setters
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	//other methods
	
	//method reading a serialized student object from a file
	//file name has .dat extension
	public StudentGrade readReportCard(String reportCardFilePath, String sID)
	{
		String stuFileName = reportCardFilePath + sID + ".dat";
		StudentGrade s1 = new StudentGrade();
		FileInputStream f1 = null;
		ObjectInputStream stuGrade = null;
		try
		{	
			f1 = new FileInputStream(stuFileName);
			stuGrade = new ObjectInputStream(f1);
			s1 = (StudentGrade)stuGrade.readObject();
			stuGrade.close();
		}
		catch (Exception e)
		{
			System.out.println("Error reading object-- " + e.toString());
		}
		finally
		{
			try { if (f1 != null) f1.close(); } catch(IOException e) {}//closing quietly}
		}
		return s1;
		
	}
	
	//write the studentGrade object to a file
	//file name has .dat extension
	public void writeReportCard(String reportCardFilePath,StudentGrade g1)
	{
		String stuFileName = reportCardFilePath + g1.getStudent().getSID() + ".dat";
		FileOutputStream f1 = null;
		ObjectOutputStream stuGrade = null;
		try
		{
			f1 = new FileOutputStream(stuFileName);
			stuGrade = new ObjectOutputStream(f1);
			stuGrade.writeObject(g1);
			stuGrade.close();
		}
		catch (Exception e)
		{
			System.out.println("Error writing object-- " + e.toString());
		}
		finally
		{
			try { if (f1 != null) f1.close(); } catch(IOException e) {}//closing quietly}
		}
	}
	
	//will read max of 40 students based on the spec
	public Student[] readFile(String filename){
		Student[] stuList = new Student[40];
		int counter = 0;
		
		try
		{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			//read in max 40 students, ignore anything beyond that according to the spec
			System.out.println("Start reading the text file...");
			while (!eof) {
				String line = buff.readLine(); //read each line
				counter++;
				//when no more to read
				//or (counter - line1) reaches 41, exit
				if (line == null  || line.trim().isEmpty() || counter >= 42) 
					eof = true;
				else {
					if (DEBUG) //calling the debug flag. 
					//however, it may not needed if we only want to show user the results
					//it will repeatedly print out the file content every time
					//readFile method gets called. Only turn it on when you need to debug
					//I have turn it on here for the instructor to check against the run results
					{
						System.out.println("Reading " + line);
						System.out.println("Counter: " + counter);
					}
					if (counter == 1) 
						; //ignore line 1, do nothing. It's heading
					if (counter > 1) { //read student data 
						buildStudents(stuList, counter-2, line); //build stuList
					}
				}
			}
			System.out.println("End reading the text file...");
			buff.close(); //close the buffer
		}
		catch (Exception e)
		{
			System.out.println("Error -- " + e.toString());
		}
		
		return stuList;
	}//end of readFile

	
	//other methods
	//build student array
	public void buildStudents(Student[] stuList, int stuCount, String temp)
	{
		//create a new Student object
		Student stu = new Student();
		int quizCount = 0;
		String s1 = "";
		int stuScores[] = new int[5];
		//create a new StringTokenizer instance
		StringTokenizer st = new StringTokenizer(temp);
		while (st.hasMoreTokens())
		{
			//the first token is stuID
			s1 = st.nextToken();
			stu.setsID(s1);
			for (quizCount = 0; quizCount < 5; quizCount++)
			{
				s1 = st.nextToken();
				//--this is for extra credit, handle missing scores
				//on the read side, will read in as 0
				//on the print to screen, will show empty as missing
				if (s1.equals("-"))
					stuScores[quizCount] = 0;
				else
					stuScores[quizCount] = Integer.parseInt(s1);
				//---end of handling missing scores
			}
		}
		stu.setScores(stuScores);
		//add to the stuList
		stuList[stuCount] = stu;
	}
	
		

} //end of class
