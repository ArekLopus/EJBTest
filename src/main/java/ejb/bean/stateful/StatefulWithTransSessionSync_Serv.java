package ejb.bean.stateful;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transSessionSync")
public class StatefulWithTransSessionSync_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	StatefulWithTransSessionSync tsfws;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Transaction SF SB With SessionSynchronization</h3>");
		out.println("A stateful SB instance is not required to commit a started transaction before a business method or interceptor method returns.");
		out.println("<br/>The SessionSynchronization interface allows a stateful SB instance to be notified by its container of transaction boundaries.");
		out.println("<br/><br/>Only a stateful session bean with CONTAINER-managed transaction demarcation can receive session synchronization notifications.");
		
		tsfws.trans();
		
	}
}
