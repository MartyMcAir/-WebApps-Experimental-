<%@ tag pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
            aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="./simpleJSP/myPage.jsp">First Main Servlet</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}">Home Main Page</a>
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
