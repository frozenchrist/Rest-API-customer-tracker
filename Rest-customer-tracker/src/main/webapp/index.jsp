<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring Rest Customer Tracker</title>
</head>
<body>

	<h1>Spring Rest Customer Tracker</h1>

	<a href="${pageContext.request.contextPath}/api/customers">Get all customers</a>
	
</body>
</html>