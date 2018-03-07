/*
  * Program Description: DefaultSocketClient class
  */

package server;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import model.Automobile;

import java.io.*;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

	//instance variables
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Object obj; 
	private Socket sock;

	BuildCarModelOptions b1 = new BuildCarModelOptions();

	//constructors
	public DefaultSocketClient(Socket client)
	{
		this.sock = client;
	}
		
	//override run method
	public void run()
	{
		try {
			  
			writer = new ObjectOutputStream(sock.getOutputStream());
			writer.flush();
			reader = new ObjectInputStream(sock.getInputStream());
		
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	
		handleSession();
		//closeSession();
	}//end of run
	
		
	public void handleSession()
	{
		String strObj = null;
		
		boolean isRunning = true;
		
		try {
			
			//while((strObj = (String)reader.readObject()) != null) 
			while(isRunning)
			{
				strObj = (String)reader.readObject();
					
				switch (strObj) 
				{
				case "build":
					//read from the client
					Properties props = (Properties)reader.readObject();
					
					//build the auto
					b1.buildAutoFromProperties(props);
					
					//send a response back
					sendOutput("Auto built successfully");
					
					//write a message that the auto list sent 
					System.out.println("\nAuto built successfully");
				
					break;
					
				case "list":
					//get all the autos
					ArrayList<String> allAutos = b1.getAllAutos();
					//write to the object stream and send to the client
					sendOutput(allAutos);
					//write a message that the auto list sent 
					System.out.println("\nAuto list sent successfully");
					break;
					
				case "config":
					//read the autoName from the client
					String autoName = (String)reader.readObject();
					//retrieve the auto
					Automobile auto = b1.getAuto(autoName);
					//send the auto to the client
					sendOutput(auto);
				
					//write a message that the auto sent 
					System.out.println("\nAuto sent successfully");
					break;
				case "quit":
					System.out.println("\nByeBye!");
					isRunning = false;
					closeSession();
					break;
				default:
					System.out.println("\nNo message from the client. Waiting for client to respond...");
					break;
				} //end of switch
			
			}//while loop
		} //end of try
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException error) {
			error.printStackTrace();
		}
		
		
		
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
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}//end of closeSession

} //end of the class
