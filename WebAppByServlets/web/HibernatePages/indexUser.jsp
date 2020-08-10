<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <title>Здравствуйте!</title>
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
                <li><a class="nav-link" href="${pageContext.request.contextPath}/HibernatePages/indexUser.jsp"
                       tabindex="-1">Назад на indexUser.jsp</a>
                </li>
            </ul>
        </div>
    </nav>

    Если вы хотите начать работу с базой данных пользователей - <br>
    нажмите кнопку ниже:

    <%--<form action = "users" method="get">--%>
    <form action="${contextPath}/hibernateP/users" method="get">
        <input type="submit" value="Начать работу с базой данных">
    </form>

    <page:hibernateUserNavBarJavaRush/>
    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
</body>
</html>