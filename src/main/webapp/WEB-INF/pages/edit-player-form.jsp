<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit player page</title>
</head>
<body>
<h1>Edit player page</h1>
<p>Here you can edit the existing player.</p>
<p>${message}</p>
<form:form method="POST" modelAttribute="player" action="${pageContext.request.contextPath}/player/edit/${player.id}.html">
	<table>
		<tbody>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstName"/></td>
		</tr>
		<tr>
			<td>Second Name</td>
			<td><form:input path="secondName"/></td>
		</tr>
		<tr>
			<td>Initial Rating:</td>
			<td><form:input path="startRating"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Add"/></td>
			<td></td>
		</tr>
		</tbody>
	</table>
	<table border="1px" cellpadding="0" cellspacing="0" >
		<thead>
	<tr>
		<th width="20%">Player</th><th width="25%">Result</th><th width="30%">Tournament</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="party" items="${player.party}">
	<tr>
		<td>${party.secondPlayer.firstName} ${party.secondPlayer.secondName}</td>
		<td>${party.result} </td>
		<br>${party.tournament.tournamentName} ${party.tournament.id}</td><br/>
	</tr>
	</c:forEach>
</form:form>
</tbody>
</table>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>