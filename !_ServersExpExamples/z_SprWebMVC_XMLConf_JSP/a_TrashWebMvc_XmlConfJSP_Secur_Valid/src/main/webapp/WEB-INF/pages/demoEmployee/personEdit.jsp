<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="<c:url value="/resources/my.css" />" rel="stylesheet">
    <title>Person</title>

    <style>
    span.error {
        color: red;
    }
    form div{
        margin: 5px;
    }
    </style>
</head>
<body>
<h3>
    Enter .
</h3>
          <a href="${contextPath}/">main</a>

    <form:form method="POST" modelAttribute="person" action="${contextPath}/demoEmployee/save">
        <div>
        Name:
        <form:input path="name"/>
        <form:errors path="name" cssClass="error"/>
        </div>

        <div>
        Age:
        <form:input path="age"/>
        <form:errors path="age" cssClass="error"/>
        </div>

        <button type="submit">Registration</button>
    </form:form>

</body>
</html>