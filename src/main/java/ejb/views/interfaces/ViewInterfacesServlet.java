package ejb.views.interfaces;

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

//If @Inject not @EGB
//https://stackoverflow.com/questions/7798595/unable-to-convert-ejbref-for-ejb
//	java.lang.IllegalStateException: Unable to convert ejbRef for ejb ViewInterfacesTestBean
//	to a business object of type interface ejb.views.InterfaceLocal

@WebServlet("/ejbViewInterfaces")
public class ViewInterfacesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
//	@Inject		//Must be @EJB here otherwise Exception above.
	ViewInterfacesTestBean lb;
	
	@EJB	
//	@Inject
	InterfaceLocal il;

	@EJB
//	@Inject				//Only @EJB
	InterfaceRemote ir;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB Intercaces View</h3>");
		
		out.println("ViewInterfacesTestBean: " + lb);
		out.println("<br/>" + lb.testMethodLocal());
		out.println("<br/>" + lb.testMethodRemote());
		out.println("<br/>" + lb.testMethodNoView());
		
		out.println("<br/><br/>InterfaceLocal: " + il);
		out.println("<br/>" + il.testMethodLocal());
		
		out.println("<br/><br/>InterfaceRemote: " + ir);
		out.println("<br/>" + ir.testMethodRemote());
		
	}
	
}
