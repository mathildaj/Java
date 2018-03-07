package scale;

import adapter.*;

public class EditOptions extends ProxyAutomobile implements Runnable{
	//BuildAuto object is to be used to access the auto inside ProxyAuto's LinkedHashMap
	private BuildAuto b1 = new BuildAuto();
	private String autoName;
	private int ops;
	private String[] opsValues;
	
	
	//default constructor
	public EditOptions(){}
	
	//other constructors
	public EditOptions(String autoName, int ops, String[] values)
	{
		this.autoName = autoName;
		this.ops = ops;
		//initialize the array
		if(values.length > 0)
		{
			this.opsValues = new String[values.length];
			for(int i = 0; i < values.length; i++)
			{
				this.opsValues[i] = values[i];
			}
		}
	}
	
	private void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}
		
	//override the run method of Runnable interface
	@Override
	public void run()
	{		
		//this one is synchronized
		//edit(this.b1, this.ops, this.opsValues);
		//this one is NOT synchronized, may produce data corruption
		nonSynchronizedEdit(this.b1, this.ops, this.opsValues);
	}
	
		
	
	
	/*Reasons to not synchronize on the Inventory class in model package:
	 1.Locking the list of autos does NOT lock each object inside it
	 2.No backwards compatibility. This multi thread operation is a new functionality,
	 and should not break the existing functionalities.
	 */
	
	/*Reasons not to lock the Inventory variable in ProxyAuto:
	 Could cause bad performance. Why would you want to lock the entire inventory list
	 for all possible operations??
	 
	 */
	 
	/*only synchronize operations where the race condition and data corruption may happen
	as synchronization carries an overhead
	we only lock this method for the short duration the editing needs
	to improve the performance */
	public synchronized void edit(BuildAuto b1, int ops, String[] opsValues)
	{
		switch(ops){
		//edit optionset name
		case 1: 
			for(int i = 0; i < 10; i++) {
				randomWait();
								System.out.println(Thread.currentThread().getName()
						+ " is trying to edit");
				
				b1.edit(autoName, ops, opsValues);	
				
				System.out.println(Thread.currentThread().getName() + " completes the Editing");  
			
				System.out.println("\n");
				System.out.println("This is after editing " + autoName + ": ");
				System.out.println("\n"); 
				
				b1.printAuto(autoName);	
				System.out.println("\n");
			}
				break;
		//edit option price
		case 2:
			for(int i = 0; i < 10; i++) {
				randomWait();
				System.out.println(Thread.currentThread().getName()
					+ " is trying to edit");
			
				b1.edit(autoName, ops, opsValues);
				System.out.println(Thread.currentThread().getName() + " completes the Editing");  
			
				System.out.println("\n");
				System.out.println("This is after editing " + autoName + ": ");
				System.out.println("\n"); 
				
				b1.printAuto(autoName);	
				System.out.println("\n"); 
			}
				break;
		default:
			break;
			
		}
		
		
	}//end of synchronized edit method
	
	//this method is ONLY to test the possible data corruption of multithreads when not using synchronization
	//Racing condition and data corruption is not consistent. Every time it may produce different results
	//It is a GOOD reminder that racing condition is a very SUBTLE bug!
	public void nonSynchronizedEdit(BuildAuto a1, int ops, String[] opsValues)
	{
		switch(ops){
		//edit optionset name
		case 1: 
			for(int j = 0; j < 10; j++) {
				System.out.println(Thread.currentThread().getName()
					+ " is trying to edit");
			
				b1.nonSynchronizedEdit(autoName, ops, opsValues);	
				System.out.println(Thread.currentThread().getName() + " completes the Editing");  
			
				System.out.println("\n");
				System.out.println("This is after editing " + autoName + ": ");
				System.out.println("\n");
				
				b1.printAuto(autoName);	
				System.out.println("\n"); 
			}
				break;
		//edit option price
		case 2:
			for(int i = 0; i < 10; i++) {
				randomWait();
				System.out.println(Thread.currentThread().getName()
					+ " is trying to edit");
				
				b1.nonSynchronizedEdit(autoName, ops, opsValues);
								
				System.out.println(Thread.currentThread().getName() + " completes the Editing");  
			
				System.out.println("\n");
				System.out.println("This is after editing " + autoName + ": ");
				System.out.println("\n");
				
				b1.printAuto(autoName);	
				System.out.println("\n"); 
			
			}
			
				break;
		default:
			break;
			
		}
	} //end of nonsynchronizedEdit method

}//end of EditOptions class
