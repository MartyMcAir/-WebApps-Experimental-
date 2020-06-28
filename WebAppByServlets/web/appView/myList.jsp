<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 04.05.2020
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Jsp</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Hello List Jsp</h1>
</div>

<h3> отступ _ ..</h3>
<p><a href="${pageContext.request.contextPath}/simpleJSP/appIndex.jsp">Назад на appIndex.jsp</a></p>
<h2>Users</h2>
<%-- ------------ADD--------------------------------------------------------------- --%>
<%-- у формы указан атрибут method со значением post. Это говорит о том, что данные из этой формы
полетят на сервер в виде POST-запроса. Атрибут action не указан, значит запрос отправится по тому же адресу,
 по которому мы перешли на эту страничку (/add).
 Таким образом, наш сервлет, привязанный к этому адресу, при получении GET-запроса возвращает эту jsp
 с формой добавления пользователей, а если получит POST-запрос,
 значит, форма отправила туда свои данные (которые мы в методе doPost() вытащим из объекта запроса,
  обработаем и передадим в модель на сохранение). --%>
<form method="get">
    <label>num1: <input type="text" name="num1"><br/> </label>
    <label>num2: <input type="text" name="num2"><br/> </label>
    <button type="submit">Submit</button>
</form>

<% HttpSession session1 = request.getSession();
    Object result1 = session1.getAttribute("result");
    // а, так всегда null _ потому что при передаче и получении надо сеттить в текущую сессию и брать из нее же
    String result = request.getParameter("result");

    if (result1 != null) out.print("result is: " + result1);
    else out.print("result is: null now.."); %>

<%-- ------------LIST--------------------------------------------------------------- --%>
<%-- Используя объект request, мы можем получить список имен, который передавали из сервлета
(мы прикрепили соответствующий атрибут к этому объекту), а используя объект out — можем вывести эти имена.   --%>
<% List<String> names = (List<String>) request.getAttribute("userNames");
    if (names != null && !names.isEmpty()) {
        out.println("<ul>");
        for (String s : names)
            out.println("<li>" + s + "</li>");
        out.println("</ul>");
    } else out.println("<p>There are no users yet!</p>"); %>


<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
<%--    <button class="w3-btn w3-round-large" onclick="location.href='../'">to main Page</button>--%>
    <button class="w3-btn w3-round-large" onclick="location.href='${pageContext.request.contextPath}'">to main Page</button>
</div>
</body>
</html>
