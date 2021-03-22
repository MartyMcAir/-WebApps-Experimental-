<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <link rel="stylesheet" href="${contextPath}/resources/css/hibernate.css">
    <title>Список пользователей</title>
</head>
<body>
<page:bootstrapHeader/>

<div class="container">
    <page:navBarMainsPages/>

    <br/>
    <a class="nav-link" href="${pageContext.request.contextPath}/HibernatePages/hibernateListUsers.jsp"
       tabindex="-1">Назад на hibernateListUsers.jsp</a>

    <table border="2" class="table" border="1">
        <thead>
        <tr>
            <td scope="col">ID</td>
            <td scope="col">Имя</td>
            <td scope="col">Возраст</td>
            <td scope="col">Действия</td>
        </tr>
        </thead>

        <tbody>
<%--        <jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
        <c:forEach items="${users}" var="user">
            <tr>
                <th scope="row">${user.getId()}</th>
                <td>${user.getName()}</td>
                <td>${user.getAge()}</td>
                <td>
                        <%--                <form action="updateUser.jsp" method="post">--%>
                    <div class="inlineDivs">
                        <form action="${contextPath}/HibernatePages/hibernateUpdateUser.jsp" method="get" class="formClass">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <input type="hidden" name="name" value="${user.getName()}">
                            <input type="hidden" name="age" value="${user.getAge()}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Изменить">Изменить</button>
                        </form>
                            <%--                <form action="deleteUser.jsp" method="post">--%>
                        <form action="${contextPath}/HibernatePages/hibernateDeleteUser.jsp" method="get"
                              class="formClass">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <button type="submit" class="btn btn-primary btn-sm" value="Удалить">Удалить</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <form action="${contextPath}/HibernateListUsers" method="get" class="formClass">--%>
    <form action="${contextPath}/HibernateUsersCrud" method="get" class="formClass">
        <input class="form-control col-md-3 mb-3" type="hidden" name="_method" value="add">
        <input class="form-control col-md-3 mb-3" required type="text" name="name" placeholder="Имя">
        <input class="form-control col-md-3 mb-3" required type="text" name="age" placeholder="Возраст">
        <button type="submit" value="Добавить" class="btn btn-primary btn-sm">Добавить</button>
    </form>

    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>