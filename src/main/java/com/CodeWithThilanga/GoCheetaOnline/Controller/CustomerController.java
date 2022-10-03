package com.CodeWithThilanga.GoCheetaOnline.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CodeWithThilanga.GoCheetaOnline.Dao.CustomerServiceImpl;
import com.CodeWithThilanga.GoCheetaOnline.Dao.ICustomerService;
import com.CodeWithThilanga.GoCheetaOnline.Model.Customer;
import com.CodeWithThilanga.GoCheetaOnline.Model.CustomerBook;
import com.CodeWithThilanga.GoCheetaOnline.Service.CustomerService;


@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService service;
       
    public CustomerController() {
        service = CustomerService.getCustomerServiceInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Customer Simple");
	}

	
	protected void addNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		String branch = request.getParameter("branch");
		
		boolean isRegistered = false;
		
		Customer customer = new Customer(name, email , username , password , branch );
		isRegistered = service.addCustomer(customer);
		
		if(isRegistered) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-login.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "Something went wrong!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-register.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	protected void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String branch = request.getParameter("branch");
		boolean isUpdated = false;
		
		Customer customer = new Customer();
		customer.setCustomerID(id);
		customer.setCustomerName(name);
		customer.setCustomerEmail(email);
		customer.setCustomerBranch(branch);
		
		isUpdated = service.updateProfile(customer);
		
		if(isUpdated) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-profile.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "Something went wrong!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-profile.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean res = service.loginCustomer( username , password);
		
		if(res==true) {
			HttpSession session = request.getSession(false);
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-profile.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("error","Invalid username or password");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	protected void checkVehicleCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branch_id = request.getParameter("bid");
		float category_price = Float.parseFloat(request.getParameter("vehicle_type_price"));
		String vid = request.getParameter("vehicle_id");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		float cost = 0;
		
		
		if(source.equals("hikkaduwa") && destination.equals("benthota")) {
			cost = category_price + 3290; // 40 km -> 3290.00
		}else if(source.equals("hikkaduwa") && destination.equals("elpitiya")) {
			cost = category_price + 2330; //28 km -> 2330
		}else if(source.equals("koggala") && destination.equals("benthota")) {
			cost = category_price + 7300; //81 km
		}else if(source.equals("koggala") && destination.equals("elpitiya")) {
			cost = category_price + 4010; //49 km
		}else if(source.equals("digana") && destination.equals("akurana")) {
			cost = category_price + 1770; //21 km
		}else if(source.equals("digana") && destination.equals("ampitiya")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("kundasale") && destination.equals("akurana")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("kundasale") && destination.equals("ampitiya")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("dehiwala") && destination.equals("panadura")) {
			cost = category_price + 1370; //16 km
		}else if(source.equals("dehiwala") && destination.equals("homagama")) {
			cost = category_price + 1450; //17 km
		}else if(source.equals("maharagama") && destination.equals("panadura")) {
			cost = category_price + 1850; //22 km
		}else if(source.equals("maharagama") && destination.equals("homagama")) {
			cost = category_price + 810; //9 km
		}else if(source.equals("jaela") && destination.equals("katana")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("jaela") && destination.equals("minuwangoda")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("negombo") && destination.equals("katana")) {
			cost = category_price + 700; //7.5 km
		}else if(source.equals("negombo") && destination.equals("minuwangoda")) {
			cost = category_price + 970; //11 km
		}else if(source.equals("mawathagama") && destination.equals("narammala")) {
			cost = category_price + 2490; //30 km
		}else if(source.equals("mawathagama") && destination.equals("wariyapola")) {
			cost = category_price + 3290; //40 km
		}else if(source.equals("kuliyapitiya") && destination.equals("narammala")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("kuliyapitiya") && destination.equals("wariyapola")) {
			cost = category_price + 2890; //35 km
		}else if(source.equals("Chavakachcheri‎") && destination.equals("araly")) {
			cost = category_price + 2890; //35 km
		}else if(source.equals("Chavakachcheri‎") && destination.equals("Manipay")) {
			cost = category_price + 1930; //23 km
		}else if(source.equals("Alaveddy") && destination.equals("araly")) {
			cost = category_price + 1050; //12 km
		}else if(source.equals("Alaveddy") && destination.equals("Manipay")) {
			cost = category_price + 650; //7 km
		}
		
		
		request.setAttribute("price",cost );
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicles.jsp");
		dispatcher.forward(request, response);	
	}
	
	protected void bookVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		float category_price = Float.parseFloat(request.getParameter("vehicle_type_price"));
		String customer_username = (String)session.getAttribute("username");
		String customer_id =String.valueOf(service.getCurrentCustomerId(customer_username));
		String customer_branch = String.valueOf(service.getCurrentCustomerBranchId(customer_id));
		String vehicle_id = request.getParameter("vehicle_id");
		String driver_id = request.getParameter("driver_id");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		float cost = 0;
		
		
		if(source.equals("hikkaduwa") && destination.equals("benthota")) {
			cost = category_price + 3290; // 40 km -> 3290.00
		}else if(source.equals("hikkaduwa") && destination.equals("elpitiya")) {
			cost = category_price + 2330; //28 km -> 2330
		}else if(source.equals("koggala") && destination.equals("benthota")) {
			cost = category_price + 7300; //81 km
		}else if(source.equals("koggala") && destination.equals("elpitiya")) {
			cost = category_price + 4010; //49 km
		}else if(source.equals("digana") && destination.equals("akurana")) {
			cost = category_price + 1770; //21 km
		}else if(source.equals("digana") && destination.equals("ampitiya")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("kundasale") && destination.equals("akurana")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("kundasale") && destination.equals("ampitiya")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("dehiwala") && destination.equals("panadura")) {
			cost = category_price + 1370; //16 km
		}else if(source.equals("dehiwala") && destination.equals("homagama")) {
			cost = category_price + 1450; //17 km
		}else if(source.equals("maharagama") && destination.equals("panadura")) {
			cost = category_price + 1850; //22 km
		}else if(source.equals("maharagama") && destination.equals("homagama")) {
			cost = category_price + 810; //9 km
		}else if(source.equals("jaela") && destination.equals("katana")) {
			cost = category_price + 1290; //15 km
		}else if(source.equals("jaela") && destination.equals("minuwangoda")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("negombo") && destination.equals("katana")) {
			cost = category_price + 700; //7.5 km
		}else if(source.equals("negombo") && destination.equals("minuwangoda")) {
			cost = category_price + 970; //11 km
		}else if(source.equals("mawathagama") && destination.equals("narammala")) {
			cost = category_price + 2490; //30 km
		}else if(source.equals("mawathagama") && destination.equals("wariyapola")) {
			cost = category_price + 3290; //40 km
		}else if(source.equals("kuliyapitiya") && destination.equals("narammala")) {
			cost = category_price + 1690; //20 km
		}else if(source.equals("kuliyapitiya") && destination.equals("wariyapola")) {
			cost = category_price + 2890; //35 km
		}else if(source.equals("Chavakachcheri‎") && destination.equals("araly")) {
			cost = category_price + 2890; //35 km
		}else if(source.equals("Chavakachcheri‎") && destination.equals("Manipay")) {
			cost = category_price + 1930; //23 km
		}else if(source.equals("Alaveddy") && destination.equals("araly")) {
			cost = category_price + 1050; //12 km
		}else if(source.equals("Alaveddy") && destination.equals("Manipay")) {
			cost = category_price + 650; //7 km
		}
		
		
		
		CustomerBook customerBook = new CustomerBook(customer_branch , customer_id , driver_id , vehicle_id , cost , source , destination);
		boolean res = service.bookVehicle(customerBook);
		
		if(res) {
			request.setAttribute("process", "Your booking is processing...");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vehicles.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	
	protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("username");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void processFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookingId");
		request.setAttribute("bookId" , bookId);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/customer-feedback.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void submitFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String customer_username = (String)session.getAttribute("username");
		String customer_id =String.valueOf(service.getCurrentCustomerId(customer_username));
		String feedback = request.getParameter("feedback");
		String bookingId = request.getParameter("bid");
		boolean res = service.addFeedback(feedback, bookingId , customer_id);
		if(res==true) {
			request.setAttribute("sucess", "Feedback submitted successfully!!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/my-bookings.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("Register Now")) {
			this.addNewCustomer(request, response);
		}else if(action.equals("Login")) {
			this.loginCustomer(request, response);
		}else if(action.equals("Logout")) {
			this.logOut(request, response);
		}else if(action.equals("Update")) {
			this.updateProfile(request, response);
		}else if(action.equals("book")) {
			this.bookVehicle(request, response);
		}else if(action.equals("check cost")) {
			this.checkVehicleCost(request, response);
		}else if(action.equals("give feedback")) {
			this.processFeedback(request, response);
		}else if(action.equals("submit feedback")) {
			this.submitFeedback(request, response);
		}
	}

}
