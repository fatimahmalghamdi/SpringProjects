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
<title>TV Shows Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
		<c:if test="${sessionScope.user_id != null }">
			<h3>Welcome <c:out value="${userName}"/></h3>
		</c:if>
		
	<a href="/logout">LogOut</a>
	<div class="container mt-3">
		<h4 style="color:blue">Books from everyOnes shelves</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Author name</th>
					<th>Posted by</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${allBooks}">
				<tr>
					<td><a href="/display/${book.getId()}"><c:out value="${book.getTitle()}"/></a></td>
					<td><c:out value="${book.getAuthorName()}"/></td>
       				<td><c:out value="${book.getCreatorUser().getUsername()}"/></td>				
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="container mt-3">
		<form action="/addbook">
			<button type="submit" class="btn btn-primary">Add to my shelve</button>
		</form>
	</div>
	
</div>

</body>
</html>