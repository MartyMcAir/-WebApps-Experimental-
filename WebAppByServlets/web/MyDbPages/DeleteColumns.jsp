<%@ page import="MyDB_CRUD.SQL_Operations" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 08.06.2020
  Time: 20:28
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
<page:navBarMainsPages/>

<% SQL_Operations sql_operations = new SQL_Operations();
    List<String> books = sql_operations.getAllColumnsNamesFromTable("books"); %>

<form name="forwardForm" action="${pageContext.request.contextPath}/DeleteColumns">
    Удалить колонку:
    <%--    <select name="pageName" size="1" multiple>--%>
    <select name="columnName">
        <% for (String item : books) {
            out.print("<option value=\"" + item + "\" > ");
            out.print(item);
            out.print("</option>");
        } %>
    </select> <br>
    <button type="submit">submit</button>
</form>

</body>
</html>
