<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <title>Удалить пользователя</title>
</head>
<body>
<page:bootstrapHeader/>

<div class="container">
    <br/>
    <a class="nav-link" href="${pageContext.request.contextPath}/HibernatePages/hibernateDeleteUser.jsp"
       tabindex="-1">Назад на hibernateDeleteUser.jsp</a>

    <strong>Вы действительно хотите удалить пользователя ${param.id}? </strong>

    <%--<form action="/users/${param.id}" method="post">--%>
    <%--<form action="${contextPath}/hibernateP/users/${param.id}" method="post">--%>
    <form action="${contextPath}/HibernateUsersCrud" method="get" class="formClass">
        <input class="form-control col-md-3 mb-3" type="hidden" name="id" value="${param.id}">
        <input class="form-control col-md-3 mb-3" type="hidden" name="_method" value="delete">
        <button type="submit" value="Удалить" class="btn btn-primary btn-sm">Удалить</button>
    </form>

    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>