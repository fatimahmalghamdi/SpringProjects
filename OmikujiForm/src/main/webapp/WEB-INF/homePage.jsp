<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<h2>Send an Omikuji</h2>

	<form action="/home/sendOmikuji" method="post">
		<div class="mb-3">
			<label for="numbers" class="form-label">pick any number from
				5 to 25</label>
			<select id="numbers" class="form-control" name="numbers">
				<% for (int i = 1; i < 10; ++i) { %>
				<option><%= i %></option>
				<% } %>
			</select>
		</div>

		<div class="mb-3">
			<label for="city" class="form-label">Enter the name of any city:</label>
			<input type="text" class="form-control" id="city" name="city">
		</div>

		<div class="mb-3">
			<label for="name" class="form-label">Enter the name of any person:</label>
			<input type="text" class="form-control" id="name"
				name="name">
		</div>

		<div class="mb-3">
			<label for="hobby" class="form-label">Enter the hobby:</label>
			<input type="text" class="form-control" id="hobby" name="hobby">
		</div>

		<div class="mb-3">
			<label for="message" class="form-label">Say something nice to
				someone:</label>
			<textarea name="message" id="message" rows="10" cols="30"  				class="form-control"> You do not realize how happy you make others.
			</textarea>
		</div>

		<button type="submit" class="btn btn-primary">Send</button>
	</form>

</body>
</html>