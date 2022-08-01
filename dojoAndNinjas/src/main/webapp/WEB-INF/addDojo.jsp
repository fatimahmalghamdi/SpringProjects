<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
		<h2>Add New Dojo</h2>
	
		<form:form action="/welcome/createdojo" method="post" modelAttribute="mydojo">
	
			<div class="mb-3">
				<label for="name" class="form-label">Dojo Name:</label>
				<form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" id="name"/>
				<form:errors path="name" cssClass="invalid-feedback"/>
			</div>
			<button type="submit" class="btn btn-primary">Add Dojo</button>
		</form:form>
	</div>

</body>
</html>