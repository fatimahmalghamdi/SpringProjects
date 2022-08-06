<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Top 10 songs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container mt-3">
		<table class="table">
			<thead>
				<tr>
					<th>Artist</th>
					<th>song title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="song" items="${thetopsongs}">
				<tr>
					<td><c:out value="${song.getArtist()}"/></td>
					<td><a href="/display/${song.id}"><c:out value="${song.getTitle()}"/></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container mt-3">
		<a href="/dashboard">Dashboard</a>
	</div>
</body>
</html>