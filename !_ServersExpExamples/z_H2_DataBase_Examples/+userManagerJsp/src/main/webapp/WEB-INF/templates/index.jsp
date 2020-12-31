<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <title>Main Page</title>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<h2 class="hello-title" th:text="'Hello ' + ${name} + '!'"></h2>

<ul>
    <li>
        <a href="./">main</a>
    </li>
    </br>
    <li>
        <a href="./hello">hello</a>
    </li>
    </br>
    <li>
        <a href="./user"> users </a>
    </li>
    </br>
</ul>
</body>
</html>