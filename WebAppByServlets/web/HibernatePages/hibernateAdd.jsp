<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 16.07.2020
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<form method="get" name="forwardForm" action="${pageContext.request.contextPath}/HibernateAdd">
    добавить колонку: <input name="columnName" type="text">
    <button type="submit">submit</button>
</form>
</body>
</html>
