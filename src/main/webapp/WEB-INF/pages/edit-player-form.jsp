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
		</tbody>
	</table><table>
			<thead>
					<tr>
						<th width="20%">Player</th><th width="25%">Result</th><th width="30%">Tournament</th>
					</tr>
			</thead>
			<tbody>
				<c:forEach var="game" items="${player.game}"><tr>
							<td>${game.secondPlayer.firstName} ${game.secondPlayer.secondName}</td>
							<td>${game.result/2} </td>
							<td>${game.tournament.tournamentName} ${game.tournament.id}</td>
				</tr></c:forEach>
			</tbody>
	</table>
<input type="submit" value="EDIT"/>
</form:form>
<p><a href="${pageContext.request.contextPath}/.html">Home page</a></p>
</body>
</html>