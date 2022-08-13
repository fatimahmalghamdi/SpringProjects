<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert new user</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	.mycontainer{width: 1000px; display: flex; margin: 10px auto; font-family: cursive;}
	.container{margin: 10px 20px;}
</style>
</head>
<body>
<div class="container">
		<h3>Welcome <c:out value="${currentUser.username}"/></h3>
		
		<table class="table">
			<thead>
				<tr>
					<th>name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${allusers}">
				<tr>
					<td><c:out value="${user.username}"/></a></td>
					<td><c:out value="${user.email}"/></td>
					<c:choose>
				    <c:when test="${user.user_level == 'Admin'}">
				        <td>admin</td>
				    </c:when>    
				    <c:otherwise>
				        <td><a href="/deleteuser/${user.getId()}">delete</a>, 
				        	<a href="/editlevel/${user.getId()}">make-admin</a>
				        </td>
				    </c:otherwise>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
<div class="container"><a href="/logoutuser">LogOut</a></div>		
	
</div>


</body>
</html>