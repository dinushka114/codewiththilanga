<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cusotmer details</title>
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
	
	<sql:query var="bookings" dataSource="${myData}">
   		SELECT c.booking_id , m.customer_name , b.branch_name , d.driver_name , d.driver_contact , c.status , c.source , c.destination , c.cost , d.driver_name , v.vehicle_no , v.vehicle_name , v.vehicle_category , t.category 
   		from customer_booking c , driver d , vehicle v , customer m, branch b , vehicle_category t 
   		WHERE v.vehicle_id = c.vehicle_id AND 
   		d.driver_id = c.driver_id AND 
   		m.customer_id = c.customer_id AND 
   		b.branch_id = c.branch_id AND 
   		v.vehicle_category = t.category_id AND 
   		b.branch_id = <c:out value='${empty branch ? 0: branch}' />;
	</sql:query>

	<jsp:include page="includes/adminNav.jsp" />


	<c:if test="${sessionScope.adminID == null}">
		<c:redirect url="admin-login.jsp" />
	</c:if>

	<div class="container mt-4">

		<h1>Customer bookings</h1>

		<form name='branchForm' action="AdminController" method="get">
			<div class='mb-3'>
				<label>Select branch</label> 
				<select name='action' class='form-control mb-4' onchange="document.branchForm.submit();">
				<option>Select branch</option>
					<c:forEach var="branch" items="${branches.rows}">
						
						<option value='<c:out value='${branch.branch_id}' />'><c:out
								value='${branch.branch_name}' /></option>
					</c:forEach>
				</select>
			</div>
		</form>
		
		<table class="table table-striped table-hover mt-4">
			<thead>
				<tr>
					<th>Booking ID</th>
					<th>Customer Name</th>
					<th>Branch</th>
					<th>Driver Name</th>
					<th>Driver Contact</th>
					<th>Vehicle Name</th>
					<th>Vehicle No</th>
					<th>Vehicle Category</th>
					<th>Source</th>
					<th>Desination</th>
					<th>Status</th>
					<th>Cost</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${bookings.rows}">
					<tr id='<c:out value="${booking.booking_id}" />'>
						<td><c:out value="${booking.booking_id}" /></td>
						<td><c:out value="${booking.customer_name}" /></td>
						<td><c:out value="${booking.branch_name}" /></td>
						<td><c:out value="${booking.driver_name}" /></td>
						<td><c:out value="${booking.driver_contact}" /></td>
						<td><c:out value="${booking.vehicle_name}" /></td>
						<td><c:out value="${booking.vehicle_no}" /></td>
						<td><c:out value="${booking.category}" /></td>
						<td><c:out value="${booking.source}" /></td>
						<td><c:out value="${booking.destination}" /></td>
						<td><c:out value="${booking.status}" /></td>
						<td><c:out value="${booking.cost}" /></td>
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
</body>
</html>