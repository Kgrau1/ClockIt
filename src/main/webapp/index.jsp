<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Index</title>
    <%@include file="head.jsp"%>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="">
    <form action="ClockingActionServlet" method="post" class="">
        <c:choose>
            <c:when test="${empty userName}">
                <a href = "logIn">Log in</a>
            </c:when>
            <c:otherwise>
                <label>Username: ${userName}</label>
                <input type="hidden" name="userName" value="${userName}">
                <br>
                <c:choose>
                    <c:when test="${isClockedIn}">
                        <button type="submit" name="action" value="Clock Out">Clock Out</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="action" value="Clock In">Clock In</button>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
