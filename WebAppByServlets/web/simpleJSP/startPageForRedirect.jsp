<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 07.06.2020
  Time: 18:53
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

<%--<form name="forward1" action="">--%>
<%--<form name="forwardForm" action="/RedirectForward">--%>
<%-- wrap in JSTL <url> --%>
<%--<form name="forwardForm" action="<c:url value="/RedirectForward"/>">--%>
<%-- with dinamic prefix _ Reccomended --%>
<form name="forwardForm" action="${pageContext.request.contextPath}/RedirectForward">
    перенаправить на старинцу внутри сайта (Forward):
    <%--    <select name="pageName" size="1" multiple>--%>
    <select name="pageName">
        <option selected value="/">на главную</option>
        <option value="/simpleJSP/testForward1.jsp">Test Forward to 1 page</option>
        <option value="/simpleJSP/testForward2.jsp">Test Forward to 2 page</option>
        <option value="/TestServletForForward">Test Forward to test Servlet</option>
    </select> <br>
    <button type="submit">submit</button>
</form>

<h1>Or redirect to google with your query</h1>
<form name="redirectForm" action="${pageContext.request.contextPath}/RedirectForward">
    перйти в google с запросом в поиске: <input type="text" name="pageName">
    <button type="submit"> submit</button>
</form>
</body>
</html>
