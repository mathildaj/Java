/*
 * Program Description: OptionSet and Option class
*/

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OptionSet implements Serializable, Comparable<OptionSet> 
{
	//instance variables
	private String name;
	private ArrayList<Option> opts;
	private int optCount;
	private Option choice;
	
	//default constructor
	protected OptionSet() {}
	
	//other constructors
	protected OptionSet(String name, int optCount, ArrayList<Option> opts)
	{
		this.name = name;
		this.optCount = optCount;
		this.opts = opts;
	}
	
	protected OptionSet(String name, int optCount, ArrayList<Option> opts, String optionName)
	{
		this.name = name;
		this.optCount = optCount;
		this.opts = opts;
		//setOptionChoice(optionName);
	}
	
	protected OptionSet(String name, int optCount, ArrayList<Option> opts, Option choice)
	{
		this.name = name;
		this.optCount = optCount;
		this.opts = opts;
		this.choice = choice;
	}
	
	protected OptionSet(String name, int optCount)
	{
		this.name = name;
		this.optCount = optCount;
		this.opts = new ArrayList<Option>();
		this.choice = null;
	}
	
	//regular getters and setters
	protected Option getOption(int index)
	{
		return this.opts.get(index);
	}
	
	protected Option getOption(String optionName)
	{
		int find = this.findOption(optionName);
		if(find != -1)
			return this.opts.get(find);
		else
			return null;
	}
	
	protected Option getOptionChoice()
	{
		return this.choice;
	}
	protected void setOptionChoice(String optionName)
	{
		int find = findOption(optionName);
		if (find != -1) //found it
			this.choice = opts.get(find);
		else
			this.choice = null;
	}
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOpts() {
		return opts;
	}

	protected void setOpts(ArrayList<Option> opts) {
		this.opts = opts;
	}
	
	protected int getOptCount() {
		return this.optCount;
	}
	protected void setOptCount(int optCount) {
		this.optCount = optCount;
	}
	
	//other setters
	//create a new Option based on name and price, then assign it to the Option arraylist
	protected void setOption(int index, String name, int price)
	{
		int max = this.getOptCount();
		if (index >=0 && index < max)
		{
			Option opt = new Option(name, price);
			if(index >= this.opts.size())
				this.opts.add(index, opt);
			else
				this.opts.set(index, opt);
		}
	}
	
	//find----------------
	
	//find the option index searching by option name
	protected int findOption(String name)
	{
		for (int i = 0; i < this.getOpts().size(); i++)
		{
			if (this.getOpts().get(i).getName().equals(name))
				return i;
		}
		return -1; //if not found by that name
	}
	
	//delete------------------------
	//delete by Option Name
	protected void deleteOption(String name)
	{
		int find = findOption(name);
		
		if ( find != -1)
		{
			//this.opts.set(find, null);
			this.opts.remove(find);
			//if, after the delete, there is no option left any more
			//then the choice needs to be set to null
			if(this.opts.size() == 0)
				this.choice = null; 
		}
	}
	
	//delete Option by Option
	protected void deleteOption(Option opt)
	{
		deleteOption(opt.getName());
	}
	
	//delete an option by option index
	protected void deleteOption(int index)
	{
		int max = this.getOptCount();
		if (index >= 0 && index < max)
			this.opts.remove(index);
		//if, after the delete, there is no option left any more
		//then the choice needs to be set to null
		if(this.opts.size() == 0)
			this.choice = null; 
	}
	
	
	//other methods
	
	//implement the Comparable interface for sorting
	//this has to be public
	public int compareTo(OptionSet set)
	{
		return new String(this.name).compareTo(new String(set.name));
	}
	
	
	public String ToString()
	{
		StringBuilder builder = new StringBuilder();
		//sort the options by name
		//Arrays.sort(this.opts);
		Collections.sort(this.opts);
		// Loop and append values.
		builder.append("\n");
		builder.append("OptionSet Name: ").append(this.getName()).append("\n");
        for (int i = 0; i < this.getOpts().size(); i++) {
            builder.append(this.opts.get(i).print());
        }
        builder.append("\n");
        if(this.getOptionChoice() != null)
        	builder.append("User choice: ").append(this.getOptionChoice().getName()).append("\n");
		return builder.toString();
	}
	
	
	protected String print()
	{
		StringBuilder builder = new StringBuilder();
		//sort the options by name
		//Arrays.sort(this.opts);
		Collections.sort(this.opts);
		// Loop and append values.
		builder.append("\n");
		builder.append("OptionSet Name: ").append(this.getName()).append("\n");
        for (int i = 0; i < this.getOpts().size(); i++) {
            builder.append(this.opts.get(i).print());
        }
        builder.append("\n");
        if(this.getOptionChoice() != null)
        	builder.append("User choice: ").append(this.getOptionChoice().getName()).append("\n");
		return builder.toString();
	}
			
		
	
}//the end of OptionSet class
