/*
  * Program Description: ProxyAutomobile abstract class
  */

package adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import exception.AutoException;
import model.*;
import util.*;
import scale.*;


public abstract class ProxyAutomobile { 
	
	private static Inventory<Automobile> myList =  new Inventory<Automobile>();
	
	//-----implement methods for Creatable interface
	public void buildAuto(String fileName, String fileType) 
	{
		FileIO f1 = new FileIO(fileName);
		Automobile myAuto = new Automobile();
		
		if(fileType.equalsIgnoreCase("text"))
			myAuto = f1.buildAutoObject(fileName);
		else if(fileType.equalsIgnoreCase("properties"))
			myAuto = f1.buildAutoFromProperties(fileName);
		
		myList.addAuto(myAuto);
	}
	
	public void printAuto(String autoName)
	{
		myList.printAuto(autoName);
	}
	
	public void printAutos()
	{
		myList.printAutos();
	}
	
	//----------implement methods for AutoServer interface
	public void buildAutoFromProperties(String fileName)
	{
		buildAuto(fileName, "properties");
	}
	
	public void buildAutoFromProperties(Properties props)
	{
		FileIO f1 = new FileIO();
		Automobile myAuto = new Automobile();
		myAuto = f1.buildAutoFromProperties(props);
		
		myList.addAuto(myAuto);
	}
	
	public ArrayList<String> listAllAutos()
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		LinkedHashMap<String, Automobile> map = new LinkedHashMap<String, Automobile>();
		if(myList != null)
		{
			map = myList.getInventory();
			if(!map.isEmpty())
			{
				Set<String> keys = map.keySet();
				for(String k: keys)
				{
					returnArray.add(k); //key is the auto name
				}
			}
		}
		
		return returnArray;
	}
	
	public Automobile getAuto(String autoName)
	{
		return myList.getAuto(autoName);
	}
	
	
	
	//------implement methods for Updatable interface
	public void updateOptionSetName(String autoName, String optionSetName, String optionSetNewName)
	{
		if (myList.getAuto(autoName) != null)
			myList.getAuto(autoName).updateOptionSet(optionSetName, optionSetNewName);
	}
	
	public void updateOptionPrice(String autoName, String optionSetName, String optionName, int newPrice)
	{
		if (myList.getAuto(autoName) != null)
			myList.getAuto(autoName).updateOption(myList.getAuto(autoName).getOptionSet(optionSetName),
					optionName, optionName, newPrice);
	}
	
	
	//--------implement Editable interface
	
	//helper method for edit
	public boolean isValidInput(int ops, String[] opsValues)
	{
		if(ops == 1) //edit option set name
		{
			if (opsValues.length != 2)
				return false;
		}
		else if(ops == 2) //edit option price
		{
			if (opsValues.length != 4)
				return false;
		}
		
		for(int i = 0; i < opsValues.length; i++)
		{
			if(opsValues[i] == null || opsValues[i].isEmpty())
				return false;
		}
		return true;
	}
	
		
	//this is the method implement the Editable interface
	//and will be used by EditOptions class
	public void edit(String autoName, int ops, String[] opsValues)
	{
		if (myList.getAuto(autoName) != null && isValidInput(ops, opsValues))
		{
			String optionSetName = opsValues[0];
			
			switch(ops){
			//edit optionset name
			case 1: 
					String optionSetNewName = opsValues[1];
					myList.getAuto(autoName).updateOptionSet(optionSetName, optionSetNewName);
					//a1.updateOptionSet(optionSetName, optionSetNewName);
					break;
			//edit option price
			case 2:
					String optionName = opsValues[1];
					String optionNewName = opsValues[2];
					int newPrice = 0;
					try{
						newPrice = Integer.parseInt(opsValues[3].trim());
					}
					catch (NumberFormatException e) {
						  System.out.println(e);
					}
					myList.getAuto(autoName).updateOption(myList.getAuto(autoName).getOptionSet(optionSetName),
							optionName, optionNewName, newPrice);
					break;
			default:
				break;
				
			}
		}
	}
	
	//this method is ONLY to test the data corruption of multithreads when not using synchronization
	public void nonSynchronizedEdit(String autoName, int ops, String[] opsValues)
	{
		if (myList.getAuto(autoName) != null && isValidInput(ops, opsValues))
		//if (a1 != null && isValidInput(ops, opsValues))
		{
			String optionSetName = opsValues[0];
			
			switch(ops){
			//edit optionset name
			case 1: 
					String optionSetNewName = opsValues[1];
					myList.getAuto(autoName).updateOptionSet(optionSetName, optionSetNewName);
					break;
			//edit option price
			case 2:
					String optionName = opsValues[1];
					String optionNewName = opsValues[2];
					int newPrice = 0;
					try{
						newPrice = Integer.parseInt(opsValues[3].trim());
					}
					catch (NumberFormatException e) {
						  System.out.println(e);
					}
					myList.getAuto(autoName).updateOption(myList.getAuto(autoName).getOptionSet(optionSetName),
							optionName, optionNewName, newPrice);
					break;
			default:
				break;
				
			}
		}
	}	
	
	
	//----------------implement methods for Fixable interface
	public void fixException(int err)
	{
		AutoException e1 = new AutoException(err);
		e1.fixException(err);
	}

}
