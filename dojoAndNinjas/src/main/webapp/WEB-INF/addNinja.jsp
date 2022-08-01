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
	
		<h2>Add New Ninja</h2>
	
		<form:form action="/welcome/createninja" method="post" modelAttribute="myninja">
	
			<div class="mb-3">
				<form:select path="dojo">
					<option>select dojo</option>
					<c:forEach var="dojo" items="${dojos}">
						<form:option value="${dojo.getId()}"><c:out value="${dojo.getName()}"/></form:option>
					</c:forEach>
				</form:select>
			</div>
			
			<div class="mb-3">
				<label for="fname" class="form-label">First Name:</label>
				<form:input path="first_name" cssClass="form-control" cssErrorClass="form-control is-invalid" id="fname"/>
				<form:errors path="first_name" cssClass="invalid-feedback"/>
			</div>
			<div class="mb-3">
				<label for="lname" class="form-label">Last Name:</label>
				<form:input path="last_name" cssClass="form-control" cssErrorClass="form-control is-invalid" id="lname"/>
				<form:errors path="last_name" cssClass="invalid-feedback"/>
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">Age:</label>
				<form:input path="age" cssClass="form-control" cssErrorClass="form-control is-invalid" id="age"/>
				<form:errors path="age" cssClass="invalid-feedback"/>
			</div>
			<button type="submit" class="btn btn-primary">Add Ninja</button>
		</form:form>
	</div>

</body>
</html>