package session_context;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class SessionContextTest {

	@Resource
	SessionContext sc;
	
	SessionContext sc2;
	
	@Resource
	public void setSc2(SessionContext sc2) {
		this.sc2 = sc2;
	}



	public void testMethod(PrintWriter out) {
		out.println("sc.getRollbackOnly() -> " + sc.getRollbackOnly());
		out.println("<br/>sc2.isCallerInRole('Admin') -> " + sc2.isCallerInRole("Admin"));
	}
}
