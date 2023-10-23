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
    <div class="form signup">
        <h1 class="header-test">SIGNUP</h1>
        <form action="<c:url value='/register.php'/>" method="post">
            <div class="flex">
                <label>
                    <input type="text" name="firstname" placeholder="First name" required />
                </label>
                <label>
                    <input type="text" name="lastname" placeholder="Last name" required />
                </label>
            </div>
            <label>
                <input type="text" name="email" placeholder="Email address" class="w-full" required />
            </label>
            <label>
                <input type="password" name="password" placeholder="Password" class="w-full" required />
            </label>
            <div class="checkbox">
                <input type="checkbox" id="signupCheck" />
                <label for="signupCheck">I accept all terms & conditions</label>
            </div>
            <input type="submit" value="Signup" />

            <a href="<c:url value='/login.php'/>"
               style="color: white"
            >Or Login</a>
        </form>
    </div>
</section>
<script src="<c:url value="/assets/main.js"/>"></script>
</body>
</html>
