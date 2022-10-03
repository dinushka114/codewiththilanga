<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add driver</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
</head>
<body>

	<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/gocheetaonline" user="root"
		password="" />
		
	<sql:query var="branches" dataSource="${myData}">
   		 SELECT * FROM branch;
	</sql:query>

	<jsp:include page="includes/adminNav.jsp" />

	<c:if test="${sessionScope.adminID == null}">
		<c:redirect url="admin-login.jsp" />
	</c:if>


	<div class="container mt-4">

		<div class='ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2'>
			<h1 id='title'>Add New Driver</h1>

		<form action="AdminController" method="post">

			<div class="mb-3">
				<input type='hidden' id='id' name='id' />
				<label for="name" class="form-label">Name</label> 
				<input type="text" class="form-control" id="nameInput" name='dname' required />
			</div>
			
			<div class="mb-3">
				<label for="name" class="form-label">Contact No</label> 
				<input type="text" class="form-control" id="contactNoInput" name='dcontact' required />
			</div>


			<div class="mb-3">
				<label for="password" class="form-label">Email</label> <input
					type='email' class="form-control" id="emailInput" name='demail' required />
			</div>
			
			<div class="mb-3">
				<label for="name" class="form-label">Branch</label>
				<select name='dbranch' class='form-control'>
						<c:forEach var="branch" items="${branches.rows}">
							<option value='<c:out value="${branch.branch_id}" />'><c:out value="${branch.branch_name}" /></option>
						</c:forEach>
						
				</select>
			</div>
			
			<div class='mb-3'>
					<input type='submit' id='submitBtn' class='btn btn-primary w-100'  name='action' value='Register Driver'>
				</div>

		</form>
		
		</div>

	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
		
	<script>
		var title = document.getElementById('title');
		var addBtn = document.getElementById('submitBtn');
		var dname = document.getElementById('nameInput');
		var contact = document.getElementById('contactNoInput');
		var email = document.getElementById('emailInput');
		var did = document.getElementById('id')
		
		if (localStorage.getItem("driver_id") !== null) {
			  title.innerHTML = "Edit a driver"
			  addBtn.value = "Update Driver"
			  did.value = localStorage.getItem("driver_id");
			  dname.value = localStorage.getItem("driver_name");
			  contact.value = localStorage.getItem("driver_contact");
			  email.value = localStorage.getItem("driver_email");
			  
			  localStorage.removeItem("driver_id");
			  localStorage.removeItem("driver_name");
			  localStorage.removeItem("driver_contact");
			  localStorage.removeItem("driver_email");
			}
		
	</script>	
</body>
</html>