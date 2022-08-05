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
<title>Languages</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
	<div class="container mt-3">
		<h1 style="color:blue">All Languages</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Creator</th>
					<th>version</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lang" items="${myAllLangs}">
				<tr>
					<td><a href="/displaylang/${lang.getId()}"><c:out value="${lang.getLangname()}"/></a></td>
					<td><c:out value="${lang.getCreator()}"/></td>
					<td><c:out value="${lang.getCurrVersion()}"/></td>
					<td><a href="/editlang/${lang.getId()}">Edit</a> , <a href="/delete/${lang.getId()}">Delete</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="container mt-3">
		<h1 style="color:blue; font-family: cursive;" >Add mew Language</h1>
		<form:form action="/languages" method="post" modelAttribute="alllangs">
	
			<div class="mb-3">
				<form:label path="langname" class="form-label">Name:</form:label>
				<form:input path="langname" cssClass="form-control" cssErrorClass="form-control is-invalid"  id="langname"/>
				<form:errors path="langname" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="creator" class="form-label">creator:</form:label>
				<form:input path="creator" class="form-control" cssErrorClass="form-control is-invalid" id="creator"/>
				<form:errors path="creator" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="currVersion" class="form-label">currVersion:</form:label>
				<form:input path="currVersion" class="form-control" cssErrorClass="form-control is-invalid" id="currVersion"/>
				<form:errors path="currVersion" cssClass="invalid-feedback"/>
			</div>
			
	
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
	
</div>

</body>
</html>