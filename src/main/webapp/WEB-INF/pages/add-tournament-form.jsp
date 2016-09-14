<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add Tournament page</title>
</head>
<body>
<h1>Add tournament page</h1>
<p>Here you can add a new Tournament.</p>
<form:form method="POST" modelAttribute="tournament" action="${pageContext.request.contextPath}/tournament/add.html">
	<table>
		<tbody>
		<tr>
			<td>tournamentName:</td>
			<td><form:input path="tournamentName"/></td>
		</tr>
		<tr>
			<td>JudgeName</td>
			<td><form:input path="judgeName"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Add"/></td>
			<td></td>
		</tr>
		</tbody>
	</table>

		<select  multiple="true" size="${size}" name="Players">
	< 		<c:forEach items="${player}" var="player">
			<option value="${player.id}"> ${player.firstName} ${player.secondName}</option>
		</c:forEach>
		</select>



</form:form>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>