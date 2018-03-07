/*
 * Program Description: Driver class
  */

package driver;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import model.*;
import util.*;
import adapter.*;
import scale.*;
import exception.AutoException;

public class Driver extends Thread {

	public static void main(String[] args) {
		
		//----------text file names-----------
		String f3 = "..\\..\\FocusWagonZTW.txt";
		
		//String f4 = "..\\..\\FocusWagonZTW_missingModelName.txt";
				
		//---use interfaces and abstract class to build an Auto list-----
		Creatable myList = new BuildAuto();
		
		myList.buildAuto(f3, "text");
						
		//update an auto
		String autoName = "FocusWagonZTW";
		//String autoName = "Hello Kitty Cutie Pie";
		
		//-----------DO NOT delete the following, this is for the single-threaded updating
		/*//---use interfaces and abstract class to update the auto object
		Updatable myNewAuto = new BuildAuto();
		myNewAuto.updateOptionSetName(autoName, "Color", "Color2");
		System.out.println("\n");
		System.out.println("This is the updated " + autoName + ": ");
		System.out.println("\n");
		myList.printAuto(autoName);
		
		//update option price
		myNewAuto.updateOptionPrice(autoName, "Transmission", "automatic", 100);
		System.out.println("\n");
		System.out.println("This is the updated " + autoName + ": ");
		System.out.println("\n");
		myList.printAuto(autoName);*/
		
		
		
		
		//-------------This is to Test the Multi threaded EditOptions
		//BuildAuto editable = new BuildAuto();		
		//String[] opsValues_1 = {"Color", "ColorHello"};
		//String[] opsValues_2 = {"Color", "TransmissionOla"};
		
		//String[] opsValues_1 = {"Style", "Style With Hello"};
		//String[] opsValues_2 = {"Style", "Style with Ola"};
		
		
		/*
		 The following are two threads trying to change the same Option "With Bowtie".
		 With synchronization.
		
		Also realize that I have no control over which thread runs first even though the code
		chooses to start one thread over another. The OS scheduler
		controls it.
		*/
		
		String[] opsValues_1 = {"Style", "With Bowtie", "With Bowtie", "3000"};
		String[] opsValues_2 = {"Style", "With Bowtie", "With Bowtie", "5000"};
		
		EditOptions e1 = new EditOptions(autoName, 2, opsValues_1);
		EditOptions e2 = new EditOptions(autoName, 2, opsValues_2);
		
		//create two threads
		
		Thread t1 = new Thread(e1);
		t1.setName("Thread Hello");
		
		t1.start();
			
		
		Thread t2 = new Thread(e2);
		t2.setName("Thread Ola");
				
		t2.start();
		
		
	
		
		//-------------End of testing Multi threaded EditOptions
		
		
		
	}

}
