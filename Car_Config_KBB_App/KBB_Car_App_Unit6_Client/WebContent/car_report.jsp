<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Automobile" %>
<%@ page import="model.OptionSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Configuration Proof of Concept</title>
</head>
<body>

<h2>Here is what you have selected</h2>

<br/>

<% Automobile auto = (Automobile)request.getSession().getAttribute("auto"); 
   
	ArrayList<String> optionSetsNames =(ArrayList<String>)request.getSession().getAttribute("optionSetsNames");
   
	String autoName = (String)request.getSession().getAttribute("autoName");
   
	String basePrice = String.valueOf(request.getSession().getAttribute("basePrice"));
%>
 

<table border="1" style="width:100%">
		
	<tr>
		<td><%=autoName %></td>
		<td>base price</td>
		<td><%=basePrice %></td>
	</tr>
	
	<% for(String setName : optionSetsNames) 
		{
			String selected = request.getParameter(setName);
			auto.setOptionChoice(setName, selected);
	%>
	<tr>
		<td><%=setName %></td>
		<td><%=selected %></td>
		<td><%=auto.getOptionChoicePrice(setName) %>
	</tr>
	<%} %>
	
	<tr>
		<td style="font-weight: bold">Total Cost</td>
		<td></td>
		<td style="font-weight: bold">$<%=auto.getTotalPrice() %></td>
	</tr>	
	
</table>

</form>

</body>
</html>