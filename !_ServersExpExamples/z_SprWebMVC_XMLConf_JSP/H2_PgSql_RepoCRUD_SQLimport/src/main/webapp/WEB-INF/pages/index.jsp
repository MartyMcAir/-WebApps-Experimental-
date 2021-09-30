<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title>Main Page</title>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<h3> Hibernate Examples () </h3>

<ul>
    <li>
        <a href="./">main</a>
    </li>
    </br>
    </br>
        <li>
            <a href="${contextPath}/customerRepository"> customers Using Repository </a>
        </li>
    </br>
    </br>
        <li>
            <a href="${contextPath}/lbClassicStatus"> lbClassicStatus </a>
        </li>
    </br>
    </br>
</ul>

</body>
</html>