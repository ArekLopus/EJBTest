package invoking_ejb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.views.InterfaceLocal;
import ejb.views.InterfaceRemote;
import ejb.views.interfaces.annotated.ViewInterfacesAnnotatedTestBean;

//-SBs can also be looked up using JNDI. JNDI is mostly used for remote access
// when a client is not container managed and can't use DI. 
//-But JNDI can also be used by local clients, even if DI results in simpler code. Injection is made at deployment time. If there
// is a chance that the data will not be used, the bean can avoid the cost of resource injection by performing a JNDI lookup.

@WebServlet("/invokingJNDI")
public class InvokingLookupJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>EJB Invoking with JNDI</h3>");

		try {
			Context ctx = new InitialContext();
			
			InterfaceRemote ir = (InterfaceRemote) ctx.lookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceRemote");
			InterfaceLocal il = InitialContext.doLookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceLocal");
			ViewInterfacesAnnotatedTestBean lv = InitialContext.doLookup("java:global/EJBTest/ViewInterfacesAnnotatedTestBean!ejb.views.interfaces.annotated.ViewInterfacesAnnotatedTestBean");
			
			out.println("InterfaceRemote: " + ir.testMethodRemote());
			out.println("<br/>InterfaceLocal: " + il.testMethodLocal());
			out.println("<br/>Local View: " + lv.testMethodNoView());
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
}
