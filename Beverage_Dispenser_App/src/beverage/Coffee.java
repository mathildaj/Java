/*
 * Program Description: Coffee class information
  */

package beverage;

import java.util.*;
import java.io.*;

public class Coffee extends Beverage implements Serializable{
	//instance variables
	private ArrayList<CoffeeBean> beans; 
	private String flavor, flavor1, flavor2;
	private int size;
	private ArrayList<Condiment> extra;
	
	//default constructor
	public Coffee(){}
	//other constructor
	public Coffee(ArrayList<CoffeeBean> beans, String flavor1, String flavor2,
				int size, ArrayList<Condiment> extra)
	{
		this.beans = beans;
		this.flavor1 = flavor1;
		this.flavor2 = flavor2;
		this.size = size;
		this.extra = extra;
	}
	
	public Coffee(ArrayList<CoffeeBean> beans, String flavor,
			int size, ArrayList<Condiment> extra)
	{
		this.beans = beans;
		this.flavor = flavor;
		
		this.size = size;
		this.extra = extra;
	}
	//getters and setters
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	
	public ArrayList<CoffeeBean> getBeans() {
		return beans;
	}
	public void setBeans(ArrayList<CoffeeBean> beans) {
		this.beans = beans;
	}
	public String getFlavor1() {
		return flavor1;
	}
	public void setFlavor1(String flavor1) {
		this.flavor1 = flavor1;
	}
	public String getFlavor2() {
		return flavor2;
	}
	public void setFlavor2(String flavor2) {
		this.flavor2 = flavor2;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public ArrayList<Condiment> getExtra() {
		return extra;
	}
	public void setExtra(ArrayList<Condiment> extra) {
		this.extra = extra;
	}
	
	//other method
	//write coffee object to a file
	public void dispenseBev()
	{
		System.out.println("Hello Coffee");
		String fileName = "coffee.dat";
		FileOutputStream f1 = null;
		ObjectOutputStream coffee = null;
		try
		{
			f1 = new FileOutputStream(fileName);
			coffee = new ObjectOutputStream(f1);
			coffee.writeObject(this);
			coffee.close();
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
		if(this.getFlavor1().length() > 0 && this.getFlavor2().length() > 0)
		{
			System.out.println("The flavor is the mix of: " +
					this.getFlavor1() + " and " +
					this.getFlavor2());
		}
		else
		{
			System.out.println("The flavor is: " + this.getFlavor());
					
		}
		
		System.out.println("The sizeof the coffee is: " + this.getSize());
	}
	
	
	

}
