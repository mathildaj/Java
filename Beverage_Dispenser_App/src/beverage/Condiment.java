/*
  * Program Description: Condiment class information
  */

package beverage;

import java.util.*;
import java.io.*;

public class Condiment implements Serializable{
	
	//instance variables
	private String type;
	private int qty;
	
	//default constructor
	public Condiment() {}
	//other constructor
	public Condiment(String type, int qty)
	{
		this.type = type;
		this.qty = qty;
	}
	//getters and setters
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	//other method
	public void print()
	{
		System.out.println("The type of condiment is: " + this.getType());
		System.out.println("The qty of condiment is: " + this.getQty());
	}
	
	

}
