package security.authorization;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	SecurityContext sc;
	
	@Inject
	AuthorizationTestEJB at;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Authorization</h3>");
		
		//UsernamePasswordCredential credentials = new UsernamePasswordCredential("aa", "aa");
		UsernamePasswordCredential credentials = new UsernamePasswordCredential("bb", "bb");
		sc.authenticate(request, response, AuthenticationParameters.withParams().credential(credentials));
				
		if(sc.getCallerPrincipal() == null) {
			out.println("Principal NULL");
		} else {
			out.println("Principal: " + sc.getCallerPrincipal().getName());
			
			String info = "<br/><br/>Session: "+ request.getSession(false) 
				+ "<br/>User: " + sc.getCallerPrincipal().getName()
				+ "<br/>is caller in role 'admin' -> "+sc.isCallerInRole("admin")
				+ "<br/>is caller in role 'user' -> "+sc.isCallerInRole("user");
			out.println(info);
		}
		
		at.testMethodAdmin();
	}
	
}
