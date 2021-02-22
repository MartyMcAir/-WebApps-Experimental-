<%@ page import="util.SQL_Operations" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 08.06.2020
  Time: 20:46
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

<%-- думал так сделать --%>
<%--<h1>Тут при создании записи в БД = заполнение полей объекта и его отправка..</h1>--%>

<% SQL_Operations sql_operations = new SQL_Operations();
    List<String> books = sql_operations.getAllColumnsNamesFromTable("books");
    books.remove("id"); // id - то не стоит изменять..
%>

<table id="myTable1" border="1">
    <tbody>
    <tr>
        <%
            for (String item : books)
                out.println("<th>" + item + "</th>");
        %>
    </tr>
    </tbody>
</table>

<form name="myForm" action="${pageContext.request.contextPath}/AddData">
    <%
        for (String item : books) {
            out.print("<input name=\"" + item + "\" type=\"text\">");
        }
    %>
    <button type="submit">submit</button>
</form>
</body>
</html>