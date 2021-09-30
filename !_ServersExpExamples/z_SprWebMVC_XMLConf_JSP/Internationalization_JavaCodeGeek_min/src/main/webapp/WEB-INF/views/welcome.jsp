<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		    <meta charset="utf-8"/>
		<title>AdmissionPage</title>
	</head>

	<body>		
		<h2>${welcome_msg}</h2>
		<hr />		
		<a id="en" href="/simple_mvc/?lang=en">English</a>
		|
		<a id="fr" href="/simple_mvc/?lang=fr">French</a>
		|
	    <a id="fr" href="/simple_mvc/?lang=ru">Russia</a>
		<div>&nbsp;</div>
		
		<div id="welcome_text">			
			<h4><spring:message code="welcome.message" /></h4>
		</div>

		<p>кириллицаотображаетсякорректно</p>
	</body>

</html>