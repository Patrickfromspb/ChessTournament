<%@ page import="com.gargolin.model.Player" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>List of tournaments</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<script>
    function placement() {
        var index = 1;
        var i = 0;
        var j = 0;
        var bigger = 0;
        var equal = 0;
        var results = new Array(1000);
        var ids = [];
        var string = "";
        var thisElement = 0.00;
        i = 0;
        <c:forEach var="player" items="${player}">
        ids.push(${player.id});
        </c:forEach>
        ids.map(String);
        for (var i = 0; i < ids.length; i++) {
            console.log(ids);
            results[i] = document.getElementById(ids[i].toString() + "result").innerHTML;
            console.log(results[i]);
        }
        for (var i = 0; i <  ${size}; i++) {
            thisElement = parseFloat(document.getElementById(ids[i] + "result").innerHTML);
            for (var j = 0; j <  ${size}; j++) {
                if (thisElement < parseFloat(results[j])) bigger++;
                if (thisElement == parseFloat(results[j])) equal++;
            }
            if (equal == 1)  string = (bigger + 1).toString();
            else  string = (bigger + 1).toString() + "-" + (bigger + equal).toString();
            document.getElementById(ids[i].toString() + "place").innerHTML = string;
            bigger = 0;
            equal = 0;
        }
    }
    $(document).ready(function () {
        $("button").click(function () {
            var first = $(this).text();
            var id = $(this).attr('id');
            var initial = "";
            var second = "";
            var splitId = id.split("+");
            var newId = splitId[1] + "+" + splitId[0];
            var change = 0;
            var resultchange = "";
            var number = 0.00;
            newId.trim();
            if (splitId[1] != splitId[0]) {
                if ((first.indexOf('1') !== -1) && (first.indexOf('2') == -1)) {
                    resultchange = document.getElementById(splitId[0] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number - 1;
                    document.getElementById(splitId[0] + "result").innerHTML = number.toString();
                    initial = "";
                    second = "";
                }
                if (first.indexOf('/') !== -1) {
                    resultchange = document.getElementById(splitId[0] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number + 0.5;
                    document.getElementById(splitId[0] + "result").innerHTML = number.toString();
                    resultchange = document.getElementById(splitId[1] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number - 0.5;
                    document.getElementById(splitId[1] + "result").innerHTML = number.toString();
                    initial = "1";
                    second = "0";
                }
                if (first.indexOf('0') !== -1) {
                    resultchange = document.getElementById(splitId[0] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number + 0.5;
                    document.getElementById(splitId[0] + "result").innerHTML = number.toString();
                    resultchange = document.getElementById(splitId[1] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number - 0.5;
                    document.getElementById(splitId[1] + "result").innerHTML = number.toString();
                    initial = "1/2";
                    second = "1/2";
                }
                if ((first.indexOf('1') == -1) && (first.indexOf('0') == -1)) {
                    resultchange = document.getElementById(splitId[1] + "result").innerHTML;
                    number = parseFloat(resultchange);
                    number = number + 1;
                    document.getElementById(splitId[1] + "result").innerHTML = number.toString();
                    initial = "0";
                    second = "1";
                }
                $(this).text(initial);
                document.getElementById(newId).innerHTML = second;
                var tournament = document.getElementById("tournament_id").innerHTML;
                id = splitId[0] + "a" + splitId[1] + "a" + tournament;
                $.ajax({
                    type: "POST",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    url: "/tournament/ajax.htm",
                    data: JSON.stringify(id)
                });
                placement();
            }
        });
        placement();
    });
</script>
<body>
<h1>List of tournaments</h1>
<p>Here you can see tournament </p>

<p> Tournament name :${tournament.tournamentName}<p>
<p>Judge name: ${tournament.judgeName}<p>
<table border="1px" cellpadding="0" cellspacing="0" height="50px" width="50px">
    <thead>
    <tr>
        <th style="width: 40px" style="height:40px"></th>
        <c:forEach var="player" items="${player}">
            <th style="width: 40px" style="height:40px">${player.firstName} ${player.secondName} </th>

        </c:forEach>
        <th style="width: 40px" style="height:40px">Result</th>
        <th style="width: 40px" style="height:40px">Placing</th>
    </tr>
    </thead>
    <tbody>
    <%
        int i = -1;
        double t;
        int j;
        int k;
        int size = (Integer) request.getAttribute("size");
        ArrayList<Integer> list = (ArrayList<Integer>) request.getAttribute("results");
        ArrayList<Player> players = (ArrayList<Player>) request.getAttribute("player");
        String string;
    %>
    <c:forEach var="player" items="${player}">
        <%i++;%>
        <tr>
            <%
                j = -1;
                t = 0;
            %>
            <td style="width: 40px; height:40px">${player.firstName} ${player.secondName}</td>
            <c:forEach begin="1" end="${size}" step="1">
                <%j++;%>
                <td style="width: 40px; height:40px">
                    <button id="<%=players.get(i).getId()%>+<%=players.get(j).getId()%>"
                            style="width: 40px; height:40px" type="button">
                        <%
                            k = list.get(size * (i) + j);
                            switch (k) {
                                case 0:
                                    string = "0";
                                    break;
                                case 1:
                                    string = "1/2";
                                    t = t + 0.5;
                                    break;
                                case 2:
                                    string = "1";
                                    t = t + 1;
                                    break;
                                default:
                                    string = "";
                            }
                        %>
                        <%=string%>
                    </button>
                </td>
            </c:forEach>
            <td style="width: 40px" style="height:40px" id="<%=players.get(i).getId()%>result"><%=t%>
            </td>
            <td style="width: 40px" style="height:40px" id="<%=players.get(i).getId()%>place"></td>

        </tr>
    </c:forEach>

    </tbody>
</table>
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
<div style="display:none" id="tournament_id">${tournament.id}</div>
</body>
</html>