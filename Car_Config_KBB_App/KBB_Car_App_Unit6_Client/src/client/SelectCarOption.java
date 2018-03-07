/*
 * Program Description: SelectCarOption class
  */


package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import model.Automobile;
import model.OptionSet;
import model.Option;

public class SelectCarOption {

	public void configureAuto(Automobile auto)
	{
		Scanner scanner = new Scanner(System.in); 
	
		ArrayList<OptionSet> optionSets = auto.getOptionSets();
				
		for(int i = 0; i < optionSets.size(); i++)
		{
			String optionSetName = auto.getOptionSetName(i);
			System.out.print(auto.printOptionSet(i));
			
			String strChoice = "";
			
			System.out.println("\nEnter an option for : " + optionSetName);
			
			strChoice = scanner.nextLine();
			
			//set the choice
			auto.setOptionChoice(optionSets.get(i), strChoice);
		}
		
		//display the configured auto price
		System.out.print(auto.printTotalPrice());
		
		//scanner.close();
	}
}
