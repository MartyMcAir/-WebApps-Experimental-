<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<style>
.error{color:red}
</style>
</head>
<body>
        <a href="./">main</a>

<form:form action="save" modelAttribute="emp">


Username: <form:input path="name"/> <br><br>
<form:errors path="name" cssClass="error"/><br><br>

Password(*): <form:password path="pass"/>
<form:errors path="pass" cssClass="error"/><br><br>

Age(*): <form:input type="number" path="age"/>
<form:errors path="age" cssClass="error"/><br><br>

Phone(* 1234567890): <form:input path="phone"/>
<form:errors path="phone" cssClass="error"/><br><br>

BirthDay(* 2014/12/12): <form:input placeholder="MM/dd/yyyy" path="birthday"/>
<form:errors path="birthday" cssClass="error"/><br><br>

<input type="submit" value="submit">
</form:form>

</body>
</html>