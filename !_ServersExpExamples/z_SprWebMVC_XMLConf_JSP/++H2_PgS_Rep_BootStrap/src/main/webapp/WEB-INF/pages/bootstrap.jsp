<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

  <head>
    <page:bootStrapHead/>
    <title>Simple Web App (with Bootstrap)</title>
  </head>

  <body>
    <page:bootstrapHeader/>

				<script src="resources/js/myJs.js"></script>
				<!-- tha too -->
				<!-- <script th:src="@{/resources/js/myJs.js}"></script> -->

				<button onclick="myPrint()">myPrint</button>
				<button onclick="myFunction()">Click me</button>
				<p id="demo"></p>

    <main class="container" role="main">

      <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
          aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
          <ul class="navbar-nav">

            <li class="nav-item">
              <a class="nav-link disabled" tabindex="-1" aria-disabled="true" href="#">Home Page</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${contextPath}/studentBootStrapRepository"> students Bootstrap Using Repository Table </a>
            </li>

          </ul>
        </div>
      </nav>

      <%--  //////////////////////////////////////////////////////////////////////////////  --%>
      <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Mapping Examples and oth</h4>
          </div>
          <div class="card-body">
            <ul class="list-unstyled mt-3 mb-4">

              <li><a class="text-muted" href="./">main</a></li>
              <li><a class="text-muted" href="${contextPath}/lbClassicStatus"> lbClassicStatus </a></li>
              <li><a class="text-muted" href="${contextPath}/studentRepository"> students Using Repository Table </a></li>

              <strong>others..</strong>
              <li> <a class="text-muted" href="${contextPath}/studentBootStrapRepository"> students Bootstrap Using Repository Table </a></li>

            </ul>
          </div>
        </div>
        <%--  class="card-deck mb-3 text-center"  --%>
      </div>

      <%--   class="container"   --%>
    </main>

    <page:bootStrapFooter/>

    <p>кириллицаотображаетсякорректно</p>
  </body>
</html>
