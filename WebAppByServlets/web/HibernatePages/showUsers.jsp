<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <title>Список пользователей</title>
</head>
<body>
<page:bootstrapHeader/>

<div class="container">
    <br/>
    <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
                aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample11">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}"
                                        tabindex="-1">Назад на главную _ HOME</a></li>
                <strong>-></strong>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/HibernatePages/showUsers.jsp"
                       tabindex="-1">Назад на showUsers.jsp</a>
                </li>
            </ul>
        </div>
    </nav>

    <table border="2">
        <tr>
            <td>ID</td>
            <td>Имя</td>
            <td>Возраст</td>
            <td>Действия</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getAge()}</td>
                <td>
                        <%--                <form action="updateUser.jsp" method="post">--%>
                    <form action="${contextPath}/HibernatePages/updateUser.jsp" method="post">
                        <input type="hidden" name="id" value="${user.getId()}">
                        <input type="hidden" name="name" value="${user.getName()}">
                        <input type="hidden" name="age" value="${user.getAge()}">
                        <input type="submit" value="Изменить" style="float:left">
                    </form>
                        <%--                <form action="deleteUser.jsp" method="post">--%>
                    <form action="${contextPath}/HibernatePages/deleteUser.jsp" method="post">
                        <input type="hidden" name="id" value="${user.getId()}">
                        <input type="submit" value="Удалить" style="float:left">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%--<form action="addUser.jsp">--%>
    <form action="${contextPath}/HibernatePages/addUser.jsp">
        <input type="submit" value="Добавить нового пользователя">
    </form>

    <page:hibernateUserNavBarJavaRush/>
    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>