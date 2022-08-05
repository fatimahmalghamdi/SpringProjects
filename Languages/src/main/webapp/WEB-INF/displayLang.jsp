<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>languages Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-3">
		<h1 style="font-weight: bold; color:blue">Languages Details</h1>
        <h4>Name: <c:out value="${mylang.langname}"/></h4>
        <h4>Creator: <c:out value="${mylang.creator}"/></h4>
        <h4>Version: <c:out value="${mylang.currVersion}"/></h4>
		<a href="/languages">Dashboard</a>
		<br><br>
		
		<div class="container mt-3">
			<form action="/editlang/${mylang.id}">
				<button type="submit" class="btn btn-primary">Edit</button>
			</form>
		</div>
		
		<div class="container mt-3">
			<form action="/delete/${mylang.id}">
				<button type="submit" class="btn btn-primary">Delete</button>
			</form>
		</div>
		
	</div>

</body>
</html>