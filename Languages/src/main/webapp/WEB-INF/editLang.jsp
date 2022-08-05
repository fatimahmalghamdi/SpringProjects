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
<title>Update Languages</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
	<div class="container mt-3">
		<h1 style="color:blue; font-family: cursive;" >Update the Language</h1>
		
		<form:form action="/editlang/${langId}" method="put" modelAttribute="mylanguages">
	
			<div class="mb-3">
				<form:label path="langname" class="form-label">Name:</form:label>
				<form:input path="langname" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="langname" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="creator" class="form-label">Creator:</form:label>
				<form:input path="creator" class="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="creator" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="currVersion" class="form-label">Version:</form:label>
				<form:input path="currVersion" class="form-control" cssErrorClass="form-control is-invalid"/>
				<form:errors path="currVersion" cssClass="invalid-feedback"/>
			</div>
			
	
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
		
		<a href="/languages">Dashboard</a>
	</div>
</div>
</body>
</html>