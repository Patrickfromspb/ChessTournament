<%@ page import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>
<%@ page import="com.sun.media.jfxmedia.events.PlayerEvent" %>
<%@ page import="com.gargolin.model.Player" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>List of tournaments</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script>
    $(document).ready(function() {
        $( "button" ).click(function() {
            var first = $(this).text();
            var id = $(this).attr('id');
            var initial="1233";
            var second="1233";
            var splitId=id.split("+");
            var newId=splitId[1]+"+"+splitId[0];
            newId.trim();
            if (splitId[1]!=splitId[0]) {
                if (first.indexOf('1') !== -1) {
                    initial = "";
                    second = "";
                }
                if (first.indexOf('/') !== -1) {
                    initial = "1";
                    second = "0";
                }
                if (first.indexOf('0') !== -1) {
                    initial = "1/2";
                    second = "1/2";
                }
                if ((first.indexOf('1') == -1)  && (first.indexOf('0') == -1)) {
                    initial = "0";
                    second = "1";
                }
                $(this).text(initial);
                document.getElementById(newId).innerHTML = second;
                var tournament=document.getElementById("tournament_id").innerHTML;
                console.log(tournament);
                id=splitId[0]+"a"+splitId[1]+"a"+tournament;
                $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    dataType : 'json',
                    url: "/tournament/ajax.htm",
                    data: JSON.stringify(id)
                });
            }
    })
    });
</script>
<body>
<h1>List of tournaments</h1>
<p>Here you can see tournament </p>

<p> Tournament name :${tournament.tournamentName}<p>
<p>Judge name: ${tournament.judgeName}<p>
<table border="1px" cellpadding="0" cellspacing="0" height="50px" width="50px" >
    <thead>
    <tr>
        <th style="width: 40px" style= "height:40px"> </th>
        <c:forEach var="player" items="${player}">
        <th style="width: 40px" style= "height:40px">${player.firstName} ${player.secondName} </th>

    </c:forEach>
        <th style="width: 40px" style= "height:40px">Result </th>
        <th style="width: 40px" style= "height:40px">Placing </th>
    </tr>
    </thead>
    <tbody>
    <%int i=-1;
        int j=0;
        int k=0;
        int size=(Integer) request.getAttribute("size");
        ArrayList<Integer> list=(ArrayList<Integer>) request.getAttribute("results");
        ArrayList<Player> players=(ArrayList<Player>) request.getAttribute("player");
        System.out.println("GHJJK "+list.size()+"fdsvfsd"+size);
        String string;
    %>
    <c:forEach var="player" items="${player}">
        <%i++;%>
        <tr>
            <%j=-1;%>
            <td style="width: 40px; height:40px" >${player.firstName} ${player.secondName}</td>
            <c:forEach begin="1" end="${size}" step="1">
                <%j++;%>
                <td style="width: 40px; height:40px" > <button id="<%=players.get(i).getId()%>+<%=players.get(j).getId()%>" style="width: 40px; height:40px"  type="button">
                <%
                  k=list.get(size*(i)+j);
                    switch(k){
                    case 0: string="0"; break;
                    case 1: string="1/2";break;
                    case 2: string="1";break;
                    default: string="";
                    }
                %>
                <%=string%>
                </button></td>
            </c:forEach>
            <td style="width: 40px" style= "height:40px" > </td>
            <td style="width: 40px" style= "height:40px" > </td>
        </tr>
    </c:forEach>

    </tbody>
    </table>
        <div style="display:none" id="tournament_id">${tournament.id}</div>
    </body>
</html>