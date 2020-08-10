<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <title>Изменить данные пользователя</title>
</head>
<body>
<page:bootstrapHeader/>

<div class="container">
    <br/>
    <a class="nav-link" href="${contextPath}/HibernatePages/hibernateUpdateUser.jsp"
       tabindex="-1">Назад на hibernateUpdateUser.jsp</a>

    <strong>Редактировать пользователя</strong>

    <%--<form action="/users/${param.id}" method="post">--%>
    <%--<form action="${contextPath}/hibernateP/users/${param.id}" method="post">--%>
    <form action="${contextPath}/HibernateUsersCrud" method="get" class="formClass">
        <input class="form-control col-md-3 mb-3" type="hidden" name="id" value="${param.id}">
        <input class="form-control col-md-3 mb-3" type="text" name="name" value="${param.name}"
               placeholder=${param.name}>
        <input class="form-control col-md-3 mb-3" type="text" name="age" value="${param.age}" placeholder=${param.age}>
        <%--    <input type="hidden" name="_method" value="put">--%>
        <input class="form-control col-md-3 mb-3" type="hidden" name="_method" value="update">
        <button type="submit" value="Обновить" class="btn btn-primary btn-sm">Обновить</button>
    </form>

    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>