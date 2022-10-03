<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Drivers</title>
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

	<sql:query var="drivers" dataSource="${myData}">
   		 SELECT d.driver_id , d.driver_name , d.driver_contact , d.driver_email , b.branch_name FROM driver d , branch b where d.driver_branch=b.branch_id;
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

		<div>
			<h1>Manage drivers</h1>
			<a href="add-new-driver.jsp" class="btn btn-primary">Add New Driver</a>
		</div>
		
		<table class="table table-striped table-hover mt-4">
			<thead>
				<tr>
					<th>Driver ID</th>
					<th>Name</th>
					<th>Contact No</th>
					<th>Email</th>
					<th>Branch</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="driver" items="${drivers.rows}">
					<tr id='<c:out value="${driver.driver_id}" />'>
						<td><c:out value="${driver.driver_id}" /></td>
						<td><c:out value="${driver.driver_name}" /></td>
						<td><c:out value="${driver.driver_contact}" /></td>
						<td><c:out value="${driver.driver_email}" /></td>
						<td><c:out value="${driver.branch_name}" /></td>

						<td>
						 <button class='btn btn-warning' id='editBtn' onclick='getValuesForEdit(<c:out value="${driver.driver_id}" />)'>Edit</button>
			
						</td>
						<td>
							<form action="AdminController" method="get">
								<input type='hidden' name='id'
									value='<c:out value='${driver.driver_id}'  />' /> <input
									type='submit' name='action' value='Delete'
									class="btn btn-danger" />
							</form>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
		
	<script>
		function getValuesForEdit(id){
			var parentElm = document.getElementById(id);
			var name = parentElm.children[1].innerHTML;
			var contact = parentElm.children[2].innerHTML;
			var email = parentElm.children[3].innerHTML;
			
			localStorage.setItem('driver_id', id)
			localStorage.setItem('driver_name', name)
			localStorage.setItem('driver_contact', contact)
			localStorage.setItem('driver_email', email)
			
			window.location.href="add-new-driver.jsp";
			
		}
	
	</script>	
		
</body>
</html>