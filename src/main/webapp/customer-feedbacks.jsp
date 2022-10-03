<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cusotmer feedbacks</title>
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

	<sql:query var="feedbacks" dataSource="${myData}">
   		SELECT f.feedback_id , c.customer_name , r.branch_name , v.vehicle_no ,d.driver_name , f.feedback
   		 FROM feedback f , customer c , customer_booking b , branch r , vehicle v , driver d
   		 WHERE v.vehicle_id = b.vehicle_id AND 
   		 c.customer_branch = r.branch_id AND 
   		 f.booking_id = b.booking_id AND 
   		 v.vehicle_branch = r.branch_id AND 
         d.driver_id = b.driver_id AND
   		 b.status='dropped' group by f.feedback_id;
	</sql:query>


	<jsp:include page="includes/adminNav.jsp" />


	<c:if test="${sessionScope.adminID == null}">
		<c:redirect url="admin-login.jsp" />
	</c:if>

	<div class="container mt-4">

		<h1>Customer feedbacks</h1>

		
		<table class="table table-striped table-hover mt-4">
			<thead>
				<tr>
					<th>Feedback ID</th>
					<th>Customer Name</th>
					<th>Branch</th>
					<th>Vehicle No</th>
					<th>Driver Name</th>
					<th>Feedback</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feedback" items="${feedbacks.rows}">
					<tr id='<c:out value="${feedback.feedback_id}" />'>
						<td><c:out value="${feedback.feedback_id}" /></td>
						<td><c:out value="${feedback.customer_name}" /></td>
						<td><c:out value="${feedback.branch_name}" /></td>
						<td><c:out value="${feedback.vehicle_no}" /></td>
						<td><c:out value="${feedback.driver_name}" /></td>
						<td><c:out value="${feedback.feedback}" /></td>
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