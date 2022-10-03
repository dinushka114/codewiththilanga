<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Feedbacks</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
</head>
<body>

	<jsp:include page="includes/navbar.jsp" />




	<div class="container mt-4">


		<div class="ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2">


			<h1>My Feedbacks</h1>

			<c:if test="${error != null}">
				<div class="alert alert-danger mt-2 alert-dismissible" role="alert">
					<c:out value='${error}' />
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>


			<form action="AdminController" method="post" class="mt-4">
				<div class="mb-3">
					<label for="name" class="form-label">Username</label> <input
						type="text" class="form-control" id="nameInput" name='username'
						required />
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type='password' class="form-control" id="passwordInput"
						name='password' required />
				</div>

				<div class='mb-3'>
					<input type='submit' class='btn btn-primary w-100' name='action'
						value='Login'>
				</div>


			</form>

		</div>

	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>