<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<a href="/dashboard">Back to Dashboard</a>
		<h2 style="font-weight: bold; ont-family:cursive"><c:out value="${theBook.getTitle()}"/></h2>
        <h4>The author: <c:out value="${theBook.getAuthorName()}"/></h4>
        <h4>Posted By: <c:out value="${theBook.getCreatorUser().getUsername()}"/></h4>
	</div>
	
	<c:if test="${theBook.getCreatorUser().getId() == user_id}">
	
		<div class="container">
			<form action="/edit/${theBook.getId()}">
				<button type="submit" class="btn btn-primary">Edit</button>
			</form>
		</div>
	
	</c:if>
	
	
	<div class="container mt-3">
		<h4 style="color:blue; ont-family:cursive">All thoughts:</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Users Thoughts</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="thought" items="${allthoughts}">
				<tr>
					<td><c:out value="${thought.getThethought()}"/></td>			
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<div class="container mt-3">
			<h4 style="font-family:cursive; color:blue">Add your Thought </h4>
			<form:form action="/addthought/${theBook.getId()}" method="post" modelAttribute="theThought">
				<div class="mb-3">
					<form:label path="thethought" class="form-label">Your Thought:</form:label>
					<form:input path="thethought" cssClass="form-control" cssErrorClass="form-control is-invalid"  id="theRate"/>
					<form:errors path="thethought" cssClass="invalid-feedback"/>
				</div>
				<button type="submit" class="btn btn-primary">Post!</button>
			</form:form>
		</div>
	
	
	

</body>
</html>