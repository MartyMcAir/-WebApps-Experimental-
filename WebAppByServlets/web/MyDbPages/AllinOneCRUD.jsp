<%@ page import="util.SQL_Operations" %>
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
    <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
                aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}"
                                        tabindex="-1">Назад на главную _ HOME</a></li>
                <strong>-></strong>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/MyDbPages/AllinOneCRUD.jsp"
                       tabindex="-1">Назад на AllinOneCRUD.jsp</a></li>
            </ul>
        </div>
    </nav>

    <%
        SQL_Operations sql_operations = new SQL_Operations();
        List<String> books = sql_operations.getAllColumnsNamesFromTable("books");
    %>

    <%--   Show Table Content   --%>
    <%--  _________________________________________________________________________  --%>
    <h5>Retrieve data from database in jsp</h5>
    <%-- FROM  https://www.studentstutorial.com/java-project/jsp-retrieve-data-using-mysql.php --%>
    <table id="myTable" border="1" class="table">
        <thead>
        <tr>
            <% // выводим имена колонок
                for (String item : books) out.println("<th scope=\"col\">" + item + "</th>");
            %>
        </tr>
        </thead>

        <tbody>
        <%
            try (ResultSet resultSet = sql_operations.getResultSetByTable("books")) {
                while (resultSet.next()) {
        %>
        <tr>
            <%
                for (int i = 0; i < books.size(); i++) {
                    if (i == 0) {
                        out.print("<th scope=\"row\">");
                        out.print(resultSet.getString(books.get(i)));
                        out.print("</th>");
                    } else {
                        out.print("<td>");
                        out.print(resultSet.getString(books.get(i)));
                        out.print("</td>");
                    }
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
    <form name="forwardForm" action="${pageContext.request.contextPath}/AddColumns" class="needs-validation"
          novalidate="">
        <div class="row"> добавить колонку:
            <input name="columnName" type="text" class="form-control col-md-3 mb-3">
            <button class="btn btn-primary btn-sm" type="submit">submit</button>
        </div>
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
        <button class="btn btn-primary btn-sm" type="submit">submit</button>
    </form>
    <%--  _________________________________________________________________________  --%>


    <hr>
    <%--   ADD INTO Table   --%>
    <%--  _________________________________________________________________________  --%>
    <strong>INSERT INTO table ..</strong>
    <form name="myForm" action="${pageContext.request.contextPath}/AddData">
        <table id=" myTable1">
            <tbody>
            <tr>
                <% // id - то не стоит изменять..
                    for (int k = 1; k < books.size(); k++) out.println("<th>" + books.get(k) + "</th>");
                %>
            </tr>
            <tr>
                <% // id сам инкрементится
                    for (int i = 1; i < books.size(); i++) {
                        out.print("<td>");
                        if (i == 3)
                            out.print("<input name=\"" + books.get(i) + "\" type=\"number\" class=\"ol-md-3 mb-3 form-control\">");
                        else
                            out.print("<input name=\"" + books.get(i) + "\" type=\"text\" class=\"ol-md-3 mb-3 form-control\">");
                        out.print("</td>");
                    }
                    request.setAttribute("ajax", "false");
                %>
            </tr>
            </tbody>
        </table>
        <button class="btn btn-primary btn-sm" type="submit">submit</button>
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
        <button class="btn btn-primary btn-sm" type="submit">submit</button>
    </form>
    <%--  _________________________________________________________________________  --%>

    <%--   class="container"   --%>
</div>

<page:bootStrapFooter/>
</body>
</html>