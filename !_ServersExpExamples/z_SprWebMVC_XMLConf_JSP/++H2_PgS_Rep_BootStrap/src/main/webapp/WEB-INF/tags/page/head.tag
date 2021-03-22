<%@ tag pageEncoding="utf-8" language="java" %>

<!-- использование taglib вместо вручную подключений скриптов и прочего >>> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>

<head>
    <meta charset="utf-8">
    <page:metrika/>

    <property name="prefix" value="/WEB-INF/views/"></property>

    <%--    <%@include file="/styles/style.css"%>--%>
    <%--    запаботало тюкю надо было указывать полный путь через contextPath +..--%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <%--    <link rel="stylesheet" href="${contextPath}/resources/css/myCss.css"/>--%>
    <%--    <script src="${contextPath}/resources/js/myJs.js"></script>--%>

    <page:myCss/>

    <!-- <<< использование taglib вместо вручную подключений скриптов и прочего -->
    <%--    пути http://localhost:8080/FirstWebApp_war_exploded/js/myJs.js
            видит но к странице не подключает--%>
    <title>$Title$</title>
</head>