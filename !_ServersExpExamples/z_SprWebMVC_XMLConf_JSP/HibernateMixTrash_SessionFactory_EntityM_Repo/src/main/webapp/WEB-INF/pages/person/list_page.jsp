<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>FILMS</title>
</head>
<body>

<h2>Films</h2>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
    </tr>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.age}</td>
            <td>
                <a href="/update/${item.id}">edit</a>
                <a href="/delete/${item.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/create" var="add"/>
<a href="${add}">Add new film</a>
</body>
</html>