<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="mycontainer">
		<h2>Here is your Omikuji</h2>
		<p>In <c:out value="${theNum}"></c:out> years you will live in <c:out value="${theCity}"></c:out>
			with <c:out value="${myName}"></c:out> as your best roommate, <c:out value="${theHobby}" />
			for a living. Also <c:out value="${theMessage}"></c:out> </p>
	</div>
	
</body>
</html>