/*
  * Program Description: KBBClient class incorporate DefaultSocketClient
 
  */


package client;

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

import java.io.*;

import java.util.Enumeration;


public class KBBClient extends Thread implements SocketClientInterface, SocketClientConstants {

	//instance variables
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	
	private Socket sock = null;
	private Socket clientSocket = null;
	
	private String strHost;
	private int iPort;
	
	private String userChoice = "";
	
	private SelectCarOption s1 = new SelectCarOption();
	private CarModelOptionsIO c1 = new CarModelOptionsIO();
	private Object fromServer = null;
	
	
	//constructors
	public KBBClient(String strHost, int iPort)
	{
		setPort(iPort);
		setHost(strHost);
	}
	
	
	public KBBClient(Socket client)
	{
		this.sock = client;
	}
		
	//override the run method
	public void run()
	{
		if(openConnection())
		{
			handleSession();
			closeSession();
		}
		
	}//end of run
	
	public boolean openConnection()
	{
		try {
			
			clientSocket = new Socket("localhost", 10000);
			
		}
		catch (IOException socketError){
			if(DEBUG)
				System.out.println("Unable to connect to " + strHost);
			return false;
		}
		
		try {
			writer = new ObjectOutputStream(clientSocket.getOutputStream());
			reader = new ObjectInputStream(clientSocket.getInputStream());
			
		}
		catch (Exception e) {
			if(DEBUG)
				System.out.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}
	
	//this method will handle the requests from the server
	public void handleSession()
	{
		Scanner scanner = null;
		
		boolean isRunning = true;
		
		try
		{
			scanner = new Scanner(System.in); 
			
			while(isRunning)
			{
				System.out.println("Please enter your choice: ");
				System.out.println("Enter [build] to upload an auto properties file ");
				System.out.println("Enter [list] to see all available autos");
				System.out.println("Enter [config] to configure an auto");
				System.out.println("Enter [quit] to quit");
				
				//while(scanner.hasNextLine()){
					userChoice = scanner.nextLine();
				//}
				
				switch (userChoice)
				{
				case "build":
					//send to the server
					sendOutput("build");
									
					System.out.println("Please enter the properties file path and name: ");
				
					String fileName = scanner.nextLine();
					
					//build the properties
					Properties props = c1.getProperties(fileName);
					//send to the server					
					sendOutput(props);
					
					//receive response from the server
					fromServer = reader.readObject();
					System.out.println("From the server: " + (String)fromServer);
					
					break;
					
				case "list":
					//send to the server
					sendOutput("list");
					
					System.out.println("Here are the available autos to configure: ");
				
					
					//get back from the server
					ArrayList<String> autoList = (ArrayList<String>)reader.readObject();
					
					//print out the auto list
					System.out.println("AutoList: " + autoList);
				
					
					for(int i = 0; i < autoList.size(); i++)
					{
						System.out.println(autoList.get(i));
					}
										
					break; 
					
				case "config":
					//send to the server
					sendOutput("config");
					
					System.out.println("Enter an auto name to configure: ");
					String autoName = scanner.nextLine();
					
					//send the autoname to the server
					sendOutput(autoName);
				
					//now get the auto back from the server
					Automobile auto = (Automobile)reader.readObject();
					//let the user choose options
					s1.configureAuto(auto);
					
					//write a message that the auto configured 
				
					System.out.println("Auto configured successfully");
										
					break;
					
				case "quit":
					//send to the server
					sendOutput("quit");
					
					System.out.println("ByeBye!");
					
					isRunning = false;
					closeSession();
					
					break;
					
				default:
					
					System.out.println("Please enter one of the choices, or enter quit to exit:");
					break;
				}
			} //end of while
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException error) {
			if(DEBUG)
				System.out.println("Handling session with " + strHost + ":" + iPort);
		}
		
		//close the scanner
		scanner.close();
		
	}//end of handleSession
	
	
	public void sendOutput(Object output)
	{
		try {
			writer.writeObject(output);
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void closeSession()
	{
		try {
			writer.close();
			reader.close();
			clientSocket.close();
		}
		catch (IOException e) {
			if(DEBUG)
				System.out.println("Error closing socket to " + strHost);
		}
		
	}
	
	public void setHost(String strHost)
	{
		this.strHost = strHost;
	}
	
	public void setPort(int iPort)
	{
		this.iPort = iPort;
	}
	
	
	//----------these two methods are to be used for the web component
	public ArrayList<String> getAllAutos() 
	{
		
		sendOutput("list");
			
		//get back from the server
		ArrayList<String> autoList = new ArrayList<String>();
		try {
			autoList = (ArrayList<String>)reader.readObject();
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
		return autoList;
		
	}
	
	

	public Automobile getAuto(String autoName) 
	{
		
		sendOutput("config");
		sendOutput(autoName);
		
		Automobile auto = null;
		
		//now get the auto back from the server
		try {
			auto = (Automobile)reader.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return auto;
		
	}
	
	
	//----------the above two methods are to be used for the web component
	
	//main
	public static void main(String arg[])
	{
		KBBClient client = new KBBClient("localhost", 10000);
		client.start();
	}
	
} //end of the class
