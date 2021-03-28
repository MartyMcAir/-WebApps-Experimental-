<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign Up</title>
<style type="text/css">
span.error {
	color: red;
}
</style>
</head>
<body>
	<h1>Sign Up</h1>

	<form:form action="signup/sign_action" method="post" modelAttribute="signupForm">
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="username" /></td>
				<td><span class="error"><form:errors path="username" /></span></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" /></td>
				<td><span class="error"><form:errors path="password" /></span></td>
			</tr>

			<tr>
				<td>Confirm Password:</td>
				<td><form:password path="confirmPassword" /></td>
				<td><span class="error"><form:errors
							path="confirmPassword" /></span></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
				<td><span class="error"><form:errors path="email" /></span></td>
			</tr>

			<tr>
				<td colspan="3"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<a href="${pageContext.request.contextPath}/" title="Home">Home</a>
</body>
</html>