<%@ page import="MyDB_CRUD.SQL_Operations" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 12.06.2020
  Time: 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>

<!doctype html>
<html lang="en">
<head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
</head>

<body>
<page:bootstrapHeader/>

<div class="container">

    <page:navBarMainsPages/>

    <strong>Mapping Example _ path </strong>

    <% final String params = (String) request.getAttribute("params");
        out.println("getRequestURI: " + request.getRequestURI());
        out.println("<br/>Params is: ");
        out.println(params);
    %>
    <%--   class="container"   --%>
</div>

<page:bootStrapFooter/>
</body>
</html>