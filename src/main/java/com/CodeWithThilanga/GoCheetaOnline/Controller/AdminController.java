package com.CodeWithThilanga.GoCheetaOnline.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CodeWithThilanga.GoCheetaOnline.Model.Driver;
import com.CodeWithThilanga.GoCheetaOnline.Model.Vehicle;
import com.CodeWithThilanga.GoCheetaOnline.Model.VehicleCategory;
import com.CodeWithThilanga.GoCheetaOnline.Service.AdminService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminService service;
    
    public AdminController() {
        service = AdminService.getAdminServiceInstance();
    }

	
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	boolean res = service.loginAdmin(username, password);
    	
    	if(res==true) {
    		HttpSession session = request.getSession(false);
    		session.setAttribute("adminID", username);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
    		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Invalid Login credentials");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin-login.jsp");
    		dispatcher.forward(request, response);
    	}
    	
    	
    }
    
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("adminID");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin-login.jsp");
		dispatcher.forward(request, response);
	}
    
    
    protected void addVehicleCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		float categoryPrice = Float.parseFloat(request.getParameter("catPrice"));
    	String categoryName = request.getParameter("category");
    	VehicleCategory category = new VehicleCategory(categoryPrice , categoryName);
 		boolean res = service.addVehicleCategory(category);
 		if(res) {
 			request.setAttribute("success", "Category added successfully!!");
 			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
 		}else {
 			request.setAttribute("error", "Something went wrong!!");
 			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
 		}
 		
 	}   
    
    protected void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	boolean res = service.deleteVehicleCategory(id);

    	if(res) {
    		request.setAttribute("success", "Category deleted successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    
    protected void editVehicleCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String id = request.getParameter("catID"); 
    	String category_name= request.getParameter("category");
    	float category_price = Float.parseFloat(request.getParameter("catPrice"));
    	VehicleCategory category = new VehicleCategory(id ,category_price ,  category_name);
    	boolean res = service.updateVehicleCategory(category);
    	if(res) {
    		request.setAttribute("success", "Category updated successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/category-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    protected void addNewDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String name = request.getParameter("dname"); 
    	String contact= request.getParameter("dcontact");
    	String email= request.getParameter("demail");
    	String branch = request.getParameter("dbranch");
    	
    	Driver driver = new Driver(name , contact , email , branch);
    	boolean res = service.addNewDriver(driver);
    	if(res) {
    		request.setAttribute("success", "Driver added successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    protected void editDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String id = request.getParameter("id");
    	String name = request.getParameter("dname"); 
    	String contact= request.getParameter("dcontact");
    	String email= request.getParameter("demail");
    	String branch = request.getParameter("dbranch");
    	Driver driver = new Driver(id , name , contact , email , branch);
    
    	
    	boolean res = service.editDriver(driver);
    	if(res) {
    		request.setAttribute("success", "Driver updated successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    protected void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	boolean res = service.deleteDriver(id);
    	if(res) {
    		request.setAttribute("success", "Driver deleted successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
       
    protected void getDataByBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("action");
    	request.setAttribute("branch", id);
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-bookings.jsp");
    	dispatcher.forward(request, response);
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	if(action.equals("Delete")) {
    		this.deleteDriver(request, response);
    	}else{
    		this.getDataByBranch(request, response);
    	}
    	
    	
    	
    	
	}
    
    protected void addNewVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String name = request.getParameter("vname"); 
    	String number= request.getParameter("vnumber");
    	String category= request.getParameter("vcategory");
    	String branch = request.getParameter("vbranch");
    	String driver = request.getParameter("vdriver");
    	
    	Vehicle vehicle = new Vehicle(name , number , category , branch , driver);
    	boolean res = service.addNewVehicle(vehicle);
    	if(res) {
    		request.setAttribute("success", "Vehicle added successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    protected void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String id = request.getParameter("id");
    	String name = request.getParameter("vname"); 
    	String number= request.getParameter("vnumber");
    	String category= request.getParameter("vcategory");
    	String branch = request.getParameter("vbranch");
    	String driver = request.getParameter("vdriver");
    	
    	Vehicle vehicle = new Vehicle(id , name , number , category , branch , driver);
    	
    	boolean res = service.updateVehicle(vehicle);
    	if(res) {
    		request.setAttribute("success", "Vehicle updated successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
    
    protected void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	boolean res = service.deleteVehicle(id);
    	if(res) {
    		request.setAttribute("success", "Vehicle deleted successfully!!");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}else {
    		request.setAttribute("error", "Something went wrong");
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicle-manage.jsp");
 	 		dispatcher.forward(request, response);
    	}
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action!=null) {
			if(action.equals("Login")) {
				this.login(request , response);
			}if(action.equals("Logout")) {
				this.logOut(request, response);
			}if(action.equals("Add category")) {
				this.addVehicleCategory(request, response);
			}if(action.equals("Delete")) {
				this.deleteCategory(request, response);
			}if(action.equals("Edit Category")) {
				this.editVehicleCategory(request, response);
			}if(action.equals("Register Driver")) {
				this.addNewDriver(request, response);
			}if(action.equals("Update Driver")) {
				this.editDriver(request, response);
			}if(action.equals("Add Vehicle")) {
				this.addNewVehicle(request, response);
			}if(action.equals("Update Vehicle")) {
				this.updateVehicle(request, response);
			}
		}else {
			String deleteAction = request.getParameter("actionDelete");
			if(deleteAction.equals("Delete")) {
				this.deleteVehicle(request, response);
			}
		}
		
		
		
		
	}

}
