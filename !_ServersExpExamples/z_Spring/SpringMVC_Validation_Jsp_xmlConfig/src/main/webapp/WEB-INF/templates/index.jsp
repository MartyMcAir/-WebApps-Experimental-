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
        </li>      </br>    </br>
    <li>
      <a href="./employee">employee validator</a>
    </li>      </br>    </br>

    <!-- ERROR
       <li>
          <a href="./person">person like employee validator</a>
        </li>        -->

        <!-- java.lang.ClassNotFoundException: org.apache.oro.text.perl.Perl5Util
                 -->
        <li>
                  <a href="./signup">signup</a>
                </li>      </br>    </br>
                                 <a href="./oauth">oauth</a>
                                </li>      </br>    </br>
  </br>
</ul>

</body>
</html>
