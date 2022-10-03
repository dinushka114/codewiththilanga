<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp">Go Cheeta Online</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
			<c:if test="${sessionScope.driverId == null}">
				<li class="nav-item">
					<a class="nav-link" href="vehicles.jsp">Vehicles</a>
				</li>
			</c:if>
			</ul>
			<ul class='d-flex ms-auto navbar-nav'>
				<c:if test="${sessionScope.driverId == null}">
				<li class='nav-item'>
					<a class='nav-link' href='customer-profile.jsp'>My Profile</a>
				</li>
				</c:if>
				<c:if test="${sessionScope.username == null && sessionScope.driverId == null}">
					<li class='nav-item'>
						<a class='nav-link' href='driver-login.jsp'>Driver</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.username == null && sessionScope.driverId == null}">
				<li class="nav-item">
					<a class="nav-link" href="admin-login.jsp">Admin</a>
				</li>
				</c:if>
			</ul>
			<ul class='navbar-nav'>
				<c:if test="${sessionScope.driverId != null}">
				<li class="nav-item">
					<a class="nav-link" href="driver-requests.jsp">My Requests</a>
				</li>
				</c:if>
				<c:if test="${sessionScope.username != null}">
					<li class='nav-item'>
						 <form class="d-flex ms-auto" action="CustomerController" method="post">
       						 <input class="btn btn-danger" type="submit"  name="action" value="Logout">
      					</form>
					</li>
				</c:if>
				
				<c:if test="${sessionScope.driverId != null}">
					<li class='nav-item'>
						 <form class="d-flex ms-auto" action="DriverController" method="post">
       						 <input class="btn btn-danger" type="submit"  name="action" value="Logout">
      					</form>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>