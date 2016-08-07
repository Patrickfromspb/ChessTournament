<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add player page</title>
</head>
<body>
<h1>Add player page</h1>
<p>Here you can add a new player.</p>
<form:form method="POST" modelAttribute="player" action="${pageContext.request.contextPath}/player/add.html">
	<table>
		<tbody>
		<tr>
			<td>FirstName:</td>
			<td><form:input path="firstName"/></td>
		</tr>
		<tr>
			<td>SecondName</td>
			<td><form:input path="secondName"/></td>
		</tr>
		<tr>
			<td>Rating:</td>
			<td><form:input path="currentRating"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Add"/></td>
			<td></td>
		</tr>
		</tbody>
	</table>

</form:form>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>