<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: FOX
  Date: 16.02.2020
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.Users" %>
<%@ page import="services.UsersService" %>



<html>
<head>
    <title>Users database</title>
</head>
<body>

<h2>Users List</h2>
<p><a href='<c:url value="create" />'>Create new</a></p>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email<th>
    </tr>

    <tbody>
    <c:forEach var="users" items="${requestScope.users}">
        <tr>
            <td>${users.getId()}</td>
            <td>${users.getName()}</td>
            <td>${users.getEmail()}</td>
            <td>
                <a href='<c:url value="edit?id=${users.getId()}" />'>Edit</a> |
                <form method="post" action='<c:url value="delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${users.getId()}">
                    <input type="submit" value="Delete">
                </form>
            </td>

        </tr>

    </c:forEach>
    </tbody>
</table>
</body>
</html>
