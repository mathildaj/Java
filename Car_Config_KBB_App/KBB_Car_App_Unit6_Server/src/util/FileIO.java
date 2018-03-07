package util;

import java.io.*;
import java.util.*;
import model.*;
import exception.*;

public class FileIO {

	//instance variables
	private String fname;
	
	//default constructor
	public FileIO(){}
	//other constructor
	public FileIO(String fname){
		this.fname = fname;
	}
	
	//getters and setters
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	//other methods
	
	public Automobile buildAutoFromProperties(Properties props)
	{
		//init an auto object
		Automobile a1 = new Automobile();
				
		String name = props.getProperty("Name");
		if(!name.equals(null))
		{
			String make = props.getProperty("Make");
			String model = props.getProperty("Model");
			int basePrice = Integer.parseInt(props.getProperty("BasePrice"));
			
			a1.setName(name);
			a1.setMake(make);
			a1.setModel(model);
			a1.setBasePrice(basePrice);
			
			//get the optionSets and options
			int optionSetCount = Integer.parseInt(props.getProperty("OptionSetCount"));
			
			
			a1.setSetCount(optionSetCount);
			
			for(int i = 0; i < optionSetCount; i++)
			{
				String optionSetName = props.getProperty("OptionSet" + (i + 1));
				
				String cName = "OptionSet" + (i + 1) + "OptionCount";
				//System.out.println(cName);
				
				int optionCount = Integer.parseInt(props.getProperty(cName));
				
				//create OptionSet
				a1.createOptionSet(i, optionSetName, optionCount);
				
				//create Options
				for(int j = 0; j < optionCount; j++)
				{
					String optionName = props.getProperty("OptionSet" + (i + 1) +"Option" + (j + 1));
					
					int optionPrice =  Integer.parseInt(props.getProperty("OptionSet" + (i + 1) +"Option" + (j + 1) + "Price"));
					a1.setOption(a1.getOptionSet(i), j, optionName, optionPrice);
				}
			}
				
		}
		
		return a1;

	}

	//build and return an Automotive object from a properties file
	public Automobile buildAutoFromProperties(String fileName)
	{
		//init an auto object
		Automobile a1 = new Automobile();
		
		FileInputStream in = null;
		
		try {
			Properties props = new Properties();
			in = new FileInputStream(fileName);
			props.load(in); //load the file in memory
			
			a1 = buildAutoFromProperties(props);
		}//end of try
		catch (Exception e)
		{
			System.out.println("Error -- " + e.toString());
			
		} //the end of catch
		
		finally //if an exception is thrown, make sure the buff closes
		{
			try {
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return a1;
		
	}
	
	
	
	//build and return an Automotive object from the text file
	public Automobile buildAutoObject (String fileName) 
	{
		int counter = 0;
		
		int setCount = 0;
		int setIndex = 0;
		int optCount = 0;
		
		String optionSetName = "";
		
		//init an auto object
		Automobile a1 = new Automobile();
		
		FileReader file = null;
		BufferedReader buff = null;
		
		try {
			file = new FileReader(fileName);
			buff = new BufferedReader(file);
					
			boolean eof = false;
			while (!eof){
				String line = buff.readLine();;
				counter++;
				if (line == null || line.trim().isEmpty())
					eof = true;
				else //if the file is not at the end
				{
					//the first line is the auto name
					if (counter == 1) 
					{
						try{
							parseName(line, a1);
						}
						catch (AutoException e)
						{
							e.fixException(e.getErrorNo());
							String str = "Name:" + e.getFixMsg();
							//get the fix from the user, then re parse
							parseName(str, a1);
						}
					}
					//the second line is the make
					else if (counter == 2)
					{
						parseMake(line, a1);
					}
					//the third line is the model
					else if (counter == 3)
					{
						parseModel(line, a1);
					}
					//the second line is the base price
					else if (counter == 4)
					{
						parseBasePrice(line, a1);
					} //end of (counter == 2)
					//the third line is the OptionSet count
					else if (counter == 5)
					{
						setCount = parseOptionSetCount(line, a1); 
						//initialize the Automotive instance
						//a1 = new Automobile(model, basePrice, setCount);
					}
					//from fifth line and onwards
					else
					{
						//find the line with the optionSet name
						if (setCount > 0 && line.indexOf(':') >= 0)
						{
							optCount = parseOptionCount(setCount, setIndex, line, a1);
							setIndex++;
						}
						else if (optCount > 0)
						{
							parseOptions(setIndex, optCount, line, a1);
							
						} //end of optCount > 0
					} //end of fourth line and onwards
				} //end of line is not null
			} //end of while
			//buff.close();
		} //the end of try
		
		catch (Exception e)
		{
			System.out.println("Error -- " + e.toString());
			
		} //the end of catch
		
		finally //if an exception is thrown, make sure the buff closes
		{
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//--------------set user choices
		//---------Temporarily commented out this operation to allow easy testing for 
		//multithreaded EditOptions
		
		//setUserChoices(a1);
		
		return a1;
	} //the end of buildAutoObject
	
	
	//set user choices
	public void setUserChoices(Automobile a1)
	{
	//hardcoded: for testing purpose ONLY,
		int optionSetSize = a1.getSetCount();
		for(int i = 0; i < optionSetSize; i++)
		{
			System.out.println("\n");
			String setName = a1.getOptionSetName(i);
			System.out.println("Here are all the options for  " + a1.getName() + " " + setName + ": ");
			OptionSet set = a1.getOptionSet(i);
			int optionSize = a1.getOptions(i).size();
			for(int j = 0; j < optionSize; j++)
			{
				System.out.println("\n");
				System.out.println(a1.getOptionName(set, j));
			
			}
			
			System.out.println("\n");
			System.out.println("Please choose an Option:  ");
			Scanner input = new Scanner(System.in);
			String choice = input.nextLine();
			a1.setOptionChoice(setName, choice);
		}
		
				
		System.out.println("\n");
		System.out.println("The total price of the auto based on your choices: ");
		System.out.println(a1.getTotalPrice());
	}
	
	//----------helper methods for parsing text to buildAutoObjects-------------------
	public void parseName(String line, Automobile a1) throws AutoException
	{
		String[] str1 = line.split(":");
		if(str1.length < 2 || str1[1] == null || str1[1].isEmpty())
		{
			//2, throw Name Missing exception
			throw new AutoException(2);
		}
		else
		{
			//a1.setName(model);
			a1.setName(str1[1]);
		}
	}
	
	public void parseMake(String line, Automobile a1) throws AutoException
	{
		String[] str1 = line.split(":");
		if(str1.length < 2 || str1[1] == null || str1[1].isEmpty())
		{
			//3, throw Make Missing exception
			throw new AutoException(3);
		}
		else
		{
			a1.setMake(str1[1]);
		}
	}
	
	public void parseModel(String line, Automobile a1) throws AutoException
	{
		String[] str1 = line.split(":");
		if(str1.length < 2 || str1[1] == null || str1[1].isEmpty())
		{
			//4, throw Model Missing exception
			throw new AutoException(4);
		}
		else
		{
			a1.setModel(str1[1]);
		}
	}
	
	public void parseBasePrice(String line, Automobile a1) throws AutoException
	{
		int basePrice = 0;
		
		String[] str2 = line.split(":");
		
		if (str2.length < 2 || str2[1] == null || str2[1].isEmpty())
		{
			//5, throw price Missing exception
			throw new AutoException(5);
		}
		try {
			basePrice = Integer.parseInt(str2[1].trim());
		}
		catch (NumberFormatException e) {
			//6, throw Price Invalid exception
			throw new AutoException(6);
		}
		if (basePrice <= 0)
		{
			//6, throw Model Price Invalid exception
			throw new AutoException(6);
		}
		else
		{
			a1.setBasePrice(basePrice);
		}
		
	}

	public int parseOptionSetCount(String line, Automobile a1) throws AutoException
	{
		int setCount = 0;
		
		String[] str3 = line.split(":");
		
		if (str3.length < 2 || str3[1] == null || str3[1].isEmpty())
		{
			//8, throw OptionSet Count Missing
			throw new AutoException(8);
		}
		try {
			setCount = Integer.parseInt(str3[1].trim());
		}
		catch (NumberFormatException e) {
			//9, throw OptionSet Count Invalid exception
			throw new AutoException(9);
		}
		if (setCount <= 0)
		{
			//9, throw OptionSet Count Invalid exception
			throw new AutoException(9);
		}
		else
		{
			a1.setSetCount(setCount);
		}
		return setCount;
	}
	
	
	public int parseOptionCount(int setCount, int setIndex, String line, Automobile a1) throws AutoException
	{
		String optionSetName = "";
		int optCount = 0;
		
		if (setCount > 0 && line.indexOf(':') >= 0)
		{
			String[] str4 = line.split(":");
			optionSetName = str4[0]; //ex:color
			if (str4[0] == null || str4[0].isEmpty())
			{
				//7, throw OptionSet Name Missing exception
				throw new AutoException(7);
			}
			if (str4.length < 2 || str4[1] == null || str4[1].isEmpty())
			{
				//12, Option Count for the OptionSet Missing exception
				throw new AutoException(12);
			}
			try {
				optCount = Integer.parseInt(str4[1].trim()); //10 colors
			}
			catch (NumberFormatException e) {
				//13, throw Option Count Invalid exception
				throw new AutoException(13);
			}
			if (optCount <= 0)
			{
				//13, throw Option Count Invalid exception
				throw new AutoException(13);
			}
			else
			{
				//ex:creating optionSet color with 10 colors
				a1.createOptionSet(setIndex, str4[0], optCount);
			}
			
		}
		return optCount;
	}
	
	//ToDo: exception not handled yet---------------------------
	public void parseOptions(int setIndex, int optCount, String line, Automobile a1)
	{
		String[] strOptions = line.split(",");
		for(int i = 0; i < optCount; i++) //ex:for each color option
		{
			//ex:create all the color options
			String[] str5 = strOptions[i].split(";");
			String optName = str5[0];
			int optPrice = Integer.parseInt(str5[1].trim());
			a1.setOption(a1.getOptionSet(setIndex-1), i, optName, optPrice);
		}
	}
	
	//-------------end of helper methods for parsing text to buildAutoObjects------------------
	
	
	//write to a serialized file to the same directory of this file
	public void writeAutoObject(Automobile a1, String fileName)
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream(fileName));
			out.writeObject(a1);
			out.close();
		} //the end of try
		catch(Exception e)
		{
			System.out.print("Error: " + e);
			System.exit(1);
		}
	}
	
		
	//deserialize the Automotive object from the file, and return the object
	public Automobile readAutoObject(String fileName)
	{
		Automobile a1 = new Automobile();
		try
		{
			ObjectInputStream in = new ObjectInputStream 
								(new FileInputStream(fileName));
			a1 = (Automobile)in.readObject();
			in.close();
		} //the end of try
		catch(Exception e)
		{
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return a1;
	}
} //the end of FileIO class
