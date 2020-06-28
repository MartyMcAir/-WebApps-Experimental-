<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 04.05.2020
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Jsp</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Hello List Jsp</h1>
</div>

<h3> отступ _ ..</h3>
<p><a href="${pageContext.request.contextPath}/simpleJSP/appIndex.jsp">Назад на appIndex.jsp</a></p>

<p><a href="../"> на главную</a></p>
<%-- отправит на http://localhost:8080/FirstWebApp_war_exploded/appView/ --%>
<p><a href="./"> на FirstWebApp_war_exploded/appView/ </a></p>
<%--отправит на сам localhost--%>
<p><a href="/"> на localhost</a></p>


<%-- Используя объект request, мы можем получить список имен, который передавали из сервлета
(мы прикрепили соответствующий атрибут к этому объекту), а используя объект out — можем вывести эти имена.   --%>
<%--<% List<String> names = (List<String>) request.getAttribute("userNames");--%>
<%--    if (names != null && !names.isEmpty()) {--%>
<%--        out.println("<ul>");--%>
<%--        for (String s : names) {--%>
<%--            out.println("<li>" + s + "</li>");--%>
<%--        }--%>
<%--        out.println("</ul>");--%>
<%--    } else out.println("<p>There are no users yet!</p>"); %>--%>

<h3>Отступ Version 2</h3>
<%-- https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2 --%>
<div>
    <h2>Users</h2>
</div>
<%--<% List<String> names = (List<String>) request.getAttribute("userNames");--%>
<%--    if (names != null && !names.isEmpty()) {--%>
<%--        out.println("<ui>");--%>
<%--        for (String s : names) {--%>
<%--            out.println("<li>" + s + "</li>");--%>
<%--        }--%>
<%--        out.println("</ui>");--%>
<%--    } else out.println("<p>There are no users yet!</p>"); %>--%>

<h3>Отступ Version 3</h3>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Users</h2>
        </div>
        <% List<String> names = (List<String>) request.getAttribute("userNames");
            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                for (String s : names)
                    out.println("<li class=\"w3-hover-sand\">" + s + "</li>");
                out.println("</ul>");
            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    + "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>There are no users yet!</h5>\n" +
                    "</div>"); %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
<%--    <button class="w3-btn w3-round-large" onclick="location.href='../'">Back to main</button>--%>
    <button class="w3-btn w3-round-large" onclick="location.href='${pageContext.request.contextPath}'">Back to main</button>
</div>
</body>
</html>
