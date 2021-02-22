<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 08.06.2020
  Time: 20:25
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

<form name="forwardForm" action="${pageContext.request.contextPath}/AddColumns">
    добавить колонку: <input name="columnName" type="text">
    <button type="submit">submit</button>
</form>

</body>
</html>
