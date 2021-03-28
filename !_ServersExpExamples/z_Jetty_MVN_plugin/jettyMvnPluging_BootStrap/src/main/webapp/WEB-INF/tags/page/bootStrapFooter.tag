<%@ tag import="java.util.Calendar" %>
<%@ tag pageEncoding="utf-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<%--<img src="${myPath}/static/martyPNG.png" alt="here image"/>--%>
<%--<img src="C:\Users\marty\OneDrive\Dropbox\GitHub\-WebApps-Experimental-\WebAppByServlets\web\static\img\martyMcAirColor.svg"--%>
<%--     alt="here image"/>--%>

<footer class="pt-4 my-md-5 pt-md-5 border-top">
    <div class="row">
        <div class="col-12 col-md">
            <%--            <img class="mb-2" src="../assets/brand/bootstrap-solid.svg" alt="" width="24" height="24">--%>
            <%--            <img class="mb-2" src="${myPath}/img/martyPNG.png" alt="" width="24" height="24">--%>
            <%--            <img class="mb-2" src="${myPath}/img/martyMcAirHeadOnly_24.svg" alt="" width="36" height="36">--%>
            <img alt="" width="36" height="36" class="mb-2"
                 src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBzdGFuZGFsb25lPSJubyI/Pgo8IURPQ1RZUEUgc3ZnIFBVQkxJQyAiLS8vVzNDLy9EVEQgU1ZHIDIwMDEwOTA0Ly9FTiIKICJodHRwOi8vd3d3LnczLm9yZy9UUi8yMDAxL1JFQy1TVkctMjAwMTA5MDQvRFREL3N2ZzEwLmR0ZCI+CjxzdmcgdmVyc2lvbj0iMS4wIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciCiB3aWR0aD0iMTIyLjAwMDAwMHB0IiBoZWlnaHQ9IjEwNC4wMDAwMDBwdCIgdmlld0JveD0iMCAwIDEyMi4wMDAwMDAgMTA0LjAwMDAwMCIKIHByZXNlcnZlQXNwZWN0UmF0aW89InhNaWRZTWlkIG1lZXQiPgo8bWV0YWRhdGE+CkNyZWF0ZWQgYnkgcG90cmFjZSAxLjE2LCB3cml0dGVuIGJ5IFBldGVyIFNlbGluZ2VyIDIwMDEtMjAxOQo8L21ldGFkYXRhPgo8ZyB0cmFuc2Zvcm09InRyYW5zbGF0ZSgwLjAwMDAwMCwxMDQuMDAwMDAwKSBzY2FsZSgwLjEwMDAwMCwtMC4xMDAwMDApIgpmaWxsPSIjMDAwMDAwIiBzdHJva2U9Im5vbmUiPgo8cGF0aCBkPSJNNTAwIDk3NyBjMCAtNyA3IC0yMyAxNyAtMzYgMTYgLTI0IDE2IC0yNCAtMTMgLTE4IC02NCAxNSAtMTIwIDE3Ci0xMzQgNyAtMTMgLTkgLTEyIC0xMyA4IC0yOSAzMCAtMjYgMjggLTMxIC0xMiAtMzEgLTM1IDAgLTU2IC0xNCAtMzQgLTIzIDE1Ci01IDkgLTggLTU5IC0yNiAtMjQgLTcgLTQzIC0xNyAtNDMgLTI0IDAgLTcgMTggLTI1IDQwIC00MSA0NSAtMzQgNDEgLTQ1IC0xOQotNTMgLTIzIC0zIC00NCAtOSAtNDcgLTE0IC0yIC01IDEzIC0yMCAzNiAtMzQgMjIgLTE0IDQwIC0yOSA0MCAtMzQgMCAtNSAtMjAKLTE0IC00NSAtMjAgLTI1IC02IC00NSAtMTUgLTQ1IC0xOSAwIC01IDExIC0xOSAyNSAtMzIgMzYgLTM0IDMzIC01MSAtNyAtMzkKLTUyIDE1IC01NiAtNCAtMTQgLTY1IDIwIC0yOSAzNSAtNTggMzQgLTYzIC02IC0yMCA2MiAtMTEzIDEwMSAtMTM3IDIzIC0xNQo0MSAtMzAgNDEgLTM0IC0xIC0xMSAtOTggLTU0IC0xMDkgLTQ4IC0yMSAxNCAtMTggNDUgNiA2MSAzNyAyNCAxOSAzMSAtMjcgMTAKLTUzIC0yNCAtNjAgLTE0IC0xOCAyNSAzNiAzNCAzMyAzNSAtMjQgNSAtMjggLTEzIC0zOCAtMjUgLTM4IC00MiAwIC0xOSA1Ci0yMyAzMCAtMjMgMjUgMCAzMyAtNiA0NCAtMzEgMTIgLTI4IDExIC0zNiAtOSAtNzMgLTI0IC00NCAtNDEgLTEwMSAtMjggLTk0CjQ5IDI1IDg1IDM3IDExNyAzOSA1NCA1IDU3IC0xNiA0IC0yOCBsLTQzIC0xMSA1NSAtMSA1NSAtMSAyIDUwIGMwIDI4IC0zIDUwCi04IDUwIC01IDAgLTkgLTYgLTkgLTE0IDAgLTE3IC0xNSAtMjIgLTk0IC0zMSBsLTU4IC03IDE4IDMyIGMyNyA0NiA3MSA4MAoxMDQgODAgMjIgMCAzMSAtNyA0NSAtMzcgMjkgLTYyIDY5IC0xMjMgODIgLTEyMyA3IDAgLTEgMjEgLTE5IDUwIC0xNyAyOCAtMjkKNTIgLTI3IDU1IDMgMiAyNSAtOSA0OSAtMjYgNTYgLTM4IDEzNSAtNzkgMTUyIC03OSAyMiAwIC03IDIxIC04OCA2NCAtNDMgMjMKLTc5IDQ2IC04MSA1MiAtMiA2IDggMTYgMjIgMjIgMjEgMTAgMjUgOSAyNSAtMyAwIC0xMCAxMCAtMTUgMzEgLTE1IDE4IDAgMjgKNCAyNCAxMCAtMyA2IDEgMTggMTAgMjcgMTEgMTAgMTQgMjQgMTAgMzcgLTQgMTIgLTkgNDUgLTEyIDc0IC02IDYwIC0yNyA4MQotNTkgNTggLTE2IC0xMiAtMjMgLTEyIC00NyAxIC0yMiAxMSAtMzIgMTIgLTQzIDIgLTE3IC0xNCAtMTkgLTcwIC0zIC04NiAxMQotMTEgNSAtMzMgLTEwIC0zMyAtMTUgMCAtNTIgMTAwIC00NiAxMjEgNCAxMiAxNSAxOSAzMSAxOSAxMyAwIDI0IDUgMjQgMTAgMAo2IC00IDEwIC0xMCAxMCAtMjggMCA1IDk4IDQ4IDE0MyAzOCA0MSAxMTcgNzkgMTQ2IDcxIDEzIC0zIDI3IC0xIDMwIDUgNCA2CjIzIDExIDQyIDExIDM5IDAgNTQgLTIxIDM2IC01MCAtOSAtMTQgLTUgLTIyIDE2IC00MyA1MyAtNDkgNjkgLTUyIDEwOCAtMTgKNDAgMzYgNDIgMzYgNjYgMTUgMTQgLTE0IDIxIC0xNCAzMiAtNCAyNSAyMCAyOSA1MCAxMiA2OSAtMTQgMTYgLTE1IDIzIC00IDQ2CjExIDI1IDEwIDI5IC0yMCA1NiAtMzggMzQgLTU4IDM2IC04OCA4IC0yMSAtMjAgLTIzIC0yMCAtNDcgLTQgLTI1IDE2IC0yNiAxNgotNjUgLTIzIC0zMiAtMzIgLTQzIC0zNyAtNjIgLTMwIC0xNSA1IC0yOCA0IC0zOCAtNCAtOCAtNyAtMjUgLTExIC0zOCAtMTAKLTI4IDQgLTExOSAtNTMgLTE1NiAtOTcgLTI1IC0yOCAtNDggLTg2IC00OCAtMTE4IDAgLTcgLTggLTE2IC0xNyAtMjIgLTEzIC03Ci0xNyAtMjEgLTE1IC01NyBsMiAtNDggLTM0IDMzIGMtMjEgMjAgLTMyIDM5IC0yOSA1MCAzIDEwIC0xMiAzOCAtMzQgNjcgLTYwCjc1IC01OSA3NCAtMTQgNzIgMjMgLTEgNDQgMSA0NyA1IDQgMyAtOCAyMyAtMjUgNDQgbC0zMSAzNyA0MyAxNiBjNTIgMTkgNTcKNDAgMTYgNjQgLTM1IDIxIC0yNSAzNiAyMiAzNiA0MyAwIDQ2IDMwIDggNjMgLTE4IDE1IC0zNyAyNyAtNDEgMjcgLTQgMCAtOCA1Ci04IDEwIDAgNiA2IDEwIDE0IDEwIDcgMCAzNCAxMSA2MCAyNSAyNiAxNCA2MCAyNSA3NyAyNSAzNSAwIDM5IDIyIDcgNDQgLTM0CjI0IC0xNSAyOSA2MiAxNiAzOSAtNyA3NCAtOSA3NyAtNiAzIDMgLTIgMTcgLTExIDMxIC0yNCAzNyAtNCAzMyAxMDUgLTIwIDExMwotNTUgMTM0IC01NyAxMjYgLTE1IC00IDE3IC0zIDMwIDEgMzAgMTEgMCA1MSAtNDUgOTYgLTEwOCAyNyAtMzcgNDMgLTUxIDU1Ci00NyAxMyA0IDIxIC02IDM0IC00MiA5IC0yNyAxNyAtNTggMTcgLTcwIDAgLTEyIDUgLTI1IDEwIC0yOCA3IC00IDggLTI1IDQKLTUzIC01IC0zOCAtNCAtNDUgNyAtMzUgNyA4IDE0IDQ2IDE3IDk4IDQgNzkgMyA4NSAtMTUgODUgLTE0IDAgLTIzIDExIC0zMQozOSAtNyAyMSAtMjIgNTEgLTM0IDY3IGwtMjMgMjggLTUgLTI5IC01IC0zMCAtMjcgNDYgYy0yNyA0NiAtOTkgMTE5IC0xMTgKMTE5IC02IDAgLTEwIC0xMyAtMTAgLTI5IDAgLTQxIC00MiAtNDggLTg2IC0xNCAtMzQgMjUgLTEyMyA2MyAtMTQ5IDYzIC04IDAKLTE1IC02IC0xNSAtMTN6IG00MDQgLTMyNiBjMTQgLTE1IDE1IC0yMyA1IC00NSAtMTAgLTIyIC05IC0yOSA2IC00NiAyNyAtMjkKNiAtNDkgLTI4IC0yNyAtMjQgMTUgLTI2IDE1IC02NCAtMTggbC00MCAtMzQgLTM0IDI4IC0zNCAyOSAyMCAyMiBjMTYgMTggMTcKMjMgNSAzMCAtOCA2IC0xOCAxMCAtMjIgMTAgLTE1IDAgLTggMjcgMTIgNDUgMTcgMTYgMjQgMTYgNDQgNSAyMCAtMTAgMjggLTEwCjQ3IDQgMzEgMjEgNjIgMjAgODMgLTN6IG0tNDQyIC0zNTIgYzE1IC04IDI3IC04IDQ0IDIgMzEgMTYgMzcgOSAzNiAtNDcgLTEKLTI2IDMgLTUzIDggLTU5IDUgLTYgNSAtMTcgMCAtMjUgLTcgLTExIC0xMyAtMTIgLTM2IC0yIC0yMiAxMSAtMjkgMTAgLTQzIC0yCi0xNSAtMTQgLTE5IC0xNCAtMzMgMCAtMTIgMTMgLTE0IDIzIC03IDQ3IDUgMjAgNSAzNSAtMSA0MSAtMTMgMTMgLTEzIDU2IDEKNTYgNiAwIDIwIC01IDMxIC0xMXoiLz4KPHBhdGggZD0iTTk3MCA1MjMgYzAgLTYgLTIzIC0yNyAtNTAgLTQ3IC00MSAtMjkgLTQ5IC00MCAtNDIgLTU0IDUgLTkgMTIgLTM0CjE1IC01NSA4IC00OSAyMyAtNjcgNTQgLTY3IDIyIDAgMjQgLTMgMTggLTI3IC0xMCAtNDQgLTQ4IC0xMDQgLTc2IC0xMjYgLTE1Ci0xMCAtNDIgLTIwIC02MCAtMjEgLTE4IC0xIC0zNSAtOCAtMzcgLTE1IC00IC0xMSA0IC0xMyAzOSAtOCAyOCA1IDM5IDQgMjkKLTIgLTggLTUgLTUxIC0zMCAtOTUgLTU1IC00NCAtMjUgLTcyIC00NiAtNjIgLTQ2IDIyIDAgMTExIDQ1IDE1MyA3NiAxNyAxMwozNiAyNCA0MiAyNCA2IDAgMTYgOSAyMiAyMCA5IDE3IDE0IDE4IDMzIDkgMzIgLTE3IDc3IC02MSA3NyAtNzYgMCAtNyAtMTAKLTEzIC0yMiAtMTMgLTI5IC0xIC0xMTAgLTM4IC04NSAtMzkgMTAgLTEgMjcgNCAzOCA5IDI3IDE1IDkyIDE2IDk3IDIgMiAtNwoxMCAtMTIgMTggLTEyIDMyIDAgLTI4IDg4IC05MiAxMzMgbC00MyAzMCAxOCAzNiBjMTAgMjAgMjQgNjAgMzEgODkgMTQgNjEgMTAKMTk3IC04IDIyNyAtNiAxMSAtMTIgMTUgLTEyIDh6Ii8+CjxwYXRoIGQ9Ik01NzYgNDkxIGMtNCAtNSAtMiAtMTIgNCAtMTYgNyAtNCA2IC0xMyAtMSAtMjcgLTI1IC00NiAtOSAtMTU4IDI1Ci0xODAgMjAgLTEzIDUzIC04IDc3IDExIDIxIDE3IDI0IDI4IDI1IDkwIGwxIDcxIC00MSAyNCBjLTUzIDMwIC04MyAzOSAtOTAKMjd6Ii8+CjwvZz4KPC9zdmc+Cg==">
            <%--            <img class="mb-2 myIcon" width="36" height="36">--%>
            <small class="d-block mb-3 text-muted">&copy; 2017-
                <%=Calendar.getInstance().get(Calendar.YEAR)%>
            </small>
        </div>
        <div class="col-6 col-md">
            <h5>Features</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Cool stuff</a></li>
                <li><a class="text-muted" href="#">Random feature</a></li>
                <li><a class="text-muted" href="#">Team feature</a></li>
                <li><a class="text-muted" href="#">Stuff for developers</a></li>
                <li><a class="text-muted" href="#">Another one</a></li>
                <li><a class="text-muted" href="#">Last time</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>Resources</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Resource</a></li>
                <li><a class="text-muted" href="#">Resource name</a></li>
                <li><a class="text-muted" href="#">Another resource</a></li>
                <li><a class="text-muted" href="#">Final resource</a></li>
            </ul>
        </div>
        <div class="col-6 col-md">
            <h5>About</h5>
            <ul class="list-unstyled text-small">
                <li><a class="text-muted" href="#">Team</a></li>
                <li><a class="text-muted" href="#">Locations</a></li>
                <li><a class="text-muted" href="#">Privacy</a></li>
                <li><a class="text-muted" href="#">Terms</a></li>
            </ul>
        </div>
    </div>
</footer>

<script src="./static/js/jquery-3.5.0.js"></script>
<%-- bootstrap required jQuery and jQuery most bee included first --%>
<script src="./static/bootstrap4_5/js/bootstrap.min.js"></script>
<%--    <script src="${myPath}/static/js/myJs.js"></script>--%>
