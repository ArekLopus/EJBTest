package invoking_ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocal;
import ejb.views.InterfaceRemote;
import ejb.views.interfaces.annotated.ViewInterfacesAnnotatedTestBean;

@WebServlet("/invokingEJB")
public class InvokingInjectionEJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	InterfaceRemote ir;
	
	@EJB
	InterfaceLocal il;
	
	@EJB
	ViewInterfacesAnnotatedTestBean lv;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB Invoking with @EJB Injection</h3>");
		
		out.println("InterfaceRemote: " + ir.testMethodRemote());
		out.println("<br/>InterfaceLocal: " + il.testMethodLocal());
		out.println("<br/>Local View: " + lv.testMethodNoView());
		
	}
	
}
