<%--
  Created by IntelliJ IDEA.
  User: marty
  Date: 14.06.2020
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <%--    <page:bootStrapHead/>--%>
    <title>Simple Web App (with Bootstrap)</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${contextPath}/public_resources/bootstrap4_5/css/bootstrap.min.css">
    <%--    <script src="${contextPath}/public_resources/bootstrap4_5/js/bootstrap.min.js"></script>--%>
    <%--    <script>// по окончанию загрузки страницы--%>
    <%--    $(document).ready(function () {--%>
    <%--        // вешаем на клик по элементу с id = example-1--%>
    <%--        $('#example-1').click(function () {--%>
    <%--            // загрузку HTML кода из файла example.html--%>
    <%--            // $(this).load('ajax/example.html');--%>
    <%--            // OR--%>
    <%--            $(this).load("<div> новое HTML </div>");--%>
    <%--        })--%>
    <%--    }); </script>--%>
</head>

<body>
<page:bootstrapHeader/>
<div class="container">
    <page:navBarMainsPages/>
    <hr>
    <ul class="list-unstyled mt-3 mb-4">
        <li><a class="text-muted" href="${contextPath}/ajaxPages/ajaxGet.jsp">current page is: ajaxGet.jsp</a></li>
    </ul>


    <strong>test form 1</strong>
    <form action="${contextPath}/ExperimentalAjaxServlet">
        <button class="example cursor" id="example-1">Click to update</button>
    </form>

    <strong>test, send value form to server </strong>
    <%--    ошибка сама исчезла непонял как --%>
    <%--    <p>Ошибка синтаксического анализа XML: ошибка синтаксиса Адрес:.. </p>--%>
    <form id="form">
        <label>Test field: <input class="input1Class" name="testName" type="text"></label>
        <br><br>
        <button type="submit">Submit form</button>
    </form>
    <p id="log"></p>

    <hr>
    <strong> table by js (from https://html5css.ru/edithtm/index.php) </strong>

    <p>Click the button to create a TABLE, a TR and a TD element.</p>
    <button onclick="myFunction()">Try it</button>

    <div id="parentForTable"></div>
    <script>
        // From https://html5css.ru/edithtm/index.php
        const parentForTable = document.querySelector('#parentForTable')

        function myFunction() {
            const x = document.createElement('TABLE')
            x.setAttribute('id', 'myTable')
            // document.body.appendChild(x)
            parentForTable.appendChild(x)

            const y = document.createElement('TR')
            y.setAttribute('id', 'myTr')
            document.getElementById('myTable').appendChild(y)

            const z = document.createElement('TD')
            const t = document.createTextNode('cell')
            z.appendChild(t)
            document.getElementById('myTr').appendChild(z)
        }
    </script>

    <strong>My Experimental Get TableHead withOut JSON _ get by plain/text</strong>
    <%--    <button onclick="myFunction2()">Try it</button>--%>
    <table id="expTable" class="table">

    </table>


    <hr>
    <hr>
    <hr>
    <strong> Example _ Table</strong>
    <table id="myTable" class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">title</th>
            <th scope="col">author</th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>testTItle</td>
            <td>authorTor</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>testTItle2</td>
            <td>authorTor2</td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td>testTItle4</td>
            <td>author4</td>
        </tr>
        <tr>
            <th scope="row">9</th>
            <td>Tor</td>
            <td>SHtorm</td>
        </tr>
        <tr>
            <th scope="row">11</th>
            <td>Thanos</td>
            <td>Titan</td>

        </tr>
        </tbody>
    </table>


    <strong> Example _ Form Add in Table</strong>
    <form name="myForm" action="#">
        <table id="myTable1">
            <tbody>
            <tr>
                <th>title</th>
                <th>author</th>
                <th>quantity</th>
            </tr>
            <tr>
                <td><input name="title" type="text" class="ol-md-3 mb-3 form-control"></td>
                <td><input name="author" type="text" class="ol-md-3 mb-3 form-control"></td>
                <td><input name="quantity" type="number" class="ol-md-3 mb-3 form-control"></td>
            </tr>
            </tbody>
        </table>
        <button class="btn btn-primary btn-sm" type="submit">submit</button>
    </form>

    <%--  class="container"  --%>
</div>
<page:bootStrapFooter/>
<script src="${contextPath}/public_resources/js/exp/ajaxMainGet.js"></script>
<script src="${contextPath}/public_resources/js/exp/ajaxGetTableHeadPlainText.js"></script>
<script src="${contextPath}/public_resources/js/exp/ajaxGetFromForm.js"></script>

</body>
</html>
