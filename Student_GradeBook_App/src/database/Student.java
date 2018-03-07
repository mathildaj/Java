/*
 * Program Description: Student class information, including studentID and scores.
  */

package database;
import java.io.*;
import adapter.*;

public class Student implements Serializable, Globalconstantable{
	
	//instance variables
	private String sID;
	private int scores[] = new int[5];
	
	//default constructor
	public Student(){}
	//other constructor
	public Student(String sID, int scores[])
	{
		this.sID = sID;
		this.scores = scores;
	}
	//getters and setters
	public String getSID()
	{
		return sID;
	}
	public int[] getScores()
	{
		return scores;
	}
	
	public void setsID(String sID) 
	{
		this.sID = sID;
	}
	public void setScores(int[] scores) 
	{
		this.scores = scores;
	}
	
	//other methods
	public void print() {
		String stuData = "";
		stuData = this.getSID() + "   ";
		String temp = "";
		//System.out.println("Report card for the student: ");
		System.out.printf("%3s%6s%7s%7s%7s%7s", "StuID", "Qu1",
				"Qu2", "Qu3", "Qu4", "Qu5");
		System.out.print("\n");
		for (int i = 0; i < 5; i++){
			//--this is for extra credit, handle missing scores
			//on the read side, will read in as 0
			//on the write, will show empty as missing
			if (this.getScores()[i] == 0)
			{
				temp = "   ";
			}
			else
			{
				temp = String.format("%3d", this.getScores()[i]);
			}
			stuData +=temp + "    ";        
		}
		
		System.out.println(stuData);
	}
	
	//this print method is for debug only, not for showing the user
	public void printForDebug() {
		if(Globalconstantable.DEBUG)
		{
			System.out.println(this.getSID());
			System.out.println(this.getScores());
		}
	}
}
