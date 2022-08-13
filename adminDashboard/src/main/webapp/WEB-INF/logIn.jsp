<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>LogIn:</h2>
	<c:choose>
		<c:when test="${sessionScope.user_id != null }">
			<h3>User logged In</h3>
		</c:when>
		<c:otherwise>
			<h3>User not logged In</h3>
		</c:otherwise>
	</c:choose>
	
	
			
</div>


</body>
</html>