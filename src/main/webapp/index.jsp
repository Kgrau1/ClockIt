<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Timeclock</title>
        <%@ include file="head.jsp" %>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <!-- Add EL to show conformation -->
        <div class="container custom-container mt-5">
            <form action="ClockInStatusServlet" method="get" class="container p-3 m-3 bg-secondary text-white">
                <label>User ID: </label>
                <input type="text" name="userId">
                <button type="submit" value="submit">Submit</button>
                <br>
                <c:if test="${param.success eq 'true'}">
                    <p style="color: green;">Successfully clocked in</p>
                </c:if>
                <c:if test="${userNotFound}">
                    <p style="color: red;">User not found</p>
                </c:if>
            </form>
            <form action="temp.jsp" class="container p-3 m-3 bg-secondary text-white">
                <label>Admin ID: </label>
                <input type="text" name="userId">
                <button type="submit" value="submit">Submit</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>