/*
 * Program Description: 
 
 This is the entry point when user wants to configure a car.
 It will get all the autos from the server,
 then forward to the view_all_autos.jsp page to show all the autos
 
 */


package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.CarModelOptionsIO;
import client.KBBClient;



/**
 * Servlet implementation class AutoListServlet
 */
@WebServlet("/AutoListServlet")
public class AutoListServlet extends HttpServlet implements Web{
	
	private static final long serialVersionUID = 1L;
	
	private KBBClient web; 
	
	
	public void getClient()
	{
		//initialize a new client
		web = new KBBClient("localhost", 10000);
	}

    /**
     * Default constructor. 
     */
    public AutoListServlet() {
       
    }
    
    public void init()
    {
    	//get the client
    	getClient();
    	//opens the connection
    	web.openConnection();
    }
    
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1. get the data
		ArrayList<String> autoList = web.getAllAutos();
		
		//2. add the data to request object
		request.setAttribute("auto_list", autoList);
		
		//3. get the request dispatcher
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("view_all_autos.jsp");
		
		//4. forward to JSP
		dispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
