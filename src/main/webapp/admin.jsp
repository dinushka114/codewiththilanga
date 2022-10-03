<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin dashboard</title>
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

	<sql:query var="salesGalle" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =1 ;
	</sql:query>

	<sql:query var="salesKandy" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =2 ;
	</sql:query>

	<sql:query var="salesNugegoda" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =3 ;
	</sql:query>

	<sql:query var="salesGampaha" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =4 ;
	</sql:query>

	<sql:query var="salesKurunegala" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =5 ;
	</sql:query>

	<sql:query var="salesJaffna" dataSource="${myData}">
   		select sum(c.cost) as 'sales'  , b.branch_name from customer_booking c , branch b where b.branch_id = c.branch_id and c.branch_id =6 ;
	</sql:query>

	<sql:query var="allSales" dataSource="${myData}">
   		select sum(cost) as 'sales'   from customer_booking;
	</sql:query>

	<jsp:include page="includes/adminNav.jsp" />




	<div class="container mt-4">

		<h1 class='mt-4 mb-4'>Hello admin</h1>

		<h2>Total sale made by branches</h2>
		<div class='container'>

			<div class='row'>
				<c:forEach var="galle" items="${salesGalle.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${galle.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out value='${empty galle.sales ? "Not Yet" : galle.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach var="kandy" items="${salesKandy.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${kandy.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out value='${empty kandy.sales ? "Not Yet" : kandy.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach var="nugegoda" items="${salesNugegoda.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${nugegoda.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out
										value='${empty nugegoda.sales ? "Not Yet" : nugegoda.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach var="gampaha" items="${salesGampaha.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${gampaha.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out
										value='${empty gampaha.sales ? "Not Yet" : gampaha.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach var="kurunegala" items="${salesKurunegala.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${kurunegala.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out
										value='${empty kurunegala.sales ? "Not Yet" : kurunegala.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>

				<c:forEach var="jaffna" items="${salesJaffna.rows}">
					<div class='col-sm-4'>
						<div class="card text-dark bg-warning mb-3 p-0">
							<div class="card-header">
								<c:out value='${jaffna.branch_name }' />
							</div>
							<div class="card-body">
								<h2 class="card-title">
									<c:out
										value='${empty jaffna.sales ? "Not Yet" : jaffna.sales }' />
								</h2>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>




		</div>

		<h2 class='mt-4'>Total sale made by all branches</h2>

		<div class='container'>
			<c:forEach var="all" items="${allSales.rows}">
				<div class="card text-white bg-success mb-3"
					style="max-width: 18rem;">
					<div class="card-header">Sales of all branches</div>
					<div class="card-body">
						<h2 class="card-title"><c:out value='${all.sales }' /></h2>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>
</body>
</html>