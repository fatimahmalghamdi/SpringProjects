<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Expense Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>

	a{
	position: absolute;
  	top: 40px;
  	left: 550px;}
</style>
</head>
<body>
<div class="container mt-3">
		<h1 style="font-weight: bold; color:blue">Expense Details</h1>
        <h4>Expense Name: <c:out value="${theExpense.getExpense_name()}"/></h4>
        <h4>Description: <c:out value="${theExpense.getDescription()}"/></h4>
        <h4>Vendor: <c:out value="${theExpense.getVendor()}"/></h4>
        <h4>Amount Spent: <c:out value="${theExpense.getAmount()}"/></h4>
		<a href="/expenses">Go Back</a>
	</div>
</body>
</html>