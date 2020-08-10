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
    <a class="nav-link" href="${contextPath}/ajaxPages/ajaxHibernateUpdateUser.jsp"
       tabindex="-1">ajaxHibernateUpdateUser.jsp</a>
    <strong>Редактировать пользователя</strong>

    <form action="${contextPath}/HibernateAjaxCrudUser" method="get" class="formClass">
<%--    <form action="${contextPath}/HibernateUsersCrud" method="get" class="formClass">--%>
        <input type="hidden" name="_method" value="update">
        <input type="hidden" name="id" value="${param.id}">
        <input class="form-control col-md-3 mb-3" type="text" name="name" value="${param.name}"
               placeholder=${param.name}>
        <input class="form-control col-md-3 mb-3" type="text" name="age" value="${param.age}" placeholder=${param.age}>
        <button type="submit" value="Обновить" class="btn btn-primary btn-sm">Обновить</button>
    </form>

    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>