<%--
Created by IntelliJ IDEA.
User: marty
Date: 12.06.2020
Time: 6:58
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
  <head>
    <page:bootStrapHead/>
    <title>Simple Web App (Jetty)</title>
  </head>

  <body>
    <page:bootstrapHeader/>
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h1 class="display-4">Wellcome to Jetty</h1>
      <p class="lead">Just for example to use.</p>
    </div>

    <div class="container">
      <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
          aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="./two.jsp">two.jsp</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">disabled link</a>
            </li>
          </li>
        </ul>
      </div>
    </nav>

    <%--  //////////////////////////////////////////////////////////////////////////////  --%>
    <div class="card-deck mb-3 text-center">
      <div class="card mb-4 shadow-sm">
        <div class="card-header">
          <h4 class="my-0 font-weight-normal">Type</h4>
        </div>
        <div class="card-body">
          <ul class="list-unstyled mt-3 mb-4">
            <li><a class="text-muted" href="./jsp/one.jsp">one jsp</a></li>
          </ul>
          <li><a class="text-muted" href="./two.jsp">two jsp</a></li>
        </ul>
        <li><a class="text-muted" href="${contextPath}/firstServlet">FirstServlet from contextPath DntWrk ( </a></li>
        <li><a class="text-muted" href="./firstServlet">FirstServlet</a></li>
        <li><a class="text-muted" href="./servletForward">ServletForward</a></li>
      </ul>
    </div>
  </div>

  <%--  class="card-deck mb-3 text-center"  --%>
</div>

<%--   class="container"   --%>
</div>
<page:bootStrapFooter/>
</body>
</html>