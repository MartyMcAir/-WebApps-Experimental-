<%@ page import="java.util.ArrayList, java.util.List, com.model.Student" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Manager</title>
</head>
<body>
<div align="center">
	<h2>Customer Manager</h2>

	<a href="./">main</a>

	<form method="get" action="studentRepository/search">
		<input type="text" name="keyword" />
		<input type="submit" value="Search" />
	</form>

	<h3><a href="studentRepository/new">New Customer</a></h3>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Setting</ht>
		</tr>
		<c:forEach items="${list}" var="obj">
		<tr>
			<td>${obj.id}</td>
			<td>${obj.name}</td>
			<td>${obj.age}</td>
			<td>
				<a href="studentRepository/edit?id=${obj.id}">Edit</a>
				<a href="studentRepository/delete?id=${obj.id}">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</table>

	<h3>Tbl Version 2 (loop in JSP ArrayList.. request.get..)</h3>
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>tbl name</h2>
            </div>
            <% List<Student> names = (List<Student>) request.getAttribute("list");
                if (names != null && !names.isEmpty()) {
                    out.println("<ul class=\"w3-ul\">");
                    for (Student s : names)
                        out.println("<li class=\"w3-hover-sand\">" + s.getName() + "</li>");
                    out.println("</ul>");
                } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                        + "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                        "   <h5>There are no users yet!</h5>\n" +
                        "</div>"); %>
        </div>
    </div>


</div>
</body>
</html>