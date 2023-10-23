<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Event</title>
</head>
<body>
<div>
    <%--    <c:if test="not empty ${msg}">--%>
    <h3>${msg}</h3>
    <%--    </c:if>--%>
</div>
<hr>
<form action="update-event" method="post">
    <input type="hidden" name="id" value="${event.id}">
    <label>name</label>
    <input type="text" name="name" value="${event.name}"><br>

    <label>date</label>
    <input type="datetime-local" name="date" value="${event.date}"><br>

    <label>nb places</label>
    <input type="number" min="0" name="max" value="${event.max}">

    <label>price</label>
    <input type="number" min="0" name="price" value="${event.price}">
    <button type="submit">update</button>
</form>

</body>
</html>
