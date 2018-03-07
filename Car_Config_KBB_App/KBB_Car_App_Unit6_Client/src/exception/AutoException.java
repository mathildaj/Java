/*
 * Program Description: the custom exception class
  */


package exception;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import adapter.*;
import model.*;


public class AutoException extends Exception {

	//instance variables
	private int errorNo;
	private String errorMsg;
	private int errorCount = 16; //the current count of the error in the file
	private ArrayList<Integer> errorNumArray;
	private ArrayList<String> errorMsgArray;
	private String fixMsg;
			
	//static file names for reading and logging exceptions
	private String exceptionFile = "C:\\DeAnzaCollege\\Spring_2017\\ying\\CIS_35B_Adv_Java\\"
			+ "workspace\\Unit_3_With_Generics\\src\\exceptionFile.txt";
	
	private String loggingFilePath = "C:\\DeAnzaCollege\\Spring_2017\\ying\\CIS_35B_Adv_Java\\"
			+ "workspace\\Unit_3_With_Generics\\src\\exceptionLogging";
	
	//default constructor
	public AutoException()
	{
		super();
		//load the error enumeration file
		errorNumArray = new ArrayList<Integer>();
		errorMsgArray = new ArrayList<String>();
		loadErrorFile();
		
	}
	//other constructor
	public AutoException(int errorNo)
	{
		super();
		//load the error enumeration file
		errorNumArray = new ArrayList<Integer>();
		errorMsgArray = new ArrayList<String>();
		loadErrorFile();
		
		this.errorNo = errorNo;
		this.errorMsg = errorMsgArray.get(errorNo-1);
		
		//log error
		logError(this.errorNo, this.errorMsg);
	}
	
	//getters and setters
	public int getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getFixMsg() {
		return fixMsg;
	}
	public void setFixMsg(String fixMsg) {
		this.fixMsg = fixMsg;
	}
	
	
	//other methods---------------------------------------
	
	
	//handle exceptions, will be overridden by exception helper classes
	public void fixException(int errorNo)
	{
		//setting the error	
		this.setErrorNo(errorNo);
		this.setErrorMsg(errorMsgArray.get(errorNo-1));
		//logging the error	
		//logError(errorNo, errorMsgArray.get(errorNo-1));
		//fix the error
		FixFileIO f1 = new FixFileIO();
		//set the fix, so it can be returned to the caller??
		this.setFixMsg(f1.fix(errorNo)); 
		
	}
	//load error enumeration file
	public void loadErrorFile()
	{
		try
		{
			FileReader file = new FileReader(exceptionFile);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int counter = 0;
			
			System.out.println("Start loading the error enumeration text file...");
			
			while (!eof) {
				String line = buff.readLine(); //read each line
				//counter++;
				if (line == null  || line.trim().isEmpty() || counter >= errorCount) 
					eof = true;
				else {
					
					String[] str1 = line.split(",");
					//add to the error arrays
					errorNumArray.add(counter, Integer.parseInt(str1[0].trim()));
					errorMsgArray.add(counter, str1[1].trim());
				}
				counter++;
			}
			System.out.println("End loading the error enumeration text file...");
			buff.close(); //close the buffer
		} //end of try
		catch (Exception e)
		{
			System.out.println("Error -- " + e.toString());
		}
		
	} //end of loadErrorFile method

	
	//method to get the current date for time stamp
	public String getTimeStamp()
	{
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyyMMdd");
		//SimpleDateFormat timestamp = new SimpleDateFormat("yyyyMMdd.hhmmss");
	    return timestamp.format(today);
		
	}
	
	//logging error to the logging file in append mode
	public void logError(int errorNo, String errorMsg)
	{
		//build the log file name with time stamp
		StringBuilder fileName =  new StringBuilder();
		fileName.append(loggingFilePath).append("_").append(getTimeStamp())
				.append(".txt");
		
		
		//build the logging message
		StringBuilder sb = new StringBuilder();
		sb.append(errorNo).append(", ");
		sb.append(errorMsg).append("\n");
		
		try 
		{
		//write to the logging file
			File f1 = new File(fileName.toString());
			if(!f1.exists())
			{
				f1.createNewFile();
			}
			FileWriter fw = new FileWriter(f1, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(sb.toString());
			bw.close();
		}
		catch (Exception e)
		{
			System.out.println("Error writing to the logging file-- " + e.toString());
		} //the end of catch
				
	}
	
	
	
} //end of AutoException class
