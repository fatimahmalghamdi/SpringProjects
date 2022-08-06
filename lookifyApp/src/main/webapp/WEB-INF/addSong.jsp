<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Song</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
		<h2>Add New Song</h2>
	
		<form:form action="/addSong" method="post" modelAttribute="mysong">
	
			<div class="mb-3">
				<form:label path="title" class="form-label">Title:</form:label>
				<form:input path="title" cssClass="form-control" cssErrorClass="form-control is-invalid" id="name"/>
				<form:errors path="title" cssClass="invalid-feedback"/>
			</div>
			
			<div class="mb-3">
				<form:label path="artist" class="form-label">Artist:</form:label>
				<form:input path="artist" cssClass="form-control" cssErrorClass="form-control is-invalid" id="name"/>
				<form:errors path="artist" cssClass="invalid-feedback"/>
			</div>
			
			<div class="mb-3">
				<form:label path="rating" class="form-label">Rating:</form:label>
				<form:select path="rating" cssClass="form-control" cssErrorClass="form-control is-invalid" id="name">
					<c:forEach var = "i" begin = "1" end = "10">
         				<form:option value="${i}"><c:out value="${i}"/></form:option>
      				</c:forEach>
				</form:select>
				<form:errors path="rating" cssClass="invalid-feedback"/>
			</div>
			<button type="submit" class="btn btn-primary">Add Song</button>
		</form:form>
	</div>



</body>
</html>