/*
  * Program Description: Statistics class information
  */

package database;

import java.io.Serializable;
import adapter.*;

public class Statistics implements Serializable, Globalconstantable{
	//instance variables
	private int[] lowScores = new int[5];
	private int[] highScores = new int[5];
	private float[] avgScores = new float[5];
	private int numStu;
	private Student[] students;
	
	//default constructor
	public Statistics(){}
	//the other constructors
	public Statistics(Course c1){
		this.students = c1.getStudentList();
		findLow(this.students);
		findHigh(this.students);
		findAvg(this.students);
	}
	public Statistics(Student[] stuList){
		//set all the instance variables
		findLength(stuList); //set the num of students
		this.students = stuList;
		findLow(this.students);
		findHigh(this.students);
		findAvg(this.students);
	}
	
	//getters
	public int getNumStu(){
		return this.numStu;
	}
	
	public Student[] getStuList(){
		return this.students;
	}
	
	public int[] getLow(){
		return this.lowScores;
	}
	
	public int[] getHigh(){
		return this.highScores;
	}
	
	public float[] getAvg(){
		return this.avgScores;
	}
	
	//setters
	
	//set the number of students for the class
	public void findLength(Student[] stuList)
	{
		int counter = 0;
		for (int i = 0; i < stuList.length; i++)
		{
			if (stuList[i] == null)
			{
				this.numStu = counter;
				return;
			}
			else
				counter++;
		}
		this.numStu = counter;
	}
	
	//find the lowest scores for each of the tests from the student list 
	public void findLow(Student[] stuList)
	{
		//set a low to hold the lowest score
		int low = 100;
		int num = this.getNumStu();
		if (num == 0) 
		{
			//System.out.println("There is no student data to calculate lowest score.");
			System.out.println("");
		}
		else
		{
			//loop through the five scores
			for (int i = 0; i < 5; i++){
				//loop through the stuList to find the lowest score for this test
				for (int j = 0; j < num; j++) {
					if (stuList[j].getScores()[i] < low){
						low = stuList[j].getScores()[i];
					}
				}
				//add the low to lowScores array
				this.lowScores[i] = low;
				//reset the low before going back to the outer loop
				low = 100;
			}
		}
		
	}
	
	//find the highest scores for each of the tests from the student list 
	public void findHigh(Student[] stuList)
	{
		//set a low to hold the highest score
		int high = 0;
		int num = this.getNumStu();
		if (num == 0) 
		{
			//System.out.println("There is no student data to calculate highest score.");
			System.out.println("");
		}
		else
		{
			//loop through the five scores
			for (int i = 0; i < 5; i++){
				//loop through the stuList to find the lowest score for this test
				for (int j = 0; j < num; j++) {
					if (stuList[j].getScores()[i] > high){
						high = stuList[j].getScores()[i];
					}
				}
				//add the high to highScores array
				this.highScores[i] = high;
				//reset the high before going back to the outer loop
				high = 100;
			}
		}
		
	}
	
	//find the average scores for each of the tests from the student list 
	public void findAvg(Student[] stuList)
	{
		//set a sum
		int sum = 0;
		//set an avg
		float avg = 0.00f;
		int num = this.getNumStu();
		if (num == 0) 
		{
			//System.out.println("There is no student data to calculate average.");
			System.out.println("");
		}
		else
		{
			//loop through the five scores
			for (int i = 0; i < 5; i++){
				//loop through the stuList to add the scores for this test
				for (int j = 0; j < num; j++) {
					sum += stuList[j].getScores()[i];
				}
				//calculate average and add to the avgScores array
				avg = (float)(sum * 1.00 / num);
				this.avgScores[i] = avg;
				//reset the sum and avg before going back to the outer loop
				sum = 0;
				avg = 0.00f;
			}
		}
		
	}
	
	//other methods
	//this print method is for debug only, not for showing the user
	public void printForDebug() {
		if(Globalconstantable.DEBUG)
		{
			System.out.println(this.getHigh());
			System.out.println(this.getLow());
			System.out.println(this.getAvg());
		}
	}
	//this method prints out class statistics only
	public void printStatistics() {
		int num = this.getNumStu();
		String temp = "";
		System.out.print("\n");
		if (num == 0) {
			System.out.println("There is no statistics to print.");
		}
		else {
			System.out.println("Statistics for the entire class: ");
			
			//print statistics
			System.out.println("High score: ");
			String scoreData = "";
			System.out.printf("%4s%8s%8s%8s%8s", "Qu1",
					"Qu2", "Qu3", "Qu4", "Qu5");
			System.out.print("\n");
			for (int i = 0; i < 5; i++){
				scoreData += Float.toString(this.getHigh()[i]) + "   ";
			}
			System.out.println(scoreData);
			
			System.out.println("Low score: ");
			scoreData = "";
			System.out.printf("%4s%8s%8s%8s%8s", "Qu1",
					"Qu2", "Qu3", "Qu4", "Qu5");
			System.out.print("\n");
			for (int i = 0; i < 5; i++){
				scoreData += Float.toString(this.getLow()[i]) + "     ";
			}
			System.out.println(scoreData);
			
			System.out.println("Average score: ");
			scoreData = "";
			System.out.printf("%4s%8s%8s%8s%8s", "Qu1",
					"Qu2", "Qu3", "Qu4", "Qu5");
			System.out.print("\n");
			for (int i = 0; i < 5; i++){
				temp = String.format("%.2f", this.getAvg()[i]);
				scoreData += temp + "   ";
			}
			System.out.println(scoreData);
		}
	}
	
	//print statistics and every student's scores
	public void printStudentsAndStatistics() {
		Student[] stuList = this.getStuList();
		int num = this.getNumStu();
		String temp = "";
		System.out.print("\n");
		if (num == 0) {
			System.out.println("There is no student to print.");
		}
		else {
			//System.out.printf("%-3s%6s%7s%7s%7s%7s", "Stud", "Qu1",
			//				"Qu2", "Qu3", "Qu4", "Qu5");
			//System.out.print("\n");
			//print student data
			for (int i=0; i < num; i++)
			{
				//System.out.println("Report card for the student: ");
				stuList[i].print();
			}
			//print statistics
			System.out.print("\n");
			System.out.println("High score: ");
			String scoreData = "";
			for (int i = 0; i < 5; i++){
				scoreData += Float.toString(this.getHigh()[i]) + "   ";
			}
			System.out.println(scoreData);
			
			System.out.println("Low score: ");
			scoreData = "";
			for (int i = 0; i < 5; i++){
				scoreData += Float.toString(this.getLow()[i]) + "   ";
			}
			System.out.println(scoreData);
			
			System.out.println("Average: ");
			scoreData = "";
			for (int i = 0; i < 5; i++){
				temp = String.format("%.2f", this.getAvg()[i]);
				scoreData += temp + "   ";
			}
			System.out.println(scoreData);
		}
	}
	
	
	
} //end of the class

