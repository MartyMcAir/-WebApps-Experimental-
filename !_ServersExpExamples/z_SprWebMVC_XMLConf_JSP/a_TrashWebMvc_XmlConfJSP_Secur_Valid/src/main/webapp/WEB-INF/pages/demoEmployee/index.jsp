<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8"/>
    <title>Main Page</title>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
    </head>
    <body>

      <ul>
        <li>
          <a href="${contextPath}/">main</a>
        </li>      </br>    </br>

    <li><a href="${contextPath}/demoEmployee/editPage">editPage</a></li>      </br>    </br>

</ul>

</body>
</html>
