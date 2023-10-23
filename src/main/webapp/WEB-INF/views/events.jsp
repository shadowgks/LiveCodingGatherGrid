<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Events</title>
</head>
<body>
<div>
<%--    <c:if test="not empty ${msg}">--%>
        <h3>${msg}</h3>
<%--    </c:if>--%>
</div>
<hr>
<form action="add-event" method="post">
    <label>name</label>
    <input type="text" name="name"><br>

    <label>date</label>
    <input type="datetime-local" name="date"><br>

    <label>nb places</label>
    <input type="number" min="0" name="max" value="${event.max}">

    <label>price</label>
    <input type="number" min="0" name="price" value="${event.price}">
    <button type="submit">add</button>
</form>


<table>
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>nb places</th>
        <th>date</th>
        <th>price</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${events}" var="event">
        <tr>
            <td>${event.id}</td>
            <td>${event.name}</td>
            <td>${event.max}</td>
            <td>${event.date}</td>
            <td>${event.price}DH</td>
            <td>
                <a href="edit-event?id=${event.id}">Edit</a>
                <a href="delete-event?id=${event.id}">Delete</a>
                <a href="reservation?id=${event.id}">Reservation</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
