<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8"/>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css">
  </head>
    <body>

      <h2>Hello World!</h2>
      <div id="pagination">

          <c:url value="/user/list" var="prev">
              <c:param name="page" value="${page-1}"/>
          </c:url>
          <c:if test="${page > 1}">
              <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
          </c:if>

          <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
              <c:choose>
                  <c:when test="${page == i.index}">
                      <span>${i.index}</span>
                  </c:when>
                  <c:otherwise>
                      <c:url value="/user/list" var="url">
                          <c:param name="page" value="${i.index}"/>
                      </c:url>
                      <a href='<c:out value="${url}" />'>${i.index}</a>
                  </c:otherwise>
              </c:choose>
          </c:forEach>
          <c:url value="/user/list" var="next">
              <c:param name="page" value="${page + 1}"/>
          </c:url>
          <c:if test="${page + 1 <= maxPages}">
              <a href='<c:out value="${next}" />' class="pn next">Next</a>
          </c:if>
      </div>
    </body>
</html>