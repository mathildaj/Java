/*
 * Program Description: StudentGrade class information
  */

package database;

import java.io.*;
import util.*;
import adapter.*;

public class StudentGrade implements Serializable, Globalconstantable{

	//private static final long serialVersionUID = -261266936650530801L;
	//instance variables
	private Student stu;
	private Statistics stat;
	
	//in my design, default constructor is needed (in the readReportCard method in Util.java)
	public StudentGrade() {}
	//other constructor
	public StudentGrade(Student stu, Statistics stat)
	{
		this.stu = stu;
		this.stat = stat;
	}
	
	//getters and setters
	public Student getStudent()
	{
		return this.stu;
	}
	public Statistics getStat()
	{
		return this.stat;
	}
	public void setStudent(Student stu)
	{
		this.stu = stu;
	}
	public void setStat(Statistics stat)
	{
		this.stat = stat;
	}
	
	//other methods
	//this print method can be used to interact with the user
	//it prints out the student scores and class statistics
	public void print()
	{
		//print this student's scores
		this.stu.print();
		//print class statistics
		this.stat.printStatistics();
	}
	
	//this print method prints only one student's scores
	public void printStudent()
	{
		//print this student's scores
		this.stu.print();
	}
	
	//this print method prints the class statistics
	public void printStatistics()
	{
		//print this student's scores
		this.stat.printStatistics();
	}
	
	//print out for debug purpose only if needed
	public void printForDebug()
	{
		if (Globalconstantable.DEBUG)
		{
			//print this student's scores
			this.stu.print();
			//print class statistics
			this.stat.printStatistics();
		}
		
	}
}
