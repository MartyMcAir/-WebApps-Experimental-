<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <link rel="stylesheet" href="${contextPath}/public_resources/css/forAjaxJsonPage.css">
    <title>Simple Web App (with Bootstrap)</title>
</head>

<body>
<page:bootstrapHeader/>
<div class="container">
    <page:navBarMainsPages/>
    <hr>
    <ul class="list-unstyled mt-3 mb-4">
        <li><a class="text-muted" href="${contextPath}/ajaxPages/ajaxJsonTblByPureJs.jsp">current page is:
            ajaxJson.jsp</a></li>
    </ul>

    <strong>My Experimental Get Table with JSON</strong>
    <table id="expTable" class="table"></table>

    <%--    <strong> Example _ Form Add in Table</strong>--%>
    <%--    <hr>--%>
    <%--    <form id="addForm" action="${pageContext.request.contextPath}/AddData">--%>
    <form id="formPlace" name="formNameIsAdd" class="tableFormIs">
    </form>
    <strong id="resultId"> Here Info For Current Submit 2.5sec &hide</strong>

    <br>
    <br>
    resultId2
    <strong>DELETE FROM table WHERE id=.. </strong>
    <form id="delFormId" name="delForm">
        Удалить данные с базы у которого ID:
        <select name="columnID" id="selectId">
        </select>
        <button class="btn btn-primary btn-sm" type="submit" id="submitId">submit</button>
    </form>
    <strong id="resultId2">Here Info For Current Del Status 2.5sec &hide</strong>

    <%--    <strong> Or delete All elements by one click</strong>--%>
    <%--    <button class="btn btn-primary btn-sm" type="submit" id="btnDelAllElements">Delete all Elements</button>--%>

    <br>
    <hr>
    <p>Click the button to DELETE</p>
    <button onclick="myFunction()">Try it</button>
    <div id="myIdIs">This can be deleted by click</div>
    <script>
        function myFunction() {
            const idItem = document.querySelector('#myIdIs')
            // const idItem = document.querySelector('#id19')
            idItem.remove()
        }
    </script>

    <hr>
    <ul class="list-unstyled mt-3 mb-4">
        <h3>More about Tables, info links is: </h3>
        <li><a class="text-muted" href="${contextPath}/ajaxPages/ajaxJsonTblByPureJs.jsp">current page is:
            ajaxJson.jsp</a></li>
        <li><a class="text-muted" href="https://datatables.net/extensions/scroller/examples/styling/bootstrap4.html"
               target="_blank">datatables.net/extensions/..</a></li>
        <li><a class="text-muted" href="https://editor.datatables.net/examples/styling/bootstrap4"
               target="_blank">editor.datatables.net/examples/styling..</a></li>
        <li><a class="text-muted" href="https://datatables.net/extensions/responsive/examples/styling/bootstrap4.html"
               target="_blank">datatables.net/extensions/responsive/..</a></li>
        <li><a class="text-muted" href="https://getbootstrap.com/docs/4.0/content/tables/"
               target="_blank">getbootstrap.com.. table style..</a></li>
        <li><a class="text-muted" href="https://www.w3schools.com/bootstrap4/bootstrap_tables.asp"
               target="_blank">w3schools.com.. table style..</a></li>
        <li><a class="text-muted" href="https://bootstrap-table.com/"
               target="_blank">bootstrap-table.com..</a></li>
        <li><a class="text-muted" href="https://stackoverflow.com/questions/31888566/sort-table-rows-in-bootstrap"
               target="_blank">stackoverflow - Sort table rows In Bootstrap</a></li>
    </ul>

    <%--  class="container"  --%>
</div>
<page:bootStrapFooter/>
<script src="${contextPath}/public_resources/js/exp/ajaxMainGet.js"></script>
<script src="${contextPath}/public_resources/js/exp/ajaxGetJsonAndShow.js"></script>
<script src="${contextPath}/public_resources/js/exp/ajaxAdd.js"></script>
<%-- ПРИ КОЖКЛЮЧЕНИИ НЧИЕГО НЕ  добавляется а точнее добавляется но как то не верно .. --%>
<script src="${contextPath}/public_resources/js/exp/ajaxDel.js"></script>
<%--<script src="${contextPath}/public_resources/js/examples/JSON_iteration.js"></script>--%>

</body>
</html>
