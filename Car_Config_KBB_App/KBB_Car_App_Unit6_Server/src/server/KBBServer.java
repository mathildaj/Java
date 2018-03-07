/*
 * Author: Ying Xie
 * Class: CIS_35B Spring 2017
 * Assignment: Unit 5
 * Due Date: June 10, 2017
 * Program Description: KBBServer class incorporate DefaultSocketClient
  */


package server;

import java.net.*;
import java.io.*;

public class KBBServer 
{
	//instance variables
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private DefaultSocketClient defaultSocket = null;
	
	//constructor
	public KBBServer()
	{
		//create a server socket
		try {
           
            serverSocket = new ServerSocket(10000);
         } catch (IOException e) {
            System.err.println("Could not listen on port: 10000.");
            System.exit(1);
        }
		
	}
	
		
	//other methods
	public void startServer()
	{
		//accept
		try {
			while(true) //server can deal with multiple client requests
			{
				//reading the message from the client
	            clientSocket = serverSocket.accept();
	            System.out.println("Accept client socket...");
	            //using DefaultSocketClient class
	            defaultSocket = new DefaultSocketClient(clientSocket);
	            defaultSocket.start(); //start a thread
			}
	     } 
		catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
	     }
				
	}
	
	//
	
	//main
	public static void main(String[] args)
	{
		KBBServer s1 = new KBBServer();
		System.out.println("Starting Server...");
		System.out.println("\n\n");
		s1.startServer();
		
	}
	
		
	

} //end of KBBServer class
