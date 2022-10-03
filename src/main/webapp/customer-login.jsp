<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Logn</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="includes/navbar.jsp" />
	
	<div class='container'>
		<div class="ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2">
			
			
			<h1>Customer Login</h1>
			
			<% if(session.getAttribute("username")!=null){ %>
			<% response.sendRedirect("vehicles.jsp"); %>
			<% }%>
					
			
			<% String error =(String)request.getAttribute("error"); %>
			<% if(error!=null){ %>
				<div class="alert alert-danger mt-2" role="alert">
				  <% out.print(error); %>
				</div>
			<% } %>
			
			<form action="CustomerController" method="post" class="mt-4">
				<div class="mb-3">
				  <label for="name" class="form-label">Username</label>
				  <input type="text" class="form-control" id="nameInput" name='username'  required />
				</div>
				
				<div class="mb-3">
				  <label for="password" class="form-label">Password</label>
				  <input type='password' class="form-control" id="passwordInput" name='password'  required  />
				</div>
				
				<div class='mb-3'>
					<input type='submit' class='btn btn-primary w-100' name='action' value='Login'>
				</div>
				
				<a href='customer-register.jsp'>don't have an account? click here to register</a>
				
			</form>
			
			</div>
	</div>
</body>
</html>