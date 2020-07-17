<%@ page import="MyDB_CRUD.SQL_Operations, java.sql.ResultSet, java.util.List" %>
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
<%
    SQL_Operations sql_operations = new SQL_Operations();
    List<String> books = sql_operations.getAllColumnsNamesFromTable("books");
%>

<%--   Show Table Content   --%>
<%--  _________________________________________________________________________  --%>
<h1>Retrieve data from database in jsp</h1>
<%-- FROM  https://www.studentstutorial.com/java-project/jsp-retrieve-data-using-mysql.php --%>
<table id="myTable" border="1">
    <tbody>
    <tr>
        <%
            for (String item : books) out.println("<th>" + item + "</th>");
        %>
    </tr>
    <%
        try (ResultSet resultSet = sql_operations.getResultSetByTable("books")) {
            while (resultSet.next()) {
    %>
    <tr>
        <%
            for (int i = 0; i < books.size(); i++) {
                out.print("<td>");
                out.print(resultSet.getString(books.get(i)));
                out.print("</td>");
            }
        %>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
    </tbody>
</table>
<%--  _________________________________________________________________________  --%>


<hr>
<%--   ADD COLUMN in Table   --%>
<strong>ADD COLUMN text type varchar(100) in Table</strong>
<form name="forwardForm" action="${pageContext.request.contextPath}/AddColumns">
    добавить колонку: <input name="columnName" type="text">
    <button type="submit">submit</button>
</form>

<%--   DELETE From Table   --%>
<%--  _________________________________________________________________________  --%>
<strong>Delete Column From Table</strong>
<form name="forwardForm" action="${pageContext.request.contextPath}/DeleteColumns">
    Удалить колонку:
    <select name="columnName">
        <% for (int i = 1; i < books.size(); i++) {
            out.print("<option value=\"" + books.get(i) + "\" > ");
            out.print(books.get(i));
            out.print("</option>");
        } %>
    </select>
    <%--    <br>--%>
    <button type="submit">submit</button>
</form>
<%--  _________________________________________________________________________  --%>


<hr>
<%--   ADD INTO Table   --%>
<%--  _________________________________________________________________________  --%>
<strong>INSERT INTO table ..</strong>
<table id="myTable1" border="1">
    <tbody>
    <tr>
        <% // id - то не стоит изменять..
            for (int k = 1; k < books.size(); k++) out.println("<th>" + books.get(k) + "</th>");
        %>
    </tr>
    </tbody>
</table>

<form name="myForm" action="${pageContext.request.contextPath}/AddData">
    <% // id сам инкрементится
        for (int i = 1; i < books.size(); i++) out.print("<input name=\"" + books.get(i) + "\" type=\"text\">");
    %>
    <button type="submit">submit</button>
</form>
<%--  _________________________________________________________________________  --%>

<%--   DELETE INTO Table WHERE ID is..   --%>
<%--  _________________________________________________________________________  --%>
<strong>DELETE FROM table WHERE id=.. </strong>
<form name="forwardForm" action="${pageContext.request.contextPath}/DeleteOneRow">
    Удалить данные с базы у которых ID:
    <select name="columnID">
        <%
            List<String> ids = sql_operations.getDataByOneColumn("id");
            for (String item : ids) {
                out.print("<option value=\"" + item + "\" > ");
                out.print(item);
                out.print("</option>");
            }
        %>
    </select>
    <button type="submit">submit</button>
</form>
<%--  _________________________________________________________________________  --%>


</body>
</html>