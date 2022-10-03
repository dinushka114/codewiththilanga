<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
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
	
	<sql:query var="bookings" dataSource="${myData}">
   		
   		SELECT c.booking_id , c.source , c.destination , c.cost , c.status ,  d.driver_name , v.vehicle_no from customer_booking c , driver d , vehicle v
   		WHERE customer_id IN ( 
   		SELECT customer_id
        FROM customer 
        WHERE customer_username = '<c:out
			value="${sessionScope.username}" />') 
			AND v.vehicle_id = c.vehicle_id AND d.driver_id = c.driver_id;
		
	</sql:query>


	<jsp:include page="includes/navbar.jsp" />

	<c:if test="${sessionScope.username == null}">
		<c:redirect url="customer-login.jsp" />
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


		<div class='sidebar'>
			<jsp:include page="includes/sidebar.jsp" />
		</div>
		<div class='content'>
			<h1>My Bookings</h1>
			
			<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Booking ID</th>
					<th>Driver Name</th>
					<th>Vehicle No</th>
					<th>Source</th>
					<th>Destination</th>
					<th>Cost</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${bookings.rows}">
					<tr id='<c:out value="${book.booking_id}" />'>
						<td><c:out value="${book.booking_id}" /></td>
						<td><c:out value="${book.driver_name}" /></td>
						<td><c:out value="${book.vehicle_no}" /></td>
						<td><c:out value="${book.source}" /></td>
						<td><c:out value="${book.destination}" /></td>
						<td><c:out value="${book.cost}" /></td>
						<td><c:out value="${book.status}" /></td>
						<c:if test="${book.status == 'dropped'}">
							<td> 
								<form action='CustomerController' method='post'>
									<input type='hidden' value='<c:out value='${book.booking_id}' />' name='bookingId' />
									<button id='feedbackBtn' type='submit' name='action' value='give feedback' class='btn btn-success'>give feedback</button>
								</form>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		
		</div>
	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
		
	<script>
		
	var feedbackBtn = document.getElementById('feedbackBtn');
	
	
	function feedbackLoadData(id){
		localStorage.setItem("bookingId", id);
		window.location.href="customer-feedback.jsp"
		
	}
	
	
	</script>

</body>
</html>