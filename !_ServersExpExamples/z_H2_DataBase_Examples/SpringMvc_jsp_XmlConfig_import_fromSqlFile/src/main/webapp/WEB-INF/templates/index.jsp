<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="utf-8"/>
    <title>Main Page</title>

    </head>

    <body>

      <h2 class="hello-title" th:text="'Hello ' + ${name} + '!'"></h2>

      <ul>
        <li>
          <a href="./">main</a>
        </li>      </br>    </br>
=
</ul>

</body>
</html>
