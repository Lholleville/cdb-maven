package main.java.edu.excilys.cdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.edu.excilys.cdb.service.ComputerService;


public class HomeServlet extends HttpServlet {

	private ComputerService computerService = ComputerService.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long nbComputers = computerService.getNbComputers();
		
		request.setAttribute("nbComputers", nbComputers);

		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/views/dashboard.jsp" ).forward( request, response );
	}
}
