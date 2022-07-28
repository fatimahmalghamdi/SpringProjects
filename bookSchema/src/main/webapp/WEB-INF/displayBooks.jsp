<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of All Books</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
		<h2><c:out value="${myBook.getTitle()}"></c:out></h2>
        <h3>Description: <c:out value="${myBook.getDescription()}"></c:out></h3>
        <h3>Language: <c:out value="${myBook.getLanguage()}"></c:out></h3>
        <h3>Number of Pages: <c:out value="${myBook.getNumberOfPages()}"></c:out></h3>
</div>
</body>
</html>