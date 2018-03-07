/*
 * Program Description: CarModelOption class
  */


package client;

import java.util.*;

import adapter.BuildAuto;

import java.io.*;

import util.*;

public class CarModelOptionsIO {

	public Properties getProperties(String fileName)
	{
		FileIO f1 = new FileIO();
		return f1.getPropertiess(fileName);
	}
	
	
}
