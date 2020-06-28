<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 02.05.2020
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="logic.LogicTestClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Secon JSP</title>
</head>
<body>

<h1>Testing Jsp</h1>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<% LogicTestClass logicTestClass = new LogicTestClass(); %>
<p><%=logicTestClass.getLogic()  %>
</p>
<p><%= new java.util.Date() %>
</p>


<% String name = request.getParameter("name"); %>
<p>Parameter name is: <%= name%>
</p>

<form action="${pageContext.request.contextPath}/hello-secondJsp?name="<%= name %>>
    Имя: <input type="text" name="name"><br>
    <button type="submit">Отправить Параметр</button>
</form>

</body>
</html>
