<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Vehicle</title>
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

	<sql:query var="vehicle_categories" dataSource="${myData}">
   		 SELECT * FROM vehicle_category;
	</sql:query>
	
	<sql:query var="branches" dataSource="${myData}">
   		 SELECT * FROM branch;
	</sql:query>
	
	<sql:query var="drivers" dataSource="${myData}">
   		 SELECT * FROM driver;
	</sql:query>

	<jsp:include page="includes/adminNav.jsp" />

	<c:if test="${sessionScope.adminID == null}">
		<c:redirect url="admin-login.jsp" />
	</c:if>


	<div class="container mt-4">

		

		<div class='ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2'>
		
		<h1 id='title'>Register a new vehicle</h1>
		<form action='AdminController' method='post' class='mt-4'>
		
			<div class="mb-3">
			<input type='hidden' name='id' id='vid' />
				<label for="name" class="form-label">Vehicle Name</label> <input type="text"
					class="form-control" id="nameInput" name='vname' 
					required />
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">Vehicle Registered Number</label> <input type="text"
					class="form-control" id="regNoInput" name='vnumber' placeholder='ex-: XX XX XXXX'
					required />
			</div>
			
			<div class="mb-3">
				<label for="name" class="form-label">Vehicle Category</label>
				<select name='vcategory' class='form-control'>
						<c:forEach var="category" items="${vehicle_categories.rows}">
							<option value='<c:out value="${category.category_id}" />'><c:out value="${category.category}" /></option>
						</c:forEach>
						
				</select>
			</div>


			<div class="mb-3">
				<label for="name" class="form-label">Vehicle Branch</label>
				<select name='vbranch' class='form-control'>
						<c:forEach var="branch" items="${branches.rows}">
							<option value='<c:out value="${branch.branch_id}" />'><c:out value="${branch.branch_name}" /></option>
						</c:forEach>
						
				</select>
			</div>

			<div class="mb-3">
				<label for="name" class="form-label">Driver</label>
				<select name='vdriver' class='form-control'>
						<c:forEach var="driver" items="${drivers.rows}">
							<option value='<c:out value="${driver.driver_id}" />'><c:out value="${driver.driver_name}" /></option>
						</c:forEach>
						
				</select>
			</div>
			
			<div class="mb-3">
				<input type='submit' name='action' id='submitBtn' value='Add Vehicle' class='btn btn-primary w-100' />
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
		var title = document.getElementById('title')
		var btn = document.getElementById('submitBtn')
		var id = document.getElementById('vid')
		var vehicle_name = document.getElementById('nameInput')
		var number = document.getElementById('regNoInput')
		
		if (localStorage.getItem("vehicle_id") !== null) {
			title.innerHTML = "Update vehicle"
			btn.value = "Update Vehicle"
			
			id.value = localStorage.getItem("vehicle_id");
			vehicle_name.value = localStorage.getItem("vehicle_name");
			number.value = localStorage.getItem("vehicle_no");
			
			 localStorage.removeItem("vehicle_id");
			 localStorage.removeItem("vehicle_name");
			 localStorage.removeItem("vehicle_no");
		
		}
	
	</script>
		
</body>
</html>