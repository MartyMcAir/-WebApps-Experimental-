<%@ page import="MyDB_CRUD.SQL_Operations" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="MyDB_CRUD.DBConnectionPoolVector" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 08.06.2020
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<%
    SQL_Operations sql_operations = new SQL_Operations();
    List<String> columnNames = sql_operations.getAllColumnsNamesFromTable("books");
%>


<%--  https://mkyong.com/jdbc/jdbc-how-to-print-all-table-names-from-a-database/  --%>
<%--  _________________________________________________________________________  --%>
<h1>Retrieve data from database in jsp</h1>
<%-- FROM  https://www.studentstutorial.com/java-project/jsp-retrieve-data-using-mysql.php --%>
<table id="myTable" border="1">
    <tbody>
    <tr>
        <%
            for (String item : columnNames)
                out.println("<th>" + item + "</th>");
        %>
    </tr>
    <%
        try {
            ResultSet resultSet = sql_operations.getResultSetByTable("books");
            while (resultSet.next()) {
    %>
    <tr>
        <%
            for (int i = 0; i < columnNames.size(); i++) {
                out.print("<td>");
                out.print(resultSet.getString(columnNames.get(i)));
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

</body>
</html>
