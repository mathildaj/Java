/*
 * Program Description: the helper class for the custom exception handling
 * this class handles FileIO exceptions
  */

package exception;

import java.util.Scanner;

public class FixFileIO {
	
	//default constructor
	public FixFileIO(){}
	
	//other methods
	//helper methods--------------------------------------
	
	/*
	 	1, File Not Found
		2, Name Missing
		3, Make Missing
		4, Model Missing
		5, Base Price Missing
		6, Base Price Invalid
		7, OptionSet Name Missing
		8, OptionSet Count Missing
		9, OptionSet Count Invalid
		10, OptionSet Missing
		11, Option Name Missing
		12, Option Count Missing
		13, Option Count Invalid
		14, Option Price Missing
		15, Option Price Invalid
		16, Option Missing
	 */
	
	
	//ToDo: will be completed later
	public String fix(int errorNo)
	//public void fix(int errorNo)
	{
		String str = "";
		switch (errorNo)
		{
			case 1:
				str = fixFileNotFound();
				//fixFileNotFound();
				break;
			case 2:
				str = fixNameMissing();
				//fixModelNameMissing();
				break;
			//will add more exception handling later
			default:
				break;
		}
		
		return str;
	}
	
	//error 1: File not found
	public String fixFileNotFound()
	//public void fixFileNotFound()
	{
		System.out.println("File not found");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a new file name for the automobile: ");
		String fileName = input.next();
		
		input.close();
		return fileName;
		
	}
	
	//2, Model Name Missing
	public String fixNameMissing()
	//public void fixModelNameMissing()
	{
		System.out.println("Auto Name Missing");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a name for the automobile: ");
		String str = input.next();
		return str;
	}
	


}
