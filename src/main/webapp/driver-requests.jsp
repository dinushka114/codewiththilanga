<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<sql:query var="requests" dataSource="${myData}">
   		 SELECT d.request_id , d.status , d.source , d.destination , d.date , v.vehicle_no , c.customer_name , v.vehicle_id
   		 FROM driver_request d , vehicle v , customer c
   		 where d.driver_id in 
   		 (select driver_id from driver where driver_email = '<c:out value='${sessionScope.driverId }' />' ) AND
   		 d.customer_id = c.customer_id AND v.vehicle_id = d.vehicle_id  ;
	</sql:query>
		
	<jsp:include page="includes/navbar.jsp" />
	
		<c:if test="${sessionScope.driverId == null}">
			<c:redirect url="driver-login.jsp" />
		</c:if>


	<div class="container mt-4">

		<h1>Driver requests</h1>
		
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Request ID</th>
					<th>Customer Name</th>
					<th>Vehicle no</th>
					<th>Source</th>
					<th>Destination</th>
					<th>Date</th>
					<th>Status</th>
					<th>action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="request" items="${requests.rows}">
					<tr id='<c:out value="${request.request_id}" />'>
						<td><c:out value="${request.request_id}" /></td>
						<td><c:out value="${request.customer_name}" /></td>
						<td><c:out value="${request.vehicle_no}" /></td>
						<td><c:out value="${request.source}" /></td>
						<td><c:out value="${request.destination}" /></td>
						<td><c:out value="${request.date}" /></td>
						<td><c:out value="${request.status}" /></td>
						<td> 
							<form name='driverForm' action="DriverController" method="post">
							<input type='hidden' name='vid' value='<c:out value='${request.vehicle_id }' />' />
							<input type='hidden' name='rid' value='<c:out value='${request.request_id }' />' />
								<select name='action' class='form-control' onchange="document.driverForm.submit();">
									<option>select action</option>
									<option value='accept'>accept</option>
									<option value='decline'>decline</option>
									<option value='complete'>complete</option>
								</select>
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
</body>
</html>