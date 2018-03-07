/*
 * Program Description: Reportcardable interface information
  */

package adapter;

public interface Reportcardable 
{
	public void printStatistics();
	public void printStudentScores(String sID);
	public void writeReportCard(String reportcardFilePath);
	public void readReportCard(String reportcardFilePath);
	
}