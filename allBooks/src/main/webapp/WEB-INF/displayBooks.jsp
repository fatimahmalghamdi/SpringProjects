<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="container mt-3">
			<h1>All Books</h1>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Language</th>
						<th># Pages</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.getId()}"></c:out></td>
						<td><a href="/books/displayById/${book.getId()}"><c:out value="${book.getTitle()}"></c:out></a></td>
						<td><c:out value="${book.getLanguage()}"></c:out></td>
						<td><c:out value="${book.getNumberOfPages()}"></c:out></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>