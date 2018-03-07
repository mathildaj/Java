/*
  * Program Description: Beverage class information
  */

package beverage;

import java.util.*;
import java.io.*;

import ui.*;

public abstract class Beverage implements Beveragable{
	
	public void dispenseBev() 
	{
		//it will be overriden by the concrete coffee, chocolate, or water class
		System.out.println("Hello Beverage");
		
	}

}
