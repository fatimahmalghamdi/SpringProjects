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
		<h1 style="font-weight: bold"><c:out value="${theBook.getTitle()}"/></h1>
        <h4>Description: <c:out value="${theBook.getDescription()}"/></h4>
        <h4>Language: <c:out value="${theBook.getLanguage()}"/></h4>
        <h4>Number of Pages: <c:out value="${theBook.getNumberOfPages()}"/></h4>
	</div>

</body>
</html>