<%--
  Created by IntelliJ IDEA.
  User: MartyMcAir
  Date: 02.05.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>

<html>
<head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
</head>
<body>
<page:navBarMainsPages/>

<script language='javascript'>
    // https://coderoad.ru/503093/%D0%9A%D0%B0%D0%BA-%D1%8F-%D0%BC%D0%BE%D0%B3%D1%83-%D0%BF%D0%B5%D1%80%D0%B5%D0%BD%D0%B0%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D1%82%D1%8C-%D0%BD%D0%B0-%D0%B4%D1%80%D1%83%D0%B3%D1%83%D1%8E-%D0%B2%D0%B5%D0%B1-%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D1%83

    // window.location
    // window.location.replace('http://www.example.com')
    // window.location.assign('http://www.example.com')
    // window.location.href = 'http://www.example.com'
    // document.location.href = '/path'

    // window.history
    // window.history.back()
    // window.history.go(-1)

    // window.navigate; ONLY for old versions of Internet Explorer
    // window.navigate('top.jsp')


    // Probably no bueno
    // self.location = 'http://www.example.com';
    // top.location = 'http://www.example.com';

    // jQuery
    // $(location).attr('href', 'http://www.example.com', 500);
    // $(window).attr('location', 'http://www.example.com');
    // $(location).prop('href', 'http://www.example.com');

    //////
    // https://www.rapidtables.com/web/dev/jquery-redirect.html
    // jQuery URL redirection
    <%--$(document).ready(function () {--%>
    <%--    url = "${pageContext.request.contextPath}";--%>
    <%--    $(location).attr("href", url);--%>
    <%--});--%>

    setTimeout(function () {
        window.location.href = "${pageContext.request.contextPath}"; // The URL that will be redirected too.
    }, 3000); // The bigger the number the longer the delay.

    // function YourJavaScriptFunction() {
    //     var i = $('#login').val();
    //     if (i == 'login')
    //         window.location = "login.php";
    //     else
    //         window.location = "Logout.php";
    // }

</script>
<p>У нашего сайта новый адрес: <b>http://localhost:8080/FirstWebApp_war_exploded/</b>.
    Через 3 секунды Вы будете перенаправлены на него.
    Если этого не происходит, то перейдите самостоятельно:
    <a href="${pageContext.request.contextPath}">Переход на сайт</a>
</p>

</body>
</html>
