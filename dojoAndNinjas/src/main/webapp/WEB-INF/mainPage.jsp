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
<style>
	.myc{ display: flex;}
</style>
</head>
<body>
<div class="container">
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
		<h2>Choose:</h2>
		
			<form action="/welcome/displayDojo" method="post">
			<div class="container myc mb-3">
				<select name="dojo" class="form-control">
					<c:forEach var="dojo" items="${allDojos}">
						<option value="${dojo.getId()}"><c:out value="${dojo.getName()}"/></option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary">Display Dojo</button>
				</div>
			</form>
		
		
		<form action="/welcome/createDojoPage">
			<div class="mb-3">
				<button type="submit" class="btn btn-primary">Add Dojo</button>
			</div>
		</form>
		
		<form action="/welcome/createNinjaPage">
			<div class="mb-3">
				<button type="submit" class="btn btn-primary">Add Ninja</button>
			</div>
		</form>
</div>
</body>
</html>