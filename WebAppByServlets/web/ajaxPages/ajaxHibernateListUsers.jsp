<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags/page" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <page:bootStrapHead/>
    <link rel="stylesheet" href="${contextPath}/public_resources/css/hibernate.css">
    <title>Список пользователей</title>
</head>
<body>
<page:bootstrapHeader/>

<div class="container">
    <page:navBarMainsPages/>

    <br/>
    <a class="nav-link" href="${pageContext.request.contextPath}/ajaxPages/ajaxHibernateListUsers.jsp"
       tabindex="-1">ajaxHibernateListUsers.jsp</a>

    <table class="table" id="expTable">
        <thead>
        <tr>
            <td scope="col">ID</td>
            <td scope="col">Имя</td>
            <td scope="col">Возраст</td>
            <td scope="col">Действия</td>
        </tr>
        </thead>

        <tbody>
        <%--        <jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
        <c:forEach items="${users}" var="user">
            <tr id="id${user.getId()}">
                <th scope="row">${user.getId()}</th>
                <td>${user.getName()}</td>
                <td>${user.getAge()}</td>
                <td>
                        <%--                <form action="updateUser.jsp" method="post">--%>
                    <div class="inlineDivs">
                            <%--                        <form action="${contextPath}/HibernateAjaxCrudUser" class="formClass" method="get">--%>
                            <%--                        <form action="${contextPath}/HibernatePages/hibernateUpdateUser.jsp" method="get"--%>
                            <%-- UPDATE FORM >>> ................................................................................... --%>
                        <form id="idShowUpdForm${user.getId()}" class="showUpdForm formClass">
                            <input name="id" value="${user.getId()}" type="hidden">
                            <button type="submit" class="btn btn-primary btn-sm openModal">Изменить</button>
                        </form>

                        <div id="idUpdForm${user.getId()}">
                            <!-- без classMainDiv  не будет видно самого окна -->
                            <div class="classMainDiv" aria-hidden="true">
                                <!-- без classInnerInMain  не будет сокрытия окна -->
                                <div class="classInnerInMain">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel${user.getId()}">Edit User
                                                        ${user.getId()}</h5>
                                                <button type="button" class="close closeModal"
                                                        data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                                <form action="${contextPath}/HibernateAjaxCrudUser"
                                                      id="idSubmitUpdForm${user.getId()}" method="get">
                                                    <input type="hidden" name="_method" value="update">
                                                    <input type="hidden" name="id" value="${user.getId()}">

                                                    <div class="form-group">
                                                        <label for="nameLabel${user.getId()}">name</label>
                                                        <input name="name" class="form-control"
                                                               id="nameLabel${user.getId()}"
                                                               placeholder="${user.getName()}"
                                                               value="${user.getName()}">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="ageLabel${user.getId()}">age</label>
                                                        <input name="age" class="form-control"
                                                               id="ageLabel${user.getId()}"
                                                               placeholder="${user.getId()}"
                                                               value="${user.getAge()}">
                                                    </div>

                                                    <button type="submit" class="btn btn-primary">ok</button>
                                                    <button type="button" class="btn btn-secondary closeModal"
                                                            data-dismiss="modal">Close
                                                    </button>
                                                </form>
                                                    <%-- modal-body --%>
                                            </div>
                                        </div>
                                    </div>
                                        <%-- modal-dialog --%>
                                </div>
                            </div>
                                <%-- classMainDiv --%>
                        </div>
                            <%-- <<< UPDATE FORM ................................................................................... --%>
                            <%--                <form action="deleteUser.jsp" method="post">--%>
                            <%--                        <form action="${contextPath}/HibernateAjaxCrudUser" method="get" class="formClass">--%>
                        <form action="${contextPath}/HibernateAjaxCrudUser" class="formClass delFormClass" method="get">
                            <input type="hidden" name="_method" value="delete">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <button type="submit" class="btn btn-primary btn-sm">Удалить</button>
                        </form>
                    </div>
                        <%-- inlineDivs --%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--    <form action="${contextPath}/HibernateListUsers" method="get" class="formClass">--%>
    <form action="${contextPath}/HibernateAjaxCrudUser" method="get" class="formClass" id="addForm">
        <input class="form-control col-md-3 marginRight" type="hidden" name="_method" value="add">
        <input class="form-control col-md-3 marginRight" required type="text" name="name" placeholder="Имя">
        <input class="form-control col-md-3 marginRight" required type="text" name="age" placeholder="Возраст">
        <button type="submit" value="Добавить" class="btn btn-primary btn-sm">Добавить</button>
    </form>

    <strong>resultId</strong>
    <strong id="resultId">Here Info For Current Status 2.5sec &hide</strong>

    <%-- class="container" --%>
</div>
<page:bootStrapFooter/>
<script src="${contextPath}/public_resources/js/ajaxForHibernate/ajaxHibernateMainGet.js"></script>
<script src="${contextPath}/public_resources/js/ajaxForHibernate/ajaxHibernateDelete.js"></script>
<script src="${contextPath}/public_resources/js/ajaxForHibernate/ajaxHibernateAdd.js"></script>
<script src="${contextPath}/public_resources/js/ajaxForHibernate/ajaxHibernateUpdate.js"></script>
</body>
</html>