<%@ page import="java.util.ArrayList, java.util.List, com.model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <page:bootStrapHead/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Customer Manager</title>
    </head>

    <body>
      <page:bootstrapHeader/>
      <main class="container" role="main">

        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample10"
            aria-controls="navbarsExample10" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample10">
            <ul class="navbar-nav">

              <li class="nav-item">
                <a class="nav-link" href="${contextPath}">Home Page</a>
              </li>
              <li class="nav-item">
                <a class="nav-link disabled" tabindex="-1" aria-disabled="true" href="${contextPath}/studentBootStrapRepository"> students Bootstrap Using Repository Table </a>
              </li>

            </ul>
          </div>
        </nav>
        <%--  //////////////////////////////////////////////////////////////////////////////  --%>

        <div align="center">
          <h2>Веб Интерфейс</h2></div>

          <%-- Search Bar --%>
          <nav class="navbar navbar-light bg-light">
            <a class="padding5px nav-link disable" href="${contextPath}/studentBootStrapRepository"
              data-toggle="tooltip" data-placement="top" title="Сброс Таблицы">
              <img src="${contextPath}/resources/open-iconic/png/reload-3x.png"></a>

              <a class="padding5px nav-link disable bg-danger" href="${contextPath}/studentBootStrapRepository/deleteAll"
                data-toggle="tooltip" data-placement="top" title="Удалить всё">
                <img src="${contextPath}/resources/octicons/x-24.svg"></a>

                <a class="padding5px btn btn-secondary" href="${contextPath}/studentBootStrapRepository/importTestData" role="button"
                  data-toggle="tooltip" data-placement="top" title="Импортировать тестовые данные (Import Test Data)">
                  <span class="fa fa-search form-control-feedback"><img src="${contextPath}/resources/octicons/database-24.svg"></span>
                  Import</a>

                  <%-- <nav class="navbar navbar-dark bg-primary"> --%>
                  <%-- <nav class="navbar  navbar-light" style="background-color: #e3f2fd;"> --%>
                  <form class="form-inline my-2 my-lg-0" method="get" action="${contextPath}/studentBootStrapRepository/search">
                    <div class="form-inline">
                      <span class="navbar-text">использовать регулярные выраж.</span>
                      <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="regex">

                        <div class="form-group has-search">
                          <span class="fa fa-search form-control-feedback">
                            <img src="${contextPath}/resources/octicons/search-24.svg">
                            </span>
                            <input class="form-control mr-sm-2" type="text" placeholder="Search" name="keyword" aria-label="Search">
                            </div>

                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Искать во всех полях</button>
                          </div>
                        </form>
                      </nav>

                      <%-- Table Start --%>
                      <table border="1" cellpadding="5" class="table table-hover table-sm">
                        <thead class="thead-dark">
                          <tr>

                            <th scope="col">ID</th>
                            <th scope="col">Имя
                              <a href="${contextPath}/studentBootStrapRepository/orderByName" method="get"><img src="${contextPath}/resources/open-iconic/png/sort-ascending_invert-2x.png"
                                data-toggle="tooltip" data-placement="top" title="Сортировать по имени"></a></th>
                                <%-- for light theme --%>
                                <%-- <img src="${contextPath}/resources/octicons/grabber-24.svg"></a> --%>
                              </a>
                              <th scope="col">Возраст
                                <a href="${contextPath}/studentBootStrapRepository/orderByAge" method="get"><img src="${contextPath}/resources/open-iconic/png/sort-ascending_invert-2x.png"
                                  data-toggle="tooltip" data-placement="top" title="Сортировать по возрасту"></a></th>

                                  <th scope="col">Настройки</th>
                                </tr>
                              </thead>

                              <tbody>
                                <%-- Filter Form --%>
                                <tr>
                                  <form class="form-inline" action="${contextPath}/studentBootStrapRepository/filter" method="post">
                                    <th scope="row"><input  class="form-control" id="isId" type="text" name="id" placeholder="id"></th>
                                    <td><input  class="form-control" id="nameId" type="text" name="name" placeholder="Имя"></td>
                                    <td><input  class="form-control" id="nameId" type="text" name="name" placeholder="Возраст"></td>
                                    <td>
                                      <div class="form-inline">
                                        <div>regex</div>
                                        <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="check">
                                          <button type="submit" value="Добавить" class="btn btn-sm btn-outline-secondary">Фильтровать</button>
                                        </div>
                                      </td>
                                    </form>
                                  </tr>

                                  <%-- Load Data --%>
                                  <c:forEach items="${list}" var="obj">
                                    <tr>
                                      <th scope="row">${obj.id}</th>
                                      <td>${obj.name}</td>
                                      <td>${obj.age}</td>
                                      <td>
                                        <a class="btn btn-primary btn-sm" href="${contextPath}/studentBootStrapRepository/edit?id=${obj.id}" role="button">Редактировать</a>
                                        <a class="btn btn-danger btn-sm" href="${contextPath}/studentBootStrapRepository/delete?id=${obj.id}" role="button">Удалить</a>
                                      </td>
                                    </tr>
                                  </c:forEach>

                                  <%-- Add Form --%>
                                  <tr>
                                    <form class="form-inline" action="${contextPath}/studentBootStrapRepository/save" method="post">
                                      <input class="form-control col-md-3 mb-3" type="hidden" name="_method" value="add">

                                        <%-- <th scope="row">#</th> --%>
                                        <th scope="row">
                                          <a role="button" class="btn bg-warning" href="${contextPath}/studentBootStrapRepository/importTestData"
                                            data-toggle="tooltip" data-placement="top" title="Удалить Выбранное в checkbox">
                                            <span class="fa fa-search form-control-feedback"><img src="${contextPath}/resources/octicons/trash-24.svg"></span>
                                            Удалить</a>
                                          </th>

                                          <td>                  <div class="form-group mx-sm-3 mb-2">
                                            <label for="nameId" class="sr-only">Имя</label>
                                            <input  class="form-control" id="nameId" required type="text" name="name" placeholder="Имя">
                                            </div></td>
                                            <td>                    <div class="form-group mx-sm-3 mb-2">
                                              <label for="ageId" class="sr-only">Password</label>
                                              <input class="form-control" id="ageId" required type="text" name="age" placeholder="Возраст">
                                              </div></td>

                                              <td>
                                                <button type="submit" value="Добавить" class="btn btn-sm btn-outline-secondary">Добавить</button>
                                              </td>
                                            </form>
                                          </tr>

                                        </tbody>
                                      </table>
                                      <%-- Table End --%>

                                      <tag:paginate max="10" offset="${offset}" count="${count}"
                                        uri="/person/list" next="&raquo;" previous="&laquo;" />

                                      </hr>

                                      <%-- Form Add --%>
                                      <form class="form-inline" action="${contextPath}/studentBootStrapRepository/save" method="post">
                                        <input class="form-control col-md-3 mb-3" type="hidden" name="_method" value="add">

                                          <div class="form-group mx-sm-3 mb-2">
                                            <label for="nameId" class="sr-only">Имя</label>
                                            <input  class="form-control" id="nameId" required type="text" name="name" placeholder="Имя">
                                            </div>

                                            <div class="form-group mx-sm-3 mb-2">
                                              <label for="ageId" class="sr-only">Password</label>
                                              <input class="form-control" id="ageId" required type="text" name="age" placeholder="Возраст">
                                              </div>

                                              <button type="submit" value="Добавить" class="btn btn-primary btn-sm">Добавить</button>
                                            </form>

                                            <%-- Examples for float by left --%>
                                            <div class="bs-bars float-left"><div id="toolbar">
                                              <a role="button" class="btn bg-info" href="#"
                                                data-toggle="tooltip" data-placement="top" title="Examples for float by left">
                                                <span class="fa fa-search form-control-feedback"><img src="${contextPath}/resources/octicons/checklist-24.svg"></span>
                                                example button</a>
                                              </div> </div>

                                              <%--  --%>
                                              <a class="navbar-brand nav-link disable bg-danger" href="${contextPath}/studentBootStrapRepository/deleteAll"
                                                data-toggle="tooltip" data-placement="top" title="Удалить всё">
                                                <img src="${contextPath}/resources/octicons/x-24.svg"></a>

                                                <%--   class="container"   --%>
                                              </main>
                                              <page:bootStrapFooter/>
                                            </body>
                                          </html>
