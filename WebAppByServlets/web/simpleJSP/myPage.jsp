<%@ page import="java.util.ArrayList , java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 01.05.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<page:head/>

<body>
<h1>My Page is works!</h1>

<div class="container">
    <page:navBarMainsPages/>

    <div id="simple">
        <hr>
        <p><a href="pageForFirstServlet.jsp">pageForFirstServlet</a></p>
        <%--<a href="${mainPage}/hello-FirstServlet">обращение к сервлету FirstServlet</a>--%>
        <p><a href="${contextPath}/hello-FirstServlet">обращение к сервлету FirstServlet</a></p>

        <h3> ?q= Parameters _ ..</h3>
        <p><a href="${contextPath}/hello-SecondServletParameter">обращение к сервлету SecondServletParameter через
            маппинг</a></p>
        <p><a href="${contextPath}/hello-secondJsp">обращение к JSP странице secondJsp.jsp через маппинг</a></p>
        <p><a href="../showCart.jsp">обращение к showCart.jsp БЕЗ маппинга</a></p>

        <h3> Redirect Examples _ ..</h3>
        <p><a href="jsPageRedirect.jsp">обращение к jsPageRedirect.jsp БЕЗ маппинга</a></p>
        <p><a href="pageRedirect.jsp">обращение к pageRedirect.jsp БЕЗ маппинга</a></p>
        <p><a href="startPageForRedirect.jsp">обращение startPageForRedirect.jsp Redirect Examples Page</a></p>
    </div>

    <div id="connect">
        <h3> Cookies _ ..</h3>
        <p><a href="${contextPath}/cookies-SetCookiesServlet">обращение к сервлету SetCookiesServlet маппинга</a></p>
        <p><a href="${contextPath}/cookies-GetCookiesServlet">обращение к сервлету GetCookiesServlet маппинга</a></p>
        <p><a href="${contextPath}/cookies-DeleteCookiesServlet">обращение к сервлету DeleteCookiesServlet маппинга</a>
        </p>

        <h3> Session _ ..</h3>
        <p><a href="${contextPath}/hello-SessionServlet">обращение к сервлету SessionServlet через маппинг</a></p>
        <p><a href="${contextPath}/hello-SessionCartServlet">обращение к сервлету SessionCartServlet через маппинг</a>
        </p>
    </div>
</div>

<div id="crud">
    <h3> CRUD DB _ Имитация CRUD с помощью списка List..</h3>
    <p><a href="appIndex.jsp">обращение appIndex.jsp App CRUD from Java Rush</a></p>
    <button class="w3-btn w3-hover-light-blue w3-round-large"
            onclick="location.href='${contextPath}/add'">Add user
    </button>
    <button class="w3-btn w3-hover-green w3-round-large"
            onclick="location.href='${contextPath}/list'">List users
    </button>

    <h3> SQL _ ..</h3>
    <p><a href="${contextPath}/hello-LibraryServletSQL">обращение к сервлету LibraryServletSQL маппинга</a></p>

    <h2> my SQL _ DB CRUD _ ..</h2>
    <p><a href="../MyDbPages/ShowSomeAll.jsp">обращение к list table ShowSomeAll.jsp</a></p>
    <p><a href="../MyDbPages/AddColumns.jsp"> add column AddColumns.jsp</a></p>
    <p><a href="../MyDbPages/DeleteColumns.jsp"> delete column DeleteColumns.jsp</a></p>
    <p><a href="../MyDbPages/AddDataToBase.jsp"> write data AddDataToBase.jsp</a></p>
    <p><a href="../MyDbPages/AllinOneCRUD.jsp"> All CRUD AllinOneCRUD.jsp</a></p>
</div>


<%-- ------------------------------------------------------------------------ --%>
<% //А тут можно писать Java-код
    String strMy = "моя строка";
    List<String> list = new ArrayList<String>();
    list.add("one");
    list.add("two");
    list.add("three");

    String s = "Вся власть роботам!";
    for (int i = 0; i < 3; i++) {
        out.println(s);
        out.println("<br>");
    } %>
<%--а это уже опять HTML--%>

<%= strMy %>

<!-- пример использования JSTL  c: - это использование префикса для вызова методов библиотеки -->
<c:set var="listStrings" value="<%list>"/>
<%-- вывод списка без taglib не будет работать _ да и так не работает..--%>
${listStrings}

<!-- Выводим наш сервлет из C:\z_n\Dropbox\BitBucket\FirstWebApp\src\main\java\servlets\SimpleServlet.java -->
<!-- не рабоатает -->
${requestScope.listAttributes}

<%--это еще HTML--%>

<%-- JS лучше размещать вконце body--%>
<page:myJs/>
</body>
</html>
