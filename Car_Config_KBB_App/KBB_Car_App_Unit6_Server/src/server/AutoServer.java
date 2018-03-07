/*
 * Program Description: AutoServer interface
  */

package server;

import java.io.*;
import java.util.*;

import model.Automobile;

public interface AutoServer {
	
	public void buildAutoFromProperties(Properties props);
	public ArrayList<String> listAllAutos();
	public Automobile getAuto(String autoName);

}
