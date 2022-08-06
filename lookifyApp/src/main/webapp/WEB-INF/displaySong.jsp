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
	<div class="row">
		<h1 style="font-weight: bold; color:blue">Song Details</h1>
        <h4>Title: <c:out value="${theSong.getTitle()}"/></h4>
        <h4>Artist: <c:out value="${theSong.getArtist()}"/></h4>
        <h4>Rating (1-10): <c:out value="${theSong.getRating()}"/></h4>
		<a href="/dashboard">DashBoard</a>
		<a href="/delete/${theSong.getId()}">Delete</a>
	</div>
</div>

</body>
</html>