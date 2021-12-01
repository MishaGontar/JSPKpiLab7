<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo1.Models.Trip" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <style>
        table {
            border-collapse: separate;
            width: 100%;
            border-spacing: 7px 11px;
        }
        td {
            font-size: 20px;
            padding: 5px;
            border: 1px solid #a52a2a;
        }
        .all{
            font-size: 19px;
            padding: 5px;
            border: 1px solid #30c4c0;
        }
    </style>
</head>
<body>
<h1 style="text-align: center ; font-size: 45px">Розклад руху поїздів </h1>
<form action="searched.jsp" method="GET">
 Пошук по населеному пункту: <input type="text" name="search" autocomplete="off"> <button>Шукати</button>
</form>
<p style="text-align: left ; font-size: 25px">Добавити маршут</p>
<form style="font-size: 18px ; margin: 1px" action="TripSearchController" method="post">
    Звідки : <input type="text" name="from" autocomplete="off" required> Куди : <input type="text" name="to" autocomplete="off" required>Поїзд : <input type="text" name="train" autocomplete="off" required>
    <button>Добавити</button>
</form>
<table>
    <tr>
        <td>Номер маршут</td>
        <td>Номер поїзда</td>
        <td>Маршут</td>
        <td>Відправлення</td>
        <td>Прибуття</td>
        <td>В дорозі</td>
    </tr>
    <% ArrayList<Trip> trips = Trip.getAllTrip();
        for(Trip trip: trips){
    %>
    <tr>
        <td class="all"style="text-align: center"><%=trip.getId()%></td>
        <td class="all"style="text-align: center"><%=trip.getTrain()%></td>
        <td class="all"style="text-align: center"> <%=trip.getFrom()%> ---> <%=trip.getTo()%> </td>
        <td class="all"style="text-align: center"> <%=trip.getDeparture()%></td>
        <td class="all"style="text-align: center"> <%=trip.getArrival()%></td>
        <td class="all"style="text-align: center"> <%=trip.getTime()%>г</td>
    </tr>
    <%}
    %>
</table>
</body>
</html>