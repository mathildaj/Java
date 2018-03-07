package scale;

import model.Automobile;

public interface Editable {

	//this is the synchronized method
	public void edit(String autoName, int ops, String[] opsValues);
	
	//this method is to test the data corruption of multithreads when not using synchronization
	public void nonSynchronizedEdit(String autoName, int ops, String[] opsValues);
	
}
