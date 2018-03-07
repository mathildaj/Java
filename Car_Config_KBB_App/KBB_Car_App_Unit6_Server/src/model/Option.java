/* 
 * Program Description: Option class
*/

package model;

import java.io.*;
import java.util.Arrays;


//inner class Option----------------------------
public class Option implements Serializable, Comparable<Option>{
	
	//instance variables
	private String name;
	private int price;
	
	//default constructor
	protected Option(){}
	//other constructor
	protected Option(String name, int price)
	{
		this.name = name;
		this.price = price;
	}
	
	//getters and setters
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected int getPrice() {
		return price;
	}
	protected void setPrice(int price) {
		this.price = price;
	}
	
	//other methods
	
	//implement the Comparable interface for sorting
	//this has to be public
	public int compareTo(Option opt)
	{
		return new String(this.name).compareTo(new String(opt.name));
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("Option name: ").append(this.getName()).append("\n");
		builder.append("Option price: ").append(this.getPrice()).append("\n");
		return builder.toString();
	}
	
	protected String print()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("Option name: ").append(this.getName()).append("\n");
		builder.append("Option price: ").append(this.getPrice()).append("\n");
		return builder.toString();
	}
	
} //the end of inner class Option------------------------------------
	