<%--<jsp:useBean id="name" scope="request" type="MyDB_CRUD.SQL_Operations"/>--%>
<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 10.06.2020
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<html>
<head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
</head>

<body>
<p><a href="${pageContext.request.contextPath}">Назад на главную</a></p>
<p><a href="${pageContext.request.contextPath}/MyDbPages/AllinOneCRUD.jsp">Назад на AllinOneCRUD.jsp</a></p>

<%--<h1>Hello ${name}</h1>--%>
<%--  _________________________________________________________________________  --%>


</body>
</html>