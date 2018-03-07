package model;

import java.util.LinkedHashMap;
import java.util.Set;

public class Inventory <T extends Automobile> {
	private LinkedHashMap<String, T> lmp;
		
	//default constructor
	public Inventory()
	{
		lmp = new LinkedHashMap<String, T>();
	}
	
	//getter
	public LinkedHashMap<String, T> getInventory()
	{
		return this.lmp;
	}
	
	//get an auto
	public T getAuto(String autoName)
	{
		if(lmp.containsKey(autoName))
			return lmp.get(autoName);
		else
			return null;
	}
	
	//add a new auto
	public void addAuto(T a1)
	{
		//if first time
		if (lmp.isEmpty())
			lmp.put(a1.getName(), a1);
		//else, needs to check for key collision
		else if (!lmp.containsKey(a1.getName()))
		{
			lmp.put(a1.getName(), a1);
		}
	
	}
	
	//add an auto with the same name
	public void replaceAuto(String autoName, T a1)
	{
		if(autoName.equals(a1.getName()))
		{
			if (lmp.containsKey(autoName))
				//replace
				lmp.put(a1.getName(), a1);
		}
	}
	
	//remove an auto
	public void removeAuto(T a1)
	{
		if (lmp.containsKey(a1.getName()))
			lmp.remove(a1.getName());
	}
	
	//print all the autos in the list
	public void printAutos()
	{
		Set<String> keys = lmp.keySet();
		for(String k: keys)
		{
			System.out.print("\n");
			System.out.println("This is the  " + k + ": ");
			System.out.println("\n");
			System.out.print(lmp.get(k));
		}
	}

	//print an auto
	public void printAuto(T a1)
	{
		
		if (lmp.containsKey(a1.getName()))
		{
			System.out.println("\n");
			System.out.println("This is the  " + a1.getName() + ": ");
			System.out.println("\n");
			System.out.print(a1);
		}
	}
	
	//print an auto
	public void printAuto(String autoName)
	{
		if (lmp.containsKey(autoName))
			System.out.print(lmp.get(autoName));
	}

}
