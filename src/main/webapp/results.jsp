<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="head.jsp"%>

<html>
    <head>
        <title>Results</title>
        <%@ include file="head.jsp" %>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
<body>
<div class="container">
    <section class="">
        <a href="error.jsp">Home</a>
        <h2>Search Results: ClockIt project</h2>

        <c:if test="${empty users}">
            <h2>No Employees Found</h2>
        </c:if>
        <c:if test="${not empty users}">
            <c:forEach items="${users}" var="user">
                <table class="table table-dark table-striped m-3 w-50">
                <tbody>
                <tr>
                    <th>User id:</th>
                    <td><c:out value="${user.id}"/></td>
                </tr>
                <tr>
                    <th>Username:</th>
                    <td><c:out value="${user.username}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </c:if>
    </section>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>