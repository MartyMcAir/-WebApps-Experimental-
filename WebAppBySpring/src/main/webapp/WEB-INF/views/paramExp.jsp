<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 05.07.2020
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="thirdParam">Go too Main Page</a>

<form action="${pageContext.request.contextPath}/getParamByServletReq">
    <label> Param firstParam:
        <input name="firstParam">
    </label>
    <label> Param secondParam:
        <input name="secondParam">
    </label>
    <button>Send Request</button>
</form>

<p>Your first param is: <% request.getAttribute("firstParam: "); %></p>
<p>Your second param is: <% request.getAttribute("secondParam: "); %></p>


</body>
</html>
