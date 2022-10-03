<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Vehicles</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/gocheetaonline" user="root"
		password="" />

	<sql:query var="vehicles" dataSource="${myData}">
   		
		select v.vehicle_id , v.vehicle_name , v.vehicle_no , c.category   , b.branch_name  , d.driver_name  from vehicle v , vehicle_category c , branch b , driver d where c.category_id = v.vehicle_category and b.branch_id = v.vehicle_branch and d.driver_id = v.vehicle_driver;
	</sql:query>

			<jsp:include page="includes/adminNav.jsp" />

			<c:if test="${sessionScope.adminID == null}">
				<c:redirect url="admin-login.jsp" />
			</c:if>


<div class="container mt-4">

		<c:if test="${error != null}">
			<div class="alert alert-danger mt-2 alert-dismissible" role="alert">
				<c:out value='${error}' />
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<c:if test="${success != null}">
			<div class="alert alert-success mt-2 alert-dismissible" role="alert">
				<c:out value='${success}' />
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

	<h1>Vehicle Manage</h1>
	<a href="add-new-vehicle.jsp" class="btn btn-primary">Add New Vehicle</a>
	
	<table class="table table-striped table-hover mt-4">
			<thead>
				<tr>
					<th>Vehicle ID</th>
					<th>Vehicle Name</th>
					<th>Vehicle No</th>
					<th>Category</th>
					<th>Branch</th>
					<th>Driver</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vehicle" items="${vehicles.rows}">
					<tr id='<c:out value="${vehicle.vehicle_id}" />'>
						<td><c:out value="${vehicle.vehicle_id}" /></td>
						<td><c:out value="${vehicle.vehicle_name}" /></td>
						<td><c:out value="${vehicle.vehicle_no}" /></td>
						<td><c:out value="${vehicle.category}" /></td>
						<td><c:out value="${vehicle.branch_name}" /></td>
						<td><c:out value="${vehicle.driver_name}" /></td>

						<td>
						 <button class='btn btn-warning' id='editBtn' onclick='getValuesForEdit(<c:out value="${vehicle.vehicle_id}" />)'>Edit</button>
			
						</td>
						<td>
							<form action="AdminController" method="post">
								<input type='hidden' name='id'
									value='<c:out value='${vehicle.vehicle_id}'  />' /> <input
									type='submit' name='actionDelete' value='Delete'
									class="btn btn-danger" />
							</form>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
</div>
	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script>

function getValuesForEdit(id){
	var parentElm = document.getElementById(id);
	var name = parentElm.children[1].innerHTML;
	var number = parentElm.children[2].innerHTML;
	
	localStorage.setItem('vehicle_id', id)
	localStorage.setItem('vehicle_name', name)
	localStorage.setItem('vehicle_no',number )
	
	window.location.href = "add-new-vehicle.jsp"
	
}

</script>

</body>
</html>