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
	<h2 style="color:blue;">Book Club</h2>

<div class="mycontainer">

	<div class="container mt-3 border">
		<h2>Register New user</h2>
	
		<form:form action="/newuser" method="post" modelAttribute="new_user">
	
			<div class="mb-3">
				<form:label path="username" class="form-label">UserName:</form:label>
				<form:input path="username" cssClass="form-control" cssErrorClass="form-control is-invalid"  id="username"/>
				<form:errors path="username" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="email" class="form-label">Email:</form:label>
				<form:input path="email" class="form-control" cssErrorClass="form-control is-invalid" id="useremail"/>
				<form:errors path="email" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="password" class="form-label">Password:</form:label>
				<form:input path="password" type="password" class="form-control" cssErrorClass="form-control is-invalid" id="userpassword"/>
				<form:errors path="password" cssClass="invalid-feedback"/>
			</div>
			
			<div class="mb-3">
				<form:label path="confirmPassword" class="form-label">ConfirmPassword:</form:label>
				<form:input path="confirmPassword" type="password" class="form-control" cssErrorClass="form-control is-invalid" id="confpass"/>
				<form:errors path="confirmPassword" cssClass="invalid-feedback"/>
			</div>
	
			<button type="submit" class="btn btn-primary">Register User</button>
		</form:form>
	</div>
	
	
	<div class="container mt-3 border">
		<h2>LogIn user</h2>
		<form:form action="/login" method="post" modelAttribute="userLoginRequests">
	
			<div class="mb-3">
				<form:label path="email" class="form-label">Email:</form:label>
				<form:input path="email" class="form-control" cssErrorClass="form-control is-invalid" id="useremail"/>
				<form:errors path="email" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="password" class="form-label">Password:</form:label>
				<form:input path="password" type="password" class="form-control" cssErrorClass="form-control is-invalid" id="userpassword"/>
				<form:errors path="password" cssClass="invalid-feedback"/>
			</div>
	
			<button type="submit" class="btn btn-primary">LogIn User</button>
		</form:form>
	</div>
	
		
</div>

</div>
</body>
</html>