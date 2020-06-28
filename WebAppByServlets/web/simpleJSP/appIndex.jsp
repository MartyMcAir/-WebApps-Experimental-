<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 04.05.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>app Index Jsp</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<!-- header -->
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Super app!</h1>
</div>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<h3> отступ _ ..</h3>

<p><a href="${pageContext.request.contextPath}/appView/add.jsp">обращение к ./appView/add.jsp без маппинга</a></p>
<p><a href="${pageContext.request.contextPath}/appView/list.jsp">обращение к ./appView/list.jsp без маппинга</a></p>

<%-- app\servlets\AddServlet.java --%>
<h3> отступ _ ..</h3>
<p><a href="${pageContext.request.contextPath}/appIndex.jsp/JRush-AddServlet">обращение к JRush-AddServlet маппинг</a></p>
<p><a href="${pageContext.request.contextPath}/appIndex.jsp/JRush-ListServlet">обращение к JRush-ListServlet маппинг</a></p>

<h3> отступ _ My Exp Servlets ..</h3>
<p><a href="${pageContext.request.contextPath}/appIndex.jsp/JRush-MyTestExpServlet">обращение к сервлету MyTestExpServlet через маппинг</a></p>
<p><a href="${pageContext.request.contextPath}/appView/myList.jsp">обращение к ./appView/myList.jsp без маппинга</a></p>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-light-blue w3-round-large"
                onclick="location.href='${pageContext.request.contextPath}/list'">List users</button>
        <button class="w3-btn w3-hover-green w3-round-large"
                onclick="location.href='${pageContext.request.contextPath}/add'">Add user</button>
    </div>
</div>
</body>
</html>
