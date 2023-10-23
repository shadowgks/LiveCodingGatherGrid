<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="shared/_header.jsp"%>
    <title>Home page</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/_handleAlerts.jsp"/>

    <h1>Hello Mr ${sessionScope.user.firstname}</h1>


<br><br>
Voir la <a href="events">pages events</a>
<br><br>
    <c:if test="${not empty sessionScope.user.firstname}">
        <a href="<c:url value="/logout"/>" >logout</a>
    </c:if>
    <c:if test="${empty sessionScope.user.firstname}">
        <a href="<c:url value="/login.php"/>" >login</a>
    </c:if>
</body>
</html>
