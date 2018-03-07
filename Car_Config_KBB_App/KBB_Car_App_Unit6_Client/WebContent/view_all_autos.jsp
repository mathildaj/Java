<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Car Choice</title>
</head>
<body>

<h2>Basic Car Choice</h2>

<br/>


<form action="${pageContext.request.contextPath}/AutoServlet" method="GET">

<table border="1" style="width: 100%">
		
	<tr>
		<td>Make/Model: </td>
		<td>
			<select id="selectedAuto" name="selectedAuto">
				<c:forEach var="auto" items="${auto_list}">
				<option value="${auto}">${auto}</option>
				</c:forEach>
			</select>
		</td>
	</tr>	
	
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="Done" />
		</td>
	
	</tr>
	

</table>

</form>

</body>
</html>