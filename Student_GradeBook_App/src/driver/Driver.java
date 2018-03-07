/*
  * Program Description: This is a test class to test Student, Statistics, and Util classes
 */

package driver;

import database.*;
import util.*;
import adapter.*;

public class Driver {

	public static void main(String[] args) {
		
		//text file path
		String chemistry = "..\\..\\chemistry.txt";
		String computer = "..\\..\\cs.txt";
		
		//initialize two course
		Course course_chemistry = new Course(chemistry);
		Course course_computer = new Course(computer);
		
		//printing students and statistics for the two courses
		System.out.println("\nStart printing for Chemistry class...");
		course_chemistry.printStudentsAndStatistics();
		System.out.println("\nEnd printing for Chemistry class...");
	
		System.out.println("\nStart printing for Computer class...");
		course_computer.printStudentsAndStatistics();
		System.out.println("\nEnd printing for Computer class...");
		
		
		
		
		//-----------------start of ReportCards-------------------------
		String chemistry_reportcard_path = "..\\..\\chemistry_reportcard\\";
		String computer_reportcard_path = "..\\..\\computer_reportcard\\";
		
		Reportcardable rc_chemistry = new ReportCardExtend(chemistry);
		rc_chemistry.writeReportCard(chemistry_reportcard_path);
		
		Reportcardable rc_computer = new ReportCardExtend(computer);
		rc_computer.writeReportCard(computer_reportcard_path);
		
		
		System.out.println("\nStart reading and printing for ReportCard for chemistry course...\n");
		rc_chemistry.readReportCard(chemistry_reportcard_path);
		System.out.println("\nEnd reading and printing for ReportCard for chemistry course...\n");
		
		System.out.println("\nStart reading and printing for ReportCard for computer course...\n");
		rc_chemistry.readReportCard(chemistry_reportcard_path);
		System.out.println("\nEnd reading and printing for ReportCard for computer course...\n");
		
		
		
		//only print out one student for testing purpose
		//can use a loop to print out all the students if needed
		//the spec is not very clear on whether to print out all students or just one
		
		String searchSID = "1234";
		
		System.out.println("This is the student id to be searched:" + searchSID + "\n");
		
		System.out.println("Printing chemistry course scores for student:" + searchSID + "\n");
		//only print out one student's scores
		rc_chemistry.printStudentScores(searchSID);
		//print out the class statistics
		rc_chemistry.printStatistics();
		System.out.println("\n");
		System.out.println("Printing computer course scores for student:" + searchSID + "\n");
		//only print out one student's scores
		rc_computer.printStudentScores(searchSID);
		//print out the class statistics
		rc_computer.printStatistics();
		
		
		
		
		
	}

}
