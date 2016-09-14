<%@ page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>List of tournaments</title>
</head>
<body>
<h1>List of tournaments</h1>
<p>Here you can see the tournament</p>
<p> Tournament name :${tournament.tournamentName}<p>
<p>Judge name: ${tournament.judgeName}<p>
<table border="1px" cellpadding="0" cellspacing="0" height="50px" width="50px" >
    <thead>
    <tr>
        <th style="width: 40px" style= "height:40px"> </th>
        <c:forEach var="player" items="${player}">
        <th style="width: 40px" style= "height:40px">${player.firstName} ${player.secondName} </th>
    </c:forEach>
    </tr>
    </thead>
    <tbody>
    <% int i=1; ArrayList<Integer> results=com.gargolin.controller.TournamentController.results;%>
    <c:forEach var="player" items="${player}">
        <tr>
            <td style="width: 40px" style= "height:40px" >${player.firstName} ${player.secondName}</td>
            <c:forEach begin="1" end="${size}" step="1">
                <td style="width: 40px" style= "height:40px"id="<%=i%> <%i++;%>"> <button style="width: 40px" style= "height:40px"type="button"><%=results.get(i-1)%></button></td>
            </c:forEach>


        </tr>
    </c:forEach>
    </tbody>
</table>