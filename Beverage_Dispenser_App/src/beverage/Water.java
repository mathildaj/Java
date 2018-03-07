/*
 * Program Description: Water class information
  */

package beverage;

import java.util.*;
import java.io.*;

public class Water extends Beverage implements Serializable{
	//instance variables
	int size; //by default, 8 oz
	
	//default constructor
	public Water(){}
	//other constructor
	public Water(int size)
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
		System.out.println("Hello Water");
		String fileName = "water.dat";
		FileOutputStream f1 = null;
		ObjectOutputStream water = null;
		try
		{
			f1 = new FileOutputStream(fileName);
			water = new ObjectOutputStream(f1);
			water.writeObject(this);
			water.close();
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
		System.out.println("The size of the water is: " + this.getSize());
	}
	
	
	

}

