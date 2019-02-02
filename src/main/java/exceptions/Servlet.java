package exceptions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

import exceptions.application.AppExceptionException;

@Transactional
@WebServlet("/exceptions")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	AppExceptionTestEJB et;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Hello World from Servlet!</h3>");
		
		//et.excRuntime();
		//et.appExcTestRuntime();
		try {
			et.appExcRollbackTest();
		} catch (AppExceptionException e) {
			out.println("Exception: " + e.getMessage());
		}
		
		out.println("<br/>" + tsr.getTransactionKey());
		out.println("<br/>" + tsr.getRollbackOnly());
		
	}
	
}
