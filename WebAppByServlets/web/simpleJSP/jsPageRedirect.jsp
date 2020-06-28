<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 02.05.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Js Page Redirect</title>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<page:navBarMainsPages/>

<script language='javascript'>
    var delay = 5000;
    setTimeout("document.location.href='${pageContext.request.contextPath}'", delay);
</script>
<p>У нашего сайта новый адрес: <b>http://localhost:8080/FirstWebApp_war_exploded/</b>.
    Через 5 секунд Вы будете перенаправлены на него.
    Если этого не происходит, то перейдите самостоятельно:
    <a href="${pageContext.request.contextPath}">Переход на сайт</a>
</p>

</body>
</html>
