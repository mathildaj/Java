/*
 * Program Description: Automobile class
  */

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Automobile implements Serializable{
	
	private static final long serialVersionUID = 2402286483974780971L;
		
	//instance variables
	private String name;
	private String make;
	private String model;
	private int basePrice;
	private ArrayList<OptionSet> optionSets;
	private ArrayList<Option> choices;
	private int setCount;
	
	//default constructor
	public Automobile()
	{
		this.name = "";
		this.make = "";
		this.model = "";
		this.basePrice = 0;
		this.setCount = 0;
		this.optionSets = new ArrayList<OptionSet>();
		this.choices = new ArrayList<Option>();
	}
	//other constructors
	public Automobile(String name, String make, String model, int basePrice, int setCount,
					 ArrayList<OptionSet> sets, ArrayList<Option> choices)
	{
		this.name = name;
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.setCount = setCount;
		this.optionSets = sets;
		this.choices = choices;
	}
	
	public Automobile(String name, String make, String model, int basePrice, int setCount,
			 ArrayList<OptionSet> sets)
	{		
		this.name = name;
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.setCount = setCount;
		this.optionSets = sets;
		setChoices(sets);
	}
	
	//regular getters and setters
	
	public String getMake()
	{
		return this.make;
	}
	public void setMake(String make)
	{
		this.make = make;
	}
	public String getModel()
	{
		return this.model;
	}
	public void setModel(String model)
	{
		this.model = model;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getBasePrice()
	{
		return this.basePrice;
	}
	public void setBasePrice(int basePrice)
	{
		this.basePrice = basePrice;
	}
	public ArrayList<OptionSet> getOptionSets()
	{
		return this.optionSets;
	}
	public void setOptionSets(ArrayList<OptionSet> sets)
	{
		this.optionSets = sets;
	}
	public int getSetCount()
	{
		return this.setCount;
	}
	public void setSetCount(int setCount)
	{
		this.setCount = setCount;
	}
	
	//get and set choices and prices
	public int getOptionChoicePrice(String setName)
	{
		int price = 0;
		OptionSet set = this.getOptionSet(setName);
		if(set != null)
		{
			price =  set.getOptionChoice().getPrice();
		}
		
		return price;
	}
	
	public int getTotalPrice()
	{
		int total = this.getBasePrice();
		if (this.choices != null)
		{
			for(int i = 0; i < this.choices.size(); i++)
			{
				total += this.choices.get(i).getPrice();
			}
		}
		
		return total;
			
	}
	
	//get the choice for the OptionSet
	public String getOptionChoice(String setName)	
	{
		String result = "";
		OptionSet set = this.getOptionSet(setName);
		if(set != null)
		{
			return set.getOptionChoice().getName();
		}
				
		return result;
	}
	//set the choice for the OptionSet
	public void setOptionChoice(String setName, String optionName)
	{
		int find = this.findOptionSet(setName);
		if(find != -1)
		{
			this.optionSets.get(find).setOptionChoice(optionName);
			OptionSet set = this.getOptionSet(setName);
			Option option = set.getOption(optionName);
			if(option != null)
				this.choices.add(find, option);
			else
			{ //this is temporary exception handling. Will need to put in the custom exception handling class
				System.out.println("No such Option. Please choose again: ");
				Scanner input = new Scanner(System.in);
				String choice = input.nextLine();
				this.setOptionChoice(setName, choice);
			}
		}
		else
		{
			System.out.print("No such OptionSet.");
		}
		
	}
	
	
	//set the choice for the OptionSet
	public void setOptionChoice(OptionSet set, String optionName)
	{
		int find = this.findOptionSet(set.getName());
		if(find != -1)
		{
		
			Option option = set.getOption(optionName);
			if(option != null)
				this.choices.add(find, option);
			else
			{ //this is temporary exception handling. Will need to put in the custom exception handling class
				System.out.println("No such Option. Please choose again: ");
				Scanner input = new Scanner(System.in);
				String choice = input.nextLine();
				this.setOptionChoice(set.getName(), choice);
			}
		}
		else
		{
			System.out.print("No such OptionSet.");
		}
		
	}
	
	//set the choices arraylist for the auto
	public void setChoices(ArrayList<OptionSet> sets)
	{
		this.choices = new ArrayList<Option>();
		
		for(int i = 0; i < sets.size(); i++)
		{
			if(sets.get(i) != null)
					this.choices.add(i, sets.get(i).getOptionChoice());
		}
	}
	
			
	//other methods-----------------
	
	//create an optionSet, and add it to the OptionSet arraylist
	public void createOptionSet(int index, String optionSetName, int optCount)
	{
		int max = this.getSetCount();
		if (index >=0 && index < max)
		{
			OptionSet set = new OptionSet(optionSetName, optCount);
			this.optionSets.add(index, set);
		}
	}
	
	//set the values of OptionSet by its name, and all the Option array info 
	public void setOptionSet(OptionSet set, String optionSetName, int optCount, 
				ArrayList<String> optionNames, ArrayList<Integer> optionPrices)
	{
		set.setName(optionSetName);
		set.setOptCount(optCount); //reset the Option array count
		for (int i = 0; i < optionNames.size(); i++)
		{
			set.setOption(i, optionNames.get(i), optionPrices.get(i));
			//update choice
			set.setOptionChoice(optionNames.get(i));
		}
	}
	
	//set the Option in the optionSet's option arraylist
	public void setOption(OptionSet set, int index, String optionName, int price)
	{
		set.setOption(index, optionName, price);
		//update choice
		//set.setOptionChoice(optionName);
	}
	
	//other getters--------------------------
	
	//get OptionSet by index
	public OptionSet getOptionSet(int index)
	{
		if (this.optionSets != null && index < this.optionSets.size()
			&& this.optionSets.get(index) != null)
			return this.optionSets.get(index);
		return null;
	}
	//get OptionSet by optionset name
	public OptionSet getOptionSet(String optionSetName)
	{
		if (this.optionSets != null)
		{
			for (int i = 0; i < this.optionSets.size(); i++)
			{
				if (this.optionSets.get(i).getName().equals(optionSetName))
				{
					return this.optionSets.get(i);
				}
			}
		}
		return null; //cannot find it, then return null
	}
	
	//get OptionSet name
	public String getOptionSetName(int index)
	{
		OptionSet set = getOptionSet(index);
		if(set != null)
		{
			String name = set.getName();
			return name;
		}
		else
		{
			return "";
		}
	}
	
	//get Options given OptionSet
	public ArrayList<Option> getOptions(int index)
	{
		OptionSet set = getOptionSet(index);
		if(set != null)
		{
			ArrayList<Option> options = set.getOpts();
			return options;
		}
		return null;
	}
	
	public Option getOption(OptionSet set, String optionName)
	{
		return set.getOption(optionName);
	}
	
	//get OptionSet name
	public String getOptionName(OptionSet set, int index)
	{
		Option option = set.getOption(index);
		if(option != null)
			return option.getName();
		else
			return "";
	}
	
	//find methods---------------
	
	//find whether an optionSet with a certain name exists
	//return the index that optionSet in the optionSet array
	public int findOptionSet(String optionSetName)
	{
		if (this.optionSets != null)
		{
			for (int i = 0; i < this.optionSets.size(); i++)
			{
				if (this.optionSets.get(i).getName().equals(optionSetName))
				{
					return i;
				}
			}
		}
		return -1; //cannot find it
	}
	//find whether an option with a certain name exists 
	//return the index that option in the option array
	public int findOption(OptionSet set, String optionName)
	{
		return set.findOption(optionName);
	}
	
	//delete------------------------------------
	
	
	//delete an optionSet by set name
	public void deleteOptionSet(String optionSetName)
	{
		int find = findOptionSet(optionSetName);
		
		if ( find != -1)
		{
			//this.optionSets.set(find, null);
			this.optionSets.remove(find);
			//remove the choice accordingly
			if(find < this.choices.size())
				this.choices.remove(find);
		}
	}
	
	//delete an optionSet by set
	public void deleteOptionSet(OptionSet set)
	{
		this.deleteOptionSet(set.getName());
	}
	
	//delete an optionSet by set index
	public void deleteOptionSet(int index)
	{
		int max = this.getSetCount();
		
		if (index >= 0 && index < this.optionSets.size())
		{
			this.optionSets.remove(index);
			//remove the choice accordingly
			if(index < this.choices.size())
				this.choices.remove(index);
		}
	}
	
	//delete an option by Option name
	public void deleteOption(OptionSet set, String optionName)
	{
		set.deleteOption(optionName);
	}
	
	//delete an option by Option index
	public void deleteOption(OptionSet set, int index)
	{
		set.deleteOption(index);
	}

	//update(find and set)----------------------------------
	public void updateOptionSet(String optionSetName, String optionSetNewName, int optionNewCount, 
							ArrayList<String> optionNames, ArrayList<Integer> optionPrices)
	{
		int find = this.findOptionSet(optionSetName);
		if (find != -1)
		{
			this.setOptionSet(this.optionSets.get(find), optionSetNewName, 
							optionNewCount, optionNames, optionPrices);
		}
	}
	//update (find and set, optionSetName only)
	public void updateOptionSet(String optionSetName, String optionSetNewName)
	{
		int find = this.findOptionSet(optionSetName);
		if (find != -1)
		{
			this.optionSets.get(find).setName(optionSetNewName);
		}
	}
	
	public void updateOption(OptionSet set, String optionName, String optionNewName, int optionNewPrice)
	{
		int find = this.findOption(set, optionName);
		if (find != -1)
		{
			this.setOption(set, find, optionNewName, optionNewPrice);
			//update choice
			if(find < this.choices.size() && this.choices.get(find) != null)
			{
				if(this.choices.get(find).getName().equals(optionName))
				{
					this.setOptionChoice(set.getName(), optionNewName);
				}
			}
		}
	}
	
	//override the public toString() function
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		//sort the options by name
		Collections.sort(this.optionSets);
		// Loop and append values.
		builder.append("Name: ").append(this.getName()).append("\n");
		builder.append("Make: ").append(this.getMake()).append("\n");
		builder.append("Model: ").append(this.getModel()).append("\n");
		builder.append("Base Price: ").append(this.getBasePrice()).append("\n");
		
        for (int i = 0; i < this.getOptionSets().size(); i++) {
            builder.append(this.optionSets.get(i).print());
        }
		
        builder.append("Total Price: ").append(this.getTotalPrice()).append("\n");
        
		return builder.toString();
	}
	
	public String printOptionSet(int index)
	{
		return this.optionSets.get(index).print();
	}
	
	public String printTotalPrice()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Total Price: ").append(this.getTotalPrice()).append("\n");
	    return builder.toString();
	}

}
