/*
  * Program Description: This is a class in between abstract  class ReportCardImp and 
 * PublicAPI
  */

package adapter;

public class ReportCardExtend extends ReportCardImp{

	//default constructor
	public ReportCardExtend(){}
	
	//other constructor
	public ReportCardExtend(String inputFile) {
		super(inputFile);
		
	}
	
		
}

