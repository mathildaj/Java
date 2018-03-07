/*
  * Program Description: CoffeeBean class information
  */


package beverage;

import java.util.*;
import java.io.*;

public class CoffeeBean implements Serializable{
	
	//instance variables
	private String beanType;
		
	//default constructor
	public CoffeeBean() {}
	//other constructor
	public CoffeeBean(String beanType)
	{
		this.beanType = beanType;
	}
	//getters and setters
	public String getBeanType() {
		return beanType;
	}
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}
	
	//other method
	public void printBean()
	{
		System.out.print("This type of coffee bean is: " + this.getBeanType());
	}
}
