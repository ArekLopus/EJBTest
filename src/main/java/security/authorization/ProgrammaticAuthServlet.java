package security.authorization;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-Declarative authenticaton covers most security cases needed by an app. But sometimes you need a finer grain of authorizing access
// (allowing a block of code instead of the entire method, permitting or denying access to an individual instead of a role, etc.).
//-You can use programmatic authorization to selectively permit or block access to a role or a principal. That’s because you have
// direct access to the JAAS java.security.Principal interface, as well as the EJB context to check the principal’s role in the code. 

//-The SessionContext interface defines the following methods related to security:
// • isCallerInRole(): This method returns a boolean and tests whether the caller has a given security role. 
// • getCallerPrincipal(): This method returns the java.security.Principal that identifies the caller.

//http://localhost:8080/EJBTest/authorizationProg?name=aa
//http://localhost:8080/EJBTest/authorizationProg?name=bb
@WebServlet("/authorizationProg")
public class ProgrammaticAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	SecurityContext sc;
	
	@Inject
	AuthorizationTestEJB at;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Programmatic Authorization</h3>");
		
		UsernamePasswordCredential credentials = null;
		String param = request.getParameter("name");
		
		if(request.getSession(false) != null) 
			request.getSession(false).invalidate();
		
		if(param != null && param.equals("aa")) {
			credentials = new UsernamePasswordCredential("aa", "aa");
		} else if (param != null && param.equals("bb")) {
			credentials = new UsernamePasswordCredential("bb", "bb");
		} else {
			credentials = new UsernamePasswordCredential("cc", "cc");
		}
		sc.authenticate(request, response, AuthenticationParameters.withParams().credential(credentials));

		Principal callerPrincipal = sc.getCallerPrincipal();
		
		if(callerPrincipal == null) {
			out.println("Principal NULL");
		} else {
			
			String name = callerPrincipal.getName();
			boolean callerInRoleAdmin = sc.isCallerInRole("admin");
			boolean callerInRoleUser = sc.isCallerInRole("user");
			
			out.println("Principal: " + callerPrincipal.getName());
			
			String info = "<br/><br/>Session: "+ request.getSession(false) 
				+ "<br/>User: " + name
				+ "<br/>is caller in role 'admin' -> " + callerInRoleAdmin
				+ "<br/>is caller in role 'user' -> " + callerInRoleUser;
			out.println(info);
		}
		
		at.testPermitAll();
	}
	
}
