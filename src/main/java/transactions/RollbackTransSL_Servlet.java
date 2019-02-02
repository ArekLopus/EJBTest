package transactions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-It is important to stress that a CMT bean is not allowed to roll back the transaction explicitly.
// Instead, you need to use the EJB context  to inform the container to roll back. ( @Resource SessionContext.setRollbackOnly() )
//-Calling this method doesn’t roll back the transaction immediately; instead, a flag is set for the container to do the
// actual rollback when it is time to end the transaction. Only session beans with CMT demarcation can use this method 
//-BMT session beans roll back transactions directly ( Usertransaction.rollback() ) or use Usertransaction.setRollback().

@WebServlet("/transRollback")
public class RollbackTransSL_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	RollbackTransSL tsl;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Rollback Transaction</h3>");
		
		out.println("CMT bean is not allowed to roll back the transaction explicitly");
		out.println("<br/>Instead, a flag is set using SessionContext.setRollbackOnly() for the container to do the actual rollback when it is time to end the transaction.");
		out.println("<br/>BMT session beans roll back transactions directly ( UserTransaction.rollback() ) or use UserTransaction.setRollback().");
		out.println("<br/><br/><br/>");
		
		tsl.trans(out);
		
	}
}
