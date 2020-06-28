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
    <h1 class="display-4">Simple Examples & code trash (Web Sockets)</h1>
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
                    <a class="nav-link" href="./bootstrap.jsp">Home Main Page</a>
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
                <h4 class="my-0 font-weight-normal">Web Sockets 1 _ ..</h4>
            </div>
            <div class="card-body">
                <%--                <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>--%>
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="ajaxPages/ajaxGet.jsp">ajaxGet.jsp</a></li>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Web Sockets 2 _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="${contextPath}/cookies-SetCookiesServlet">SetCookiesServlet
                        маппинга</a></li>
                </ul>
            </div>
        </div>

        <div class="card mb-4 shadow-sm">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Web Sockets 3 _ ..</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li><a class="text-muted" href="simpleJSP/appIndex.jsp">appIndex.jsp App CRUD from Java Rush</a>
                    </li>
                </ul>
            </div>
        </div>
        <%--  class="card-deck mb-3 text-center"  --%>
    </div>

    <%--  class="card-deck mb-3 text-center"  --%>
</div>
<%--   class="container"   --%>

<page:bootStrapFooter/>
</body>
</html>