<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="admin.jsp">Admin Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="customer-bookings.jsp">Customer bookings</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" aria-current="page" href="customer-feedbacks.jsp">Customer feedbacks</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="driver-manage.jsp">Drivers</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="vehicle-manage.jsp">Vehicles</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="category-manage.jsp">Vehicle Categories</a>
        </li>
      </ul>
      <form class="d-flex ms-auto" action="AdminController" method="post">
        <input class="btn btn-danger" type="submit"  name="action" value="Logout">
      </form>
    </div>
    
  </div>
</nav>