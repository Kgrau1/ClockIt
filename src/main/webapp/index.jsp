<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Index</title>
</head>
<body>
<form action="ClockingActionServlet" method="post">
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
</body>
</html>
