<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Feedback</title>
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
   		 SELECT * FROM feedback where booking_id = <c:out value='${bookId}' />;
	</sql:query>

	<jsp:include page="includes/navbar.jsp" />

	<c:forEach var="feedback" items="${feedbacks.rows}">
		
		<c:if test="${feedback.booking_id == bookId }">
			<c:redirect url="my-bookings.jsp" />
		</c:if>>
	
	</c:forEach>


	<div class="container mt-4">


		<div class="ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2">


			<h1>Customer Feedback</h1>

			<form action="CustomerController" method='post'>
				
		
			  <div class="mb-3">
			  <input type='hidden' name='bid' value='<c:out value='${bookId}' />' />
			    <label for="feedbackInputLabel" class="form-label">Enter your feedback</label>
			    <textarea rows="5" cols="10" name="feedback" class="form-control" id="exampleInputPassword1"></textarea>
			  </div>
		
			  <input type="submit" class="btn btn-primary" name='action' value="submit feedback" />
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