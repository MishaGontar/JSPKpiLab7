<%@ page import="com.example.demo1.Models.Trip" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
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
        <td>Номер маршуту</td>
        <td>Номер поїзда</td>
        <td>Маршут</td>
        <td>Відправлення</td>
        <td>Прибуття</td>
        <td>В дорозі</td>
    </tr>
    <% ArrayList<Trip> trips = Trip.getTripByName(request.getParameter("search"));
        for(Trip trip: trips){
    %>
    <tr>
        <td class="all"style="text-align: center"><%=trip.getId()%></td>
        <td class="all"style="text-align: center"><%=trip.getTrain()%></td>
        <td class="all"style="text-align: center"> <%=trip.getFrom()%> ---> <%=trip.getTo()%> </td>
        <td class="all"style="text-align: center"> <%=trip.getDeparture()%></td>
        <td class="all"style="text-align: center"> <%=trip.getArrival()%></td>
        <td class="all"style="text-align: center"> <%=trip.getTime()%>ч</td>
    </tr>
    <%}
    %>
</table>
</body>
</body>
</html>
