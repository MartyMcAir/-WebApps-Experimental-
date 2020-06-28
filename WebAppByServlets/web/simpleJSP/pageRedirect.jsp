<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 02.05.2020
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page Redirect to Google</title>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<%-- такая попытка вызову сервлета внутри JSP не работает --%>
<%--<%@ page import="servlets.ForwardServlet" %>--%>
<%--<% RedirectServlet redirectServlet = new RedirectServlet();--%>
<%--redirectServlet.init(); %>--%>


<% String name = request.getParameter("name");%>
<p>Parameter name is: <%= name %></p>

<p><a href="${pageContext.request.contextPath}/hello-ForwardServlet">Перейти через Forward, на др страницу этого же сайта</a></p>

<form name="stay on page" action="${pageContext.request.contextPath}/simpleJSP/pageRedirect.jsp"<%= name %>>
    Query: <input type="text" name="name"><br>
    <button type="submit">Отправить Параметр и остаться на этой же страницце</button>
</form>

<%-- ${pageContext.request.contextPath}/RedirectForward --%>
<form name="G form" action="https://www.google.com?q="<%= name %>>
<%--<form name="G form" action="https://www.google.com?q="<%= name %>>--%>
    <button type="submit">Перейти в Google с этим же запросом</button>
</form>



<% // такой подход не работает надо JS
//    if (name != null) {
//        out.println("<p>Ваши данные получен: <p>" + name + " and wait for 500ms for Redirect");
//        out.println("через 500 мс ваш запрос будет перенаправлен в Google");
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // Сам редирект в Google
//        response.sendRedirect("https://www.google.com?q=" + name);
//    }
%>

</body>
</html>
