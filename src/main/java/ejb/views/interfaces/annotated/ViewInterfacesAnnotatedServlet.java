package ejb.views.interfaces.annotated;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocalWithAnnotation;
import ejb.views.InterfaceRemoteWithAnnotation;

//@Remote nad @Local are on interfaces

//If @Inject not @EGB
//https://stackoverflow.com/questions/7798595/unable-to-convert-ejbref-for-ejb
//	java.lang.IllegalStateException: Unable to convert ejbRef for ejb ViewInterfacesAnnotatedTestBean
//	to a business object of type interface ejb.views.InterfaceLocalWithAnnotation

@WebServlet("/ejbViewInterfacesAnnotated")
public class ViewInterfacesAnnotatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
//	@Inject		//Must be @EJB here otherwise Exception above.
	ViewInterfacesAnnotatedTestBean lb;
	
	@EJB
//	@Inject
	InterfaceLocalWithAnnotation il;
	
	@EJB
//	@Inject				//Only @EJB
	InterfaceRemoteWithAnnotation ir;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB Annotated Interfaces View</h3>");
		
		out.println("ViewInterfacesAnnotatedTestBean: " + lb);
		out.println("<br/>" + lb.testMethodLocal());
		out.println("<br/>" + lb.testMethodRemote());
		out.println("<br/>" + lb.testMethodNoView());
		
		out.println("<br/><br/>InterfaceLocalWithAnnotation: " + il);
		out.println("<br/>" + il.testMethodLocal());
		
		out.println("<br/><br/>InterfaceRemoteWithAnnotation: " + ir);
		out.println("<br/>" + ir.testMethodRemote());
		
	}
	
}
