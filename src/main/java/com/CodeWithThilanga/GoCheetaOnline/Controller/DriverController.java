package com.CodeWithThilanga.GoCheetaOnline.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CodeWithThilanga.GoCheetaOnline.Service.CustomerService;
import com.CodeWithThilanga.GoCheetaOnline.Service.DriverService;

/**
 * Servlet implementation class DriverController
 */
@WebServlet("/DriverController")
public class DriverController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DriverService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DriverController() {
		super();
		service = service.getDriverServiceInstance();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void driverLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		boolean res = service.driverLogin(email, phone);

		if (res == true) {
			HttpSession session = request.getSession(false);
			session.setAttribute("driverId", email);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-requests.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("error", "Check credentials again!!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-login.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void driverLogOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.removeAttribute("driverId");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-login.jsp");
		dispatcher.forward(request, response);
	}

	protected void processBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vid = request.getParameter("vid");
		String rid = request.getParameter("rid");
		boolean res = service.processBooking(vid , rid);
		if(res==true) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-requests.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	protected void completeBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vid = request.getParameter("vid");
		String rid = request.getParameter("rid");
		boolean res = service.completeBooking(vid , rid);
		if(res==true) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-requests.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void declineBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vid = request.getParameter("vid");
		String rid = request.getParameter("rid");
		boolean res = service.cancleBooking(vid , rid);
		if(res==true) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/driver-requests.jsp");
			dispatcher.forward(request, response);
		}
	
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("Login")) {
			this.driverLogin(request, response);
		}
		if (action.equals("Logout")) {
			this.driverLogOut(request, response);
		} else if(action.equals("accept")) {
			this.processBooking(request, response);
			System.out.println("Acceprrrrrrrrrrrrrrrr");
		}else if(action.equals("complete")) {
			System.out.println("ompleeeeeeeeeeeeeee");
			this.completeBooking(request, response);
		}else if(action.equals("decline")) {
			this.declineBooking(request, response);
		}
	}

}
