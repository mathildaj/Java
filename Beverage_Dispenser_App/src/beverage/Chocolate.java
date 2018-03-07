/*
 * Program Description: Chocolate class information
  */

package beverage;

import java.util.*;
import java.io.*;

public class Chocolate extends Beverage implements Serializable{
	//instance variables
	private int size; //by default, 8 oz
	
	//default constructor
	public Chocolate(){}
	//other constructor
	public Chocolate(int size)
	{
		this.size = size;
	}
		
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	//other method
	//write chocolate object to a file
	public void dispenseBev()
	{
		System.out.println("Hello Chocolate");
		String fileName = "chocolate.dat";
		FileOutputStream f1 = null;
		ObjectOutputStream chocolate = null;
		try
		{
			f1 = new FileOutputStream(fileName);
			chocolate = new ObjectOutputStream(f1);
			chocolate.writeObject(this);
			chocolate.close();
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
	
	//print method
	public void print()
	{			
		System.out.println("The size of the chocolate is: " + this.getSize());
	}
	
	
	

}
