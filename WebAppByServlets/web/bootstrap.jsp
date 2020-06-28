<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 12.06.2020
  Time: 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
</head>

<body>
<page:bootstrapHeader/>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Simple Examples & code trash (Servlets)</h1>
    <p class="lead">Just for example to use.</p>
</div>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
                aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="simpleJSP/myPage.jsp">First Main Servlet</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Home Main Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./ajaxMainPage.jsp">Ajax Main Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./webSocketsMainPage.jsp">not Ready _ Web Sockets Main Page</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">? create Type Script Main
                        Page ?</a>
                </li>
            </ul>
        </div>
    </nav>

    <%--  //////////////////////////////////////////////////////////////////////////////  --%>
    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">?q= Parameters _ ..</h4>
            </div>
            <div class="card-body">
                <%--                <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>--%>
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="simpleJSP/pageForFirstServlet.jsp">pageForFirstServlet</a></li>
                    <li><a class="text-muted" href="${contextPath}/hello-FirstServlet">сервлет
                        FirstServlet</a></li>
                    <li><a class="text-muted" href="${contextPath}/hello-SecondServletParameter">сервлет
                        SecondServletParameter через маппинг</a></li>
                    <li><a class="text-muted" href="${contextPath}/hello-secondJsp">JSP страница
                        secondJsp.jsp через
                        маппинг</a></li>
                    <li><a class="text-muted" href="showCart.jsp">showCart.jsp БЕЗ маппинга</a></li>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Cookies _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="${contextPath}/cookies-SetCookiesServlet">SetCookiesServlet
                        маппинга</a></li>
                    <li><a class="text-muted" href="${contextPath}/cookies-GetCookiesServlet">GetCookiesServlet
                        маппинга</a></li>
                    <li><a class="text-muted" href="${contextPath}/cookies-DeleteCookiesServlet">DeleteCookiesServlet
                        маппинга</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">CRUD DB _ Имитация с помощью списка List..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="simpleJSP/appIndex.jsp">appIndex.jsp App CRUD from Java Rush</a>
                    </li>
                    <button class="w3-btn w3-hover-light-blue w3-round-large"
                            onclick="location.href='${contextPath}/add'">Add user
                    </button>
                    <button class="w3-btn w3-hover-green w3-round-large"
                            onclick="location.href='${contextPath}/list'">List users
                    </button>
                    <li><a class="text-muted" href="${contextPath}/hello-LibraryServletSQL">LibraryServletSQL
                        маппинга</a></li>
                </ul>
            </div>
        </div>
        <%--  class="card-deck mb-3 text-center"  --%>
    </div>

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Redirect Examples _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="simpleJSP/pageRedirect.jsp">pageRedirect.jsp БЕЗ
                        маппинга</a>
                    </li>
                    <li><a class="text-muted" href="simpleJSP/startPageForRedirect.jsp">startPageForRedirect.jsp
                        Redirect Examples Page</a></li>
                    <li><a class="text-muted" href="simpleJSP/jsPageRedirect.jsp">Java Script
                        PageRedirect.jsp БЕЗ маппинга</a></li>
                    <li><a class="text-muted" href="simpleJSP/jQueryPageRedirect.jsp">jQuery
                        PageRedirect.jsp БЕЗ маппинга (неразобрался с jQuery)</a></li>

                    <%--&lt;%&ndash; https://devcolibri.com/%D0%BA%D0%B0%D0%BA-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D1%82%D1%8C-servlet-%D0%BF%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5-%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE/                   &ndash;%&gt;--%>
                    <%--                    <li><a class="text-muted" href="simpleJSP/SetValueToJspPage.jsp">--%>
                    <%--                        SetValueToJspPage.jsp (Передаем данные с Servlet на JSP)</a></li>--%>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Session _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="${contextPath}/hello-SessionServlet">SessionServlet
                        через маппинг</a></li>
                    <li><a class="text-muted" href="${contextPath}/hello-SessionCartServlet">SessionCartServlet
                        через
                        маппинг</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">my SQL _ DB CRUD _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="MyDbPages/ShowSomeAll.jsp">list table ShowSomeAll.jsp</a></li>
                    <li><a class="text-muted" href="MyDbPages/AddColumns.jsp">add column AddColumns.jsp</a></li>
                    <li><a class="text-muted" href="MyDbPages/DeleteColumns.jsp">delete column DeleteColumns.jsp</a>
                    </li>
                    <li><a class="text-muted" href="MyDbPages/AddDataToBase.jsp">write data AddDataToBase.jsp</a></li>
                    <li><a class="text-muted" href="MyDbPages/AllinOneCRUD.jsp">All CRUD AllinOneCRUD.jsp</a></li>
                </ul>
            </div>
        </div>
        <%--  class="card-deck mb-3 text-center"  --%>
    </div>
    <%--   class="container"   --%>
</div>

<page:bootStrapFooter/>
</body>
</html>