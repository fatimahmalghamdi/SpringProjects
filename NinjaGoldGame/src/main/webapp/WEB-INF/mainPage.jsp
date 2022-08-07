
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ninja Gold</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div>
	<h3>Your Gold: <c:out value="${gold}"/></h3>
	<a href="/clear">Reset</a>
	</div>

<div class="d-flex justify-content-around">
	<div class="container mt-3" style="width: 18rem;">
	    <h5 class="card-title">Farm</h5>
	    <p class="card-text">(earns 10-20 gold)</p>
	    <form method=POST action="/">
	     	<input type="hidden" name="location" value="farm">
  			<button class="btn btn-warning">Find Gold!</button>
		</form>
	</div>

	<div class="container mt-3" style="width: 18rem;">
	    <h5 class="card-title">Cave</h5>
	    <p class="card-text">(earns 5-10 gold)</p>
	    <form method=POST action="/">
	     	<input type="hidden" name="location" value="cave">
  			<button class="btn btn-warning">Find Gold!</button>
		</form>
	</div>
	
	<div class="container mt-3" style="width: 18rem;">
	    <h5 class="card-title">House</h5>
	    <p class="card-text">(earns 2-5 gold)</p>
	    <form method=POST action="/">
	     	<input type="hidden" name="location" value="house">
  			<button class="btn btn-warning">Find Gold!</button>
		</form>
	</div>
	
	<div class="container mt-3" style="width: 18rem;">
	    <h5 class="card-title">Casino</h5>
	    <p class="card-text">(earns/takes 0-50 gold)</p>
	    <form method=POST action="/">
	     	<input type="hidden" name="location" value="casino">
  			<button class="btn btn-warning">Find Gold!</button>
		</form>
	</div>
	
	<div class="container mt-3" style="width: 18rem;">
	    <h5 class="card-title">Spa</h5>
	    <p class="card-text">(takes 5-20 gold)</p>
	    <form method=POST action="/">
	     	<input type="hidden" name="location" value="spa">
  			<button class="btn btn-warning">Spend Gold!</button>
		</form>
	</div>
</div>
	
	
<div>
	<h2>Activities:</h2>
 	<div class="" style="">   
    	<div class=" p-3 mb-3 mb-md-0 mr-md-3 bg-light" 
      	style="margin: 0 auto;
	    overflow-y: scroll;
	    width: 600px;
	    height: 200px;
		display: flex;
		flex-direction: column-reverse;">

		
		</div>
	</div>
</div>
</body>
</html>

