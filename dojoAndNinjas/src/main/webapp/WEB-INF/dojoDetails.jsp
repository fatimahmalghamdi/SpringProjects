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
<div class="container mt-3">
		<h1 style="font-weight: bold; color:blue">Dojo Details</h1>
        <table class="table">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ninja" items="${my_dojo.ninjas}">
				<tr>
					<td><c:out value="${ninja.getFirst_name()}"/></td>
					<td><c:out value="${ninja.getLast_name()}"/></td>
					<td><c:out value="${ninja.getAge()}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/welcome/choose">Go Back</a>
</div>

</body>
</html>