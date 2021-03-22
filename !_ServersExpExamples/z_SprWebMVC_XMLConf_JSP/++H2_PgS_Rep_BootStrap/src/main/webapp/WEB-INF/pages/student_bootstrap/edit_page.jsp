<%@ page import="java.util.ArrayList, java.util.List, com.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"	"http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <page:bootStrapHead/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Редактирование</title>
    </head>

    <body>
      <page:bootstrapHeader/>

      <main class="container col-md-4 blog-main" role="main">

        <form:form action="save" method="post" modelAttribute="obj">
          <div class="form-group">
            <div align="center"> <h2>Редактирование</h2>
            <label for="idIs" class="font-weight-bold">ID: ${obj.id}</label>
          </div>
          <form:hidden path="id" class="form-control" id="idIs" />
        </div>

        <div class="form-group row">
          <label for="nameId" class="col-sm-2 col-form-label">Имя</label>
          <div class="col-sm-10">
            <form:input path="name" type="text" class="form-control" id="nameId" />
          </div>
        </div>

        <div class="form-group row">
          <label for="ageId" class="col-sm-2 col-form-label">Возраст</label>
          <div class="col-sm-10">
            <form:input path="age" class="form-control" type="text" id="ageId" />
          </div>
        </div>

        <%-- <div class="form-group">
        <label for="ageId">Возраст</label>
        <form:input path="age" class="form-control" type="text" id="ageId" />
      </div> --%>

      <button type="submit" class="btn btn-primary">Сохранить</button>
    </form:form>

    <%--   class="container"   --%>
  </main>
</body>
<page:bootStrapFooter/>
</html>
