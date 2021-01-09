<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8"/>
    <title>Main Page</title>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
    </head>
    
    <body>

      <h2 class="hello-title" th:text="'Hello ' + ${hello_variable} + '!'"></h2>

      <ul>
        <li>
          <a href="./">main</a>
        </li>      </br>    </br>
                <li>
                    <a href="./customer"> customers </a>
                </li>   </br>    </br>
                                                <li>
                                                    <a href="./sub_main"> sub main </a>
                                                </li>   </br>    </br>

</ul>

</body>

</html>