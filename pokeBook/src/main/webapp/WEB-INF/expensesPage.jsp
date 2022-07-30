<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- New line below to use the JSP Form Tag Library -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expenses Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="container">
	<c:if test="${success != null}">
		<div class="alert alert-success">
			<c:out value="${success}"/>
		</div>
	</c:if>
	<div class="container mt-3">
		<h1 style="color:blue">All Expenses</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Expense</th>
					<th>Vendor</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="expense" items="${allExpenses}">
				<tr>
					<td><c:out value="${expense.getExpense_name()}"></c:out></td>
					<td><c:out value="${expense.getVendor()}"></c:out></td>
					<td>$<c:out value="${expense.getAmount()}"></c:out></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container mt-3">
		<h1 style="color:blue">Track an Expense</h1>
		<form:form action="/expenses" method="post" modelAttribute="myExpense">
	
			<div class="mb-3">
				<form:label path="expense_name" class="form-label">Expense Name:</form:label>
				<form:input path="expense_name" cssClass="form-control" cssErrorClass="form-control is-invalid"  id="expenseName"/>
				<form:errors path="expense_name" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="vendor" class="form-label">Vendor:</form:label>
				<form:input path="vendor" class="form-control" cssErrorClass="form-control is-invalid" id="vendor"/>
				<form:errors path="vendor" cssClass="invalid-feedback"/>
			</div>
	
			<div class="mb-3">
				<form:label path="amount" class="form-label">Amount:</form:label>
				<form:input path="amount" class="form-control" cssErrorClass="form-control is-invalid" id="amount"/>
				<form:errors path="amount" cssClass="invalid-feedback"/>
			</div>
			
			<div class="mb-3">
				<form:label path="description" class="form-label">Description:</form:label>
				<form:textarea path="description" class="form-control" cssErrorClass="form-control is-invalid" id="description" rows="10" cols="10"/>
				<form:errors path="description" cssClass="invalid-feedback"/>
			</div>
	
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
	
</div>

</body>
</html>