<%@ page import="java.util.List, java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 01.05.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- taglib не рабоатает -->
<html>
<head>

  <title>$Title$</title>
</head>
<body>
<h1>My Page is works!</h1>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<%--это еще HTML--%>
<% //А тут можно писать Java-код
  String strMy = "моя строка";
  List<String> list = new ArrayList<String>();
  list.add("one");
  list.add("two");
  list.add("three");

  String s = "Вся власть роботам!";
  for (int i = 0; i < 10; i++) {
    out.println(s);
    out.println("<br>");
  } %>
<%--а это уже опять HTML--%>

<%= strMy%>

<!-- Выводим наш сервлет из C:\z_n\Dropbox\BitBucket\FirstWebApp\src\main\java\servlets\SimpleServlet.java -->
<!-- не рабоатает -->
${requestScope.listAttributes}

</body>
</html>