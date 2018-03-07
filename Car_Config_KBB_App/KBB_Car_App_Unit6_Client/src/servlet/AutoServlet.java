/*
 * Program Description: 
 * 
 *This Servlet class takes care of both business logic and presentation html.
 *It will get the Auto based on the user choice, and get all the optionSets and
 *Options for this Auto.
 *Then, it will post to the car_report.jsp page after user makes the option choices.
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.KBBClient;
import model.Automobile;
import model.Option;
import model.OptionSet;

/**
 * Servlet implementation class AutoServlet
 */
@WebServlet("/AutoServlet")
public class AutoServlet extends HttpServlet implements Web {
	
	private static final long serialVersionUID = 1L;
	
	private KBBClient client;
	
	//needs session object
	private HttpSession session;
	
	public void getClient()
	{
		//initialize a new client
		client = new KBBClient("localhost", 10000);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoServlet() {
        super();
       
    }
    
    public void init()
    {
    	getClient();
    	 client.openConnection();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set the getsession to true, as we will use session to pass parameters to car_report.jsp
		session = request.getSession(true);
		
		//autoName
		String autoName = request.getParameter("selectedAuto");
		request.getSession().setAttribute("autoName", autoName);
		
		//auto object
		Automobile auto = client.getAuto(autoName);
		request.getSession().setAttribute("auto", auto);
		
		//base price
		int basePrice = auto.getBasePrice();
		request.getSession().setAttribute("basePrice", basePrice);
		
		//optionSets
		ArrayList<OptionSet> optionSets = auto.getOptionSets();
		
		//set content type
		response.setContentType("text/html");
		
		//start writing html
		PrintWriter webWriter = response.getWriter();
		
		//header
		webWriter.println("<!DOCTYPE html>");
		webWriter.println("<html");
		webWriter.println("<head>");
		webWriter.println("<title>Basic Car Choice</title>");
		webWriter.println("</head>");
		
		//body
		webWriter.println("<body>");
		webWriter.println("<h3>Choose Options for the Car</h3>");
		
		//form
		webWriter.println("<form action=\"car_report.jsp\" method=\"POST\">");
		
		webWriter.println("<table border=\"1\" style=\"width:100%\">");
		webWriter.println("<tr>");
		webWriter.println("<td align=\"center\">Make/Model:</td>");
		webWriter.println("<td>" + autoName + "</td>");
		webWriter.println("</tr>");
		
		
		ArrayList<String> optionSetsNames = new ArrayList<String>();
		
		for(int i = 0; i < optionSets.size(); i++)
		{
			String optionSetName = auto.getOptionSetName(i);
			
			optionSetsNames.add(optionSetName);
			
			webWriter.println("<tr>");
			webWriter.println("<td align=\"center\">" + optionSetName + ":</td>");
			webWriter.println("<td>");
			
			webWriter.println("<select name=\""+ optionSetName + "\">");
			
			ArrayList<Option> options = auto.getOptions(i);
			
			for(int m = 0; m < options.size(); m++)
			{
				String optionName = auto.getOptionName(optionSets.get(i), m);
				
				webWriter.println("<option value=\"" + optionName +"\">" + optionName + "</option>");
			}
			
			
			webWriter.println("</select>");
			
			
			webWriter.println("</td>");
			webWriter.println("</tr>");
		}
		
		request.getSession().setAttribute("optionSetsNames", optionSetsNames);
		
		//submit button for the form
		webWriter.println("<tr>");
		webWriter.println("<td colspan=\"2\" align=\"right\">");
		webWriter.println("<input type=\"submit\" value=\"Done\">");
		webWriter.println("</td>");
		webWriter.println("</tr>");
		
		//close the table
		webWriter.println("</table>");
		//close the form
		webWriter.println("</form>");
		//close the html
		webWriter.println("</body>");
		webWriter.println("</html>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
