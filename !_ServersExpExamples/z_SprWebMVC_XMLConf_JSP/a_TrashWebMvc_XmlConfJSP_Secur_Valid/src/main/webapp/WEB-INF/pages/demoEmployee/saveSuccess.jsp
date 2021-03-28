<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="<c:url value="/resources/my.css" />" rel="stylesheet">
    <title>Person Saved Successfully</title>

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
    Person Saved Successfully.
</h3>

          <a href="${contextPath}/">main</a>

<form:form method="POST" modelAttribute="person" action="${contextPath}/edit">
    <div>
        ${name}
    </div>
    <div>
        ${age}
    </div>
    <button type="submit">Edit</button>
</form:form>
</body>
</html>