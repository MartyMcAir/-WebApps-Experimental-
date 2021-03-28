<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h1>User management</h1>
		<a href="./">main</a>
	</br>
	<a href="./employee/form">add employee in form</a>
</br>
</br>

<h2> List of content </h2>

<table>
	<c:forEach var="emp" items="${employee}">
		<tr>
			<td>
				<c:out value="${emp.name}"/>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
