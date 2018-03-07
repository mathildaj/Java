/*
  * Program Description: Creatable interface
  */

package adapter;

import exception.AutoException;

public interface Creatable {
	
	public void buildAuto(String fileName, String fileType);
	public void printAuto(String autoName);
	public void printAutos();
}
