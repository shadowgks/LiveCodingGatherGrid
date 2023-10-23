<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="shared/_header.jsp"%>

    <title>Login & Signup Form</title>
</head>
<body class="login-container">

<jsp:include page="shared/_handleAlerts.jsp"/>

<section class="wrapper active">

    <div class="form">
        <h1 class="header-test">LOGIN</h1>
        <form action="<c:url value='/login.php'/>" method="post">
            <label>
                <input type="text" name="email" placeholder="Email address" class="w-full" required />
            </label>
            <label>
                <input type="password" name="password" placeholder="Password" class="w-full" required />
            </label>
            <a href="#" style="color: white">Forgot password?</a>
            <input type="submit" value="Login" />

            <a href="<c:url value='/register.php'/>"
               style="color: white"
            >register if you don't have account</a>
        </form>
    </div>
</section>

<script src="<c:url value="/assets/main.js"/>"></script>
</body>
</html>
