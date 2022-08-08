<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
		<h2>Edit Book:</h2>
	
		<form:form action="/edit/${BookId}" method="put" modelAttribute="current_book">
	
			<div class="mb-3">
				<form:label path="title" class="form-label">Title:</form:label>
				<form:input path="title" cssClass="form-control" cssErrorClass="form-control is-invalid"  id="title"/>
				<form:errors path="title" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="authorName" class="form-label">Author Name:</form:label>
				<form:input path="authorName" class="form-control" cssErrorClass="form-control is-invalid" id="network"/>
				<form:errors path="authorName" cssClass="invalid-feedback"/>
			</div>
	
	
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
	
	
	<div class="container mt-3">
		<form action="/dashboard">
			<button type="submit" class="btn btn-primary">Cancel</button>
		</form>
	</div>
	
	

</body>
</html>