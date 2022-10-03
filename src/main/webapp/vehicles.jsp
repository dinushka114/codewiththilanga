<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Drivers</title>
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

	<sql:query var="vehicles" dataSource="${myData}">
   		SELECT v.vehicle_id , v.vehicle_name , v.vehicle_no , c.category  , c.category_id ,c.price , d.driver_name , d.driver_id ,  v.is_available , v.vehicle_branch
   		FROM vehicle v , vehicle_category c , driver d
   		WHERE v.vehicle_branch IN ( 
   		SELECT customer_branch
        FROM customer 
        WHERE customer_username = '<c:out
			value="${sessionScope.username}" />')
        AND v.vehicle_category = c.category_id AND v.vehicle_driver = d.driver_id And v.is_available = true;
	</sql:query>
	
	<sql:query var="sources" dataSource="${myData}">
   		SELECT s.source_id ,  s.source_address FROM source s where s.branch_id in (SELECT customer_branch
        FROM customer 
        WHERE customer_username = '<c:out
			value="${sessionScope.username}" />')	 
	</sql:query>
	
	<sql:query var="destinations" dataSource="${myData}">
   		SELECT d.destination_id ,  d.destination_city FROM destination d where d.branch_id in (SELECT customer_branch
        FROM customer 
        WHERE customer_username = '<c:out
			value="${sessionScope.username}" />')	 
	</sql:query>
	


	<jsp:include page="includes/navbar.jsp" />


	<div class="container mt-4">

		<c:if test="${sessionScope.username == null}">
			<c:redirect url="customer-login.jsp" />
		</c:if>



		<h1>
			Available Vehicles for you ,
			<c:out value="${sessionScope.username}" />
		</h1>
		
		
		<c:if test="${price != null}">
				<h2>Total cost is : <c:out value='Rs ${price}0' /></h2>
		</c:if>
		
		<c:if test="${process != null}">
				<p><c:out value='${process}' /></p>
				<p>You will be redirect your bookings page...</p>
				 <script>
			         setTimeout(function(){
			            window.location.href = 'my-bookings.jsp';
			         }, 5000);
			      </script>
		</c:if>
		
		
		<div
			class='mt-4 row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5'>
			<c:forEach var="vehicle" items="${vehicles.rows}">
				<div class="card m-2 p-0">
					<div class="card-body">
						<h3 class="card-title">
							<c:out value='${vehicle.category}' />
						</h3>
						<h6 class="card-subtitle mb-2 text-muted">
							<c:out value='${vehicle.vehicle_name}' />
							-
							<c:out value='${vehicle.driver_name}' />
						</h6>

						<c:if test="${vehicle.is_available == true}">
							<span class="badge bg-success">Available</span>
						</c:if>
						<c:if test="${vehicle.is_available == false}">
							<span class="badge bg-warning">Not Available</span>
						</c:if>


					</div>
					<div class='card-footer'>
						<form name='travelForm' action="CustomerController" method='post'>
							<div class='mb-3'>
								<label>Select Source Address</label> 
								<select
									name = 'source'
									class='form-control'>
									<c:forEach var="source" items="${sources.rows}">
										<option value='<c:out value='${source.source_address }' />'> <c:out value='${source.source_address }' /> </option>
									</c:forEach>
								</select>
							</div>
							<div class='mb-3'>
								<label>Select Destination Address</label>
								 <select
								 	name='destination'
									class='form-control'>
									<c:forEach var="destination" items="${destinations.rows}">
										<option value='<c:out value='${destination.destination_city}' />'><c:out value='${destination.destination_city}' /></option>
									</c:forEach>
								</select>
							</div>
							
							<div class='mb-3'>
								<label>Payment method</label>
								<input type='text' class='form-control' disabled value='Cash on delivery' />
							</div>

							<div>
							<input type='hidden' name='bid' value='<c:out value='${vehicle.vehicle_branch}' />'/>
							<input type='hidden' name='vehicle_type_price'
									value='<c:out value='${vehicle.price}' />' /> 
								<input type='hidden' name='vehicle_id'
									value='<c:out value='${vehicle.vehicle_id}' />' /> 
									
									<input type='hidden' name='driver_id'
									value='<c:out value='${vehicle.driver_id}' />' /> 
								<div class='clearfix'>
									<input
									class='btn btn-secondary pull-left' type='submit' name='action'
									value='book' />
									
									<input
									class='btn btn-warning pull-right' type='submit' name='action'
									value='check cost' />
								</div>
							</div>

						</form>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>