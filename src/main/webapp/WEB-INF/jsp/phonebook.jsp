<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="employees" scope="request" type="java.util.List<com.example.jonebook.services.dto.ExtendedEmployee>"/>
<html>
<head>
    <title>Jonebook</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body style="background-color: #eee">
<div class="container">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Jonebook</a>

        <div>
<%--            <sec:authorize access="hasRole('ADMIN')">--%>
<%--                <a class="btn btn-primary" href="${pageContext.request.contextPath}/edit">Edit</a>--%>
<%--            </sec:authorize>--%>
            <sec:authorize access="not hasRole('USER')">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">Login</a>
            </sec:authorize>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </nav>

    <table class="table table-hover table-bordered bg-light">
        <tr>
            <th>Id</th>
            <th>Full name</th>
            <th>Email</th>
            <th>Phone</th>
            <sec:authorize access="hasRole('USER')">
                <th>Internal phone</th>
            </sec:authorize>
            <th>Department</th>
            <th>Posts</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td><c:out value="${employee.id}" default="None"/></td>
                <td><c:out value="${employee.name}" default="None"/></td>
                <td><c:out value="${employee.email}" default="None"/></td>
                <td><c:out value="${employee.phone}" default="None"/></td>

                <sec:authorize access="hasRole('USER')">
                    <td><c:out value="${employee.internalPhone}" default="None"/></td>
                </sec:authorize>

                <td>
                    <c:choose>
                        <c:when test="${empty employee.department}">
                            None
                        </c:when>
                        <c:otherwise>
                            <c:out value="${employee.department}"/>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${empty employee.posts}">
                            None
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="post" items="${employee.posts}">
                                <c:out value="${post} "/>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>