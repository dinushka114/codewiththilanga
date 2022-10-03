<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>

	<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/gocheetaonline" user="root"
		password="" />

	<sql:query var="branches" dataSource="${myData}">
   		 SELECT * FROM branch;
	</sql:query>


<jsp:include page="includes/navbar.jsp" />
	
	<% if(session.getAttribute("username")!=null){ %>
	<% response.sendRedirect("vehicles.jsp"); %>
	<% }%>

	<div class='container'>
		<div class="ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-2">
			<h1>Register a customer</h1>

			<c:if test="${error != null}">
			<div class="alert alert-danger mt-2 alert-dismissible" role="alert">
				<c:out value='${error}' />
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			</c:if>

			<form action="CustomerController" method="post" class="mt-4">
				<div class="mb-3">
				  <label for="name" class="form-label">Name</label>
				  <input type="text" class="form-control" id="nameInput" name='name' placeholder="Saman bandara" required />
				</div>
				<div class="mb-3">
				  <label for="email" class="form-label">Email</label>
				  <input type='email' class="form-control" id="emailInput" name='email' onkeyup="ValidateEmail();" required placeholder="name@example.com" />
				  <div  id="lblErrorEmail" role="alert">
					
				  </div>
				</div>
				
				<div class="mb-3">
				  <label for="username" class="form-label">Username</label>
				  <input type='text' class="form-control" id="usernameInput"  name='uname' required  />
				</div>
				
				<div class="mb-3">
				  <label for="password" class="form-label">Password</label>
				  <input type='password' class="form-control" id="passwordInput" onkeyup="ValidatePassword();" name='pass'  required  />
				</div>
				
				<div class="mb-3">
				  <label for="password" class="form-label">Confirm password</label>
				  <input type='password' class="form-control" id="confirmPasswordInput" name='cpass' onkeyup="ValidatePassword();"  required  />
				</div>
				
				 <div id="lblErrorPasswordSimple" role="alert">
					Password must contain at least one lower case letter
				  </div>
				  <div id="lblErrorPasswordUpper" role="alert">
					Password must contain at least one upper case letter
				  </div>
				  <div id="lblErrorPasswordSpecial" role="alert">
					Password must contain at least one special charactor
				  </div>
				  <div id="lblErrorPasswordNumber" role="alert">
					Password must contain at least one number
				  </div>
				  <div id="lblErrorPasswordLength" role="alert">
					Password must contain at least 8 characters
				  </div>
				  <div id="lblErrorPasswordSame" role="alert">
					Passowords must be same
				  </div>
				
				<div class="mb-3">
					<label for="branch" class="form-label">Branch</label>
					<select name='branch' class='form-control'>
						<c:forEach var="branch" items="${branches.rows}">
							<option value='<c:out value="${branch.branch_id}" />'><c:out value="${branch.branch_name}" /></option>
						</c:forEach>
						
					</select>
					
				</div>
				
				<div class='mb-3'>
					<input type='submit' id='submitBtn' class='btn btn-primary w-100' disabled name='action' value='Register Now'>
				</div>
				
				<a href='customer-login.jsp'>already registerd? click here to login</a>
				
			</form>

		</div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script>

//Email validations
var lblError = document.getElementById("lblErrorEmail");
lblError.style.display = "none";

function ValidateEmail() {
    var email = document.getElementById("emailInput").value;
    var lblError = document.getElementById("lblErrorEmail");
    var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    
    if (!expr.test(email)) {
    	lblError.setAttribute("class" , "alert alert-danger mt-2")
    	lblError.style.display = "block"
    	lblError.innerHTML = "Invalid Email!!"
    }else{
    	lblError.setAttribute("class" , "alert alert-success mt-2")
    	lblError.style.display = "block"
    	lblError.innerHTML = "Email validated"
    }
}

//password validations
var passwordValidationErrors = ""

var cpassword = document.getElementById('confirmPasswordInput');
var passwordErrorSimple = document.getElementById("lblErrorPasswordSimple"); 
var passwordErrorUpper = document.getElementById("lblErrorPasswordUpper");
var passwordErrorSpecial = document.getElementById("lblErrorPasswordSpecial"); 
var passwordErrorNumber = document.getElementById("lblErrorPasswordNumber");
var passwordErrorLength = document.getElementById("lblErrorPasswordLength");
var passwordErrorSame = document.getElementById("lblErrorPasswordSame");

var submitBtn = document.getElementById('submitBtn');

passwordErrorSimple.style.display = "none"
passwordErrorUpper.style.display = "none"
passwordErrorSpecial.style.display = "none"
passwordErrorNumber.style.display = "none"
passwordErrorLength.style.display = "none"
passwordErrorSame.style.display = "none"



function ValidatePassword(){
	var test = 0;
	var password = document.getElementById('passwordInput').value;
	var cpassword = document.getElementById('confirmPasswordInput').value;
	
	var lowerCaseLetters = /[a-z]/g;
	var upperCaseLetters = /[A-Z]/g;
	var specialChrs =  /\W|_/g;
	var numbers = /[0-9]/g;
	
	if(password.match(lowerCaseLetters)){
		passwordErrorSimple.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorSimple.style.display = "block"
		test+=1;
	}else{
		passwordErrorSimple.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorSimple.style.display = "block"
		test-=1;
	}
	
	if(password.match(upperCaseLetters)){
		passwordErrorUpper.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorUpper.style.display = "block"
		test+=1;
	}else{
		passwordErrorUpper.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorUpper.style.display = "block"
		test-=1;
	}
	
	if(password.match(specialChrs)){
		passwordErrorSpecial.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorSpecial.style.display = "block"
		test+=1;
	}else{
		passwordErrorSpecial.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorSpecial.style.display = "block"
		test-=1;
	}
	
	if(password.match(numbers)){
		passwordErrorNumber.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorNumber.style.display = "block"
		test+=1;
	}else{
		passwordErrorNumber.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorNumber.style.display = "block"
		test-=1;
	}
	
	if(password.length>=8){
		passwordErrorLength.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorLength.style.display = "block"
		test+=1;
	}else{
		passwordErrorLength.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorLength.style.display = "block"
		test-=1;
	}
	
	if(password!='' && password==cpassword){
		passwordErrorSame.setAttribute("class" , "alert alert-success mt-2")
		passwordErrorSame.style.display = "block"
		test+=1;		
	}else{
		passwordErrorSame.setAttribute("class" , "alert alert-danger mt-2")
		passwordErrorSame.style.display = "block"
		test-=1;
	}
	
	if(test==6){
		submitBtn.disabled = false;
	}else{
		submitBtn.disabled = true;
	}

	
	}
	
</script>

</body>
</html>