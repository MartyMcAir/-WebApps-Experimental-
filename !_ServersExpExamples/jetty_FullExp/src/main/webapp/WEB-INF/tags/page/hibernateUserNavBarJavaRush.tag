<%@ tag pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%-- https://javarush.ru/groups/posts/523-vashe-pervoe-prilozhenie-s-ispoljhzovaniem-java-servletov --%>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
            aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon">test</span>
    </button>
    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link text-muted"
                                    href="./HibernatePages/indexUser.jsp">indexUser.jsp</a></li>
            <li class="nav-item"><a class="nav-link text-muted"
                                    href="./HibernatePages/addUser.jsp">addUser.jsp</a>
            </li>
            <li class="nav-item"><a class="nav-link text-muted" href="./HibernatePages/updateUser.jsp">updateUser.jsp</a>
            </li>
            <li class="nav-item"><a class="nav-link text-muted" href="./HibernatePages/deleteUser.jsp">deleteUser.jsp</a>
            </li>
            <li class="nav-item"><a class="nav-link text-muted"
                                    href="./HibernatePages/showUsers.jsp">showUsers.jsp</a></li>
        </ul>
    </div>
</nav>
