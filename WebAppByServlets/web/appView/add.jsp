<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 04.05.2020
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Jsp</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Hello to page for Add.Jsp _ Add new User</h1>
</div>
<h3> отступ _ ..</h3>
<p><a href="${pageContext.request.contextPath}/simpleJSP/appIndex.jsp">Назад на appIndex.jsp</a></p>

<p><a href="../"> на главную</a></p>

<%-- https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2 --%>
<%-- у формы указан атрибут method со значением post. Это говорит о том, что данные из этой формы
олетят на сервер в виде POST-запроса. Атрибут action не указан, значит запрос отправится по тому же адресу,
 по которому мы перешли на эту страничку (/add).
 Таким образом, наш сервлет, привязанный к этому адресу, при получении GET-запроса возвращает эту jsp
 с формой добавления пользователей, а если получит POST-запрос,
 значит, форма отправила туда свои данные (которые мы в методе doPost() вытащим из объекта запроса,
  обработаем и передадим в модель на сохранение). --%>
<form method="post">
    <label>Name: <input type="text" name="name"><br/> </label>
    <label>Password: <input type="password" name="pass"><br/> </label>
    <button type="submit">Submit</button>
</form>

<%-- https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2 --%>
<h3>Отступ ниже форма Version 2</h3>
<div>
    <% if (request.getAttribute("userName") != null)
        out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>"); %>
    <form method="post">
        <label>Name: <input type="text" name="name"><br/></label>
        <label>Password:<input type="password" name="pass"><br/></label>
        <button type="submit">Submit</button>
    </form>
</div>

<h3>Отступ ниже форма Version 3</h3>
<div class="w3-container w3-padding">
    <% if (request.getAttribute("userName") != null) {
        out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                "   <h5>User '" + request.getAttribute("userName") + "' added!</h5>\n" +
                "</div>");
    } %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Add user</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Name:
                <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <label>Password:
                <input type="password" name="pass" class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
<%--    <button class="w3-btn w3-round-large" onclick="location.href='../'">Back to main</button>--%>
    <button class="w3-btn w3-round-large" onclick="location.href='${pageContext.request.contextPath}'">Back to main</button>
</div>

</body>
</html>
