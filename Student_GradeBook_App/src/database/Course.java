/*
 * Program Description: Course class information
  */

package database;

import util.*;
import adapter.*;

public class Course implements Globalconstantable{
	private String filename;
	private Student[] students;
	
	//default constructor
	public Course(){};
	//other constructor
	public Course(String filename)
	{
		this.filename = filename;
		//using util class to get the student list
		Util u1 = new Util(filename);
		students = u1.readFile(filename);
	}
	
	//getters and setters
	public String getFilename()
	{
		return this.filename;
	}
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	public Student[] getStudentList()
	{
		return this.students;
	}
	
	public void setStudentList(Student[] sList)
	{
		students = sList;
	}
	
	//other methods
	//this print method is for debug only
	public void printForDebug()
	{
		if (DEBUG)
		{
			Statistics s1 = new Statistics(getStudentList());
			s1.printStudentsAndStatistics();
		}
	}
				
	//calling the Statistic object print method
	//print all student's scores and statistics
	public void printStudentsAndStatistics()
	{
		Statistics s1 = new Statistics(getStudentList());
		s1.printStudentsAndStatistics();
	}
	
	//calling the Statistic object print method
	//print statistics only
	public void printStatistics()
	{
		Statistics s1 = new Statistics(getStudentList());
		s1.printStatistics();
	}
	
	

}
