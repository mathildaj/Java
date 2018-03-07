/*
 * Program Description: Updatable interface
  */

package adapter;



public interface Updatable {
	
	public void updateOptionSetName(String autoName, String optionSetName, String newName);
	
	public void updateOptionPrice(String autoName, String optionSetName, String optionName, int newPrice);

}
