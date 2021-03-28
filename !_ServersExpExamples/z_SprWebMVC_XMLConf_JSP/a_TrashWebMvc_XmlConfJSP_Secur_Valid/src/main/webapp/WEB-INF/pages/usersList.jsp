<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Home</title>
	</head>
<body>
	<h1>User management</h1>
	        <a href="./">main</a>

	<table>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>
					<c:out value="${user.username}"/>
				</td>
				<td>
					<a href="<c:url value="/user/delete/${user.id}"/>">Delete user</a>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${empty users}">
			no users added yet.
		</c:if>
	</table>
	
	<a href="<c:url value="/user/add"/>" >Add new user</a>
</body>
</html>
