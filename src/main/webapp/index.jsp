<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/views/shared/_header.jsp"%>
    <title>JSP - Hello World</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/shared/_handleAlerts.jsp"/>

    <h1><%= "Hello World!" %></h1>
    <br/><br/>

    <a href="events">pages events</a>

    <br/><br/>
    <a href="<c:url value="/login.php"/>">login</a>
</body>
</html>