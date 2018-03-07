/*
  * Program Description: ReportCardImp class information
  */

package adapter;

import database.*;
import util.*;

public abstract class ReportCardImp implements Reportcardable, Globalconstantable {

	//implement the two methods in Reportcardable Interface
	private StudentGrade[] a1 = new StudentGrade[40];
	private String filename;
	
	public ReportCardImp() {}
	
	public ReportCardImp(String inputFile)
	{
		filename = inputFile;
		buildStudentGrades(inputFile);
	}
	//set the StudentGrade array
	//this method builds the StudentGrades[] array from reading the text file
	public void buildStudentGrades(String textFile)
	{
		//get the student list from Course
		Course c1 = new Course(textFile);
		Student[] s1 = c1.getStudentList();
		//get the statistics
		Statistics st1 = new Statistics(s1);
		//build the StudentGrade array		
		for (int i = 0; i < s1.length; i++)
		{
			StudentGrade sg1 = new StudentGrade(s1[i], st1);
			a1[i] = sg1;
		}
	}
	
	public void writeReportCard(String reportcardFilePath)
	{
		Util newUtil = new Util(filename);
		//write max of 40 StudentGrade reportcards to files
		for (int i = 0; i < a1.length; i++)
		{
			//write the serialized StudentGrade object to file
			if (a1[i] != null && a1[i].getStudent()!= null)
				newUtil.writeReportCard(reportcardFilePath, a1[i]);
		}
		
	}
	
	public void readReportCard(String reportcardFilePath)
	{
		Util newUtil = new Util(filename);
		StudentGrade sg = new StudentGrade();
		//read each StudentGrade report card from serialized files
		for (int i = 0; i < a1.length; i++)
		{
			//create a new StudentGrade object from a file
			if (a1[i] != null && a1[i].getStudent()!= null)
			{
				sg = newUtil.readReportCard(reportcardFilePath, a1[i].getStudent().getSID());
				//calling StudentGrade print method to print the individual student's scores to console
				System.out.println("Report card for the student: ");
				sg.print();
				System.out.print("\n");
			}
		}
	}
	
	
	//this method implement the interface method
	//this methods prints out the statistics contained in the StudentGrade object
	public void printStatistics()
	{
		if(a1[0] != null)
		{
			//only needs to access one StudentGrade object to get the statistics
			a1[0].printStatistics();
		}
		
	}
	
	//this method implement the interface method
	//this method find the StudentGrade object by sID from the StudentGrade[] a1, 
	//then print out to the screen with only this individual student's scores
	public void printStudentScores(String sID)
	{	
		boolean keep = true;
		int i = 0;
		String searchSID = "";
		while (keep && i < a1.length)
		{
			searchSID = a1[i].getStudent().getSID();
			//System.out.println("this is the student id to be searched:" + searchSID);
			if (searchSID.equals(sID))
			{
				//print the student information
				a1[i].printStudent();
				keep = false; //exit the loop
			}
			i++;
		}
	}
	
	
}
