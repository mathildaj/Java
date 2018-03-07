/*
 * Author: Ying Xie
 * Class: CIS_35B Spring 2017
 * Assignment: Unit 5
 * Due Date: June 10, 2017
 * Program Description: BuildCarModelOptions class
  */

package server;

import java.io.*;
import java.util.*;
import java.net.*;

import adapter.*;
import model.*;
import util.FileIO;

public class BuildCarModelOptions {
	
	private AutoServer a1 = new BuildAuto();

	public void buildAutoFromProperties(Properties props)
	{
		a1.buildAutoFromProperties(props);
	}
	
	
	public ArrayList<String> getAllAutos()
	{
		return a1.listAllAutos();
	}
	
	public Automobile getAuto(String autoName)
	{
		return a1.getAuto(autoName);
	}
}
