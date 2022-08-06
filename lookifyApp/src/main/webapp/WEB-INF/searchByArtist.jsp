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
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container p-5">
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	
	<div class="row">
		<div class="col-4"><h5>Songs by artist: <c:out value="${artist}"/></h5></div>
		<div class="col-6">
			<form action="/index/search" method="post">
				<input name="key">
				<button type="submit" class="btn btn-primary">New Search</button>
			</form>
		</div>
		<div class="col-2"><a href="/index/dashboard">Dashboard</a></div>
	</div>
	<div class="row">
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Rating</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="song" items="${allSongs}">
				<tr>
					<td><a href="/index/display/${song.getId()}"><c:out value="${song.getTitle()}"/></a></td>
					<td><c:out value="${song.getRating()}"/></td>
					<td><a href="/index/delete/${song.getId()}">delete</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
	
</div>

</body>
</html>