package ejb.views.beans_with_same_interface;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//=The specification allows you to narrow down implementations to the specific EJB implementation.
//-You can do so by using the "beanName" attribute of the @EJB where you can specify the name of the bean 
// (which by default is the bean implementation's simple class name).

@WebServlet("/ejbTheSameInterfaces")
public class Bean_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB(beanName="BeanOne")
	InterfaceRemoteMulti ir1;
	
	@EJB(beanName="bean2")
	InterfaceRemoteMulti ir2;
	
	@EJB(beanName="BeanOne")
	InterfaceLocalMulti il1;
	
	@EJB(beanName="bean2")
	InterfaceLocalMulti il2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Two EJBs With The Same Interfaces</h3>");
		
		out.println(ir1.testMethodRemote());
		out.println("<br/>");
		out.println(ir2.testMethodRemote());
		out.println("<br/><br/>");
		
		out.println(il1.testMethodLocal());
		out.println("<br/>");
		out.println(il2.testMethodLocal());
		out.println("<br/><br/>Done");
	}
	
}
