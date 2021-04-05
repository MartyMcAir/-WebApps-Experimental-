<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css">


      <!-- Bootstrap core CSS -->
      <link href="<c:url value="resources/css/bootstrap.css" />" rel="stylesheet">

      <!-- Custom styles for this template -->
      <link href="<c:url value="resources/css/jumbotron-narrow.css" />" rel="stylesheet">

      <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
    </head>

    <body>

      <main class="container" role="main">
        <h3>${pageContext.request.userPrincipal.name}</h3>

        <sec:authorize access="!isAuthenticated()">
          <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
          <p>Ваш логин: <sec:authentication property="principal.username" /></p>
          <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
          <h4><a href="${contextPath}/registration">Зарегистрироваться</a></h4>
        </sec:authorize>

        <h3> form with csrf token </h3>
        <!-- csrt for log out-->
        <form action="${logoutUrl}" method="post" id="logoutForm">
          <input type="hidden"
            name="${__csrf.parameterName}"
            value="${__csrf.token}"/>
          </form>

          <h4><a href="${contextPath}/news">Новости (только пользователь)</a></h4>
          <h4><a href="${contextPath}/admin">Пользователи (только админ)</a></h4>
        </main>

      </body>
    </html>
