<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage vehicle categories</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
</head>

<style>
@media only screen and (min-width: 600px) {
	#categoryAddForm {
		width:35%;
	}
}
</style>

<body>

	<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/gocheetaonline" user="root"
		password="" />

	<sql:query var="categories" dataSource="${myData}">
   		 SELECT * FROM vehicle_category;
	</sql:query>


	<jsp:include page="includes/adminNav.jsp" />


	<c:if test="${sessionScope.adminID == null}">
		<c:redirect url="admin-login.jsp" />
	</c:if>

	
	<div class="container mt-4">

		<div>
			<h1>Vehicle Categories</h1>
			<form class='d-flex ms-auto mt-4 mb-4' id='categoryAddForm'
				action="AdminController" method="post">
					<div class="m-2">
						<input type='hidden' id='hiddenId' name='catID' >
						<input type='text' name='catPrice' id='catPriceInput' class='form-control' placeholder='enter price' />
						<input type="text" class="form-control" id="catInput" placeholder='enter category'
							name='category' required />
					</div>

					<input type="submit" id='addBtn' name="action" value="Add category"
						class="btn btn-primary m-2" />
			</form>

		</div>


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




		<!-- 	Category Add Form Start-->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<form class='w-1/2' action="AdminController" method="post">
						<div class="modal-body">
							<div class="mb-3">
								<label for="category" class="form-label">Category</label> <input
									type="text" class="form-control" id="catInput" name='category'
									required />
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<input type="submit" name="action" value="Add category"
								class="btn btn-primary" />
						</div>
					</form>

				</div>
			</div>
		</div>
		<!-- 	Category Add Form End-->

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Category ID</th>
					<th>Category</th>
					<th>Price</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categories.rows}">
					<tr id='<c:out value="${category.category_id}" />'>
						<td><c:out value="${category.category_id}" /></td>
						<td><c:out value="${category.category}" /></td>
						<td><c:out value="${category.price}" /></td>
						<td>
						 <button class='btn btn-warning' id='editBtn' onclick='getValuesForEdit(<c:out value="${category.category_id}" />)'>Edit</button>
			
						</td>
						<td>
							<form action="AdminController" method="post">
								<input type='hidden' name='id'
									value='<c:out value='${category.category_id}'  />' /> <input
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
		
		var editBtn = document.getElementById('editBtn');
		var addBtn = document.getElementById('addBtn');
		var hiddenId = document.getElementById('hiddenId');
		var categoryField = document.getElementById('catInput');
		var catPriceInputField = document.getElementById('catPriceInput')
		
		function getValuesForEdit(id){
			var parentElm = document.getElementById(id);
			categoryField.value = parentElm.children[1].innerHTML;
			catPriceInputField.value = parentElm.children[2].innerHTML;
			hiddenId.value = id;
			addBtn.value = "Edit Category"
		}
		
	
	</script>	
</body>
</html>