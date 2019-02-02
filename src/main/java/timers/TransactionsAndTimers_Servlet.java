package timers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-An EB usually creates a timer within a transaction. If this transaction is rolled back, the timer creation also is rolled back.
//-Similarly, if a bean cancels a timer within a transaction that gets rolled back, the timer cancellation is rolled back.
// In this case, the timer's duration is reset as if the cancellation had never occurred.
//-In beans that use container-managed transactions, the @Timeout method usually has the Required or RequiresNew transaction attr
// to preserve transaction integrity. With these attributes, the EJB container begins the new transaction before calling the
// @Timeout method. If the transaction is rolled back, the container will call the @Timeout method at least one more time.

@WebServlet("/timersTransactions")
public class TransactionsAndTimers_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TransactionsAndTimers tt;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Timers And Transactions</h3>");
		out.println("An EB usually creates a timer within a transaction. If this transaction is rolled back, the timer creation also is rolled back.");
		out.println("<br/>If a bean cancels a timer within a transaction that gets rolled back, the timer cancellation is rolled back.");
		out.println("<br/>The EJB container begins the new transaction before calling the @Timeout method");
		
		tt.intervalTimer();
		
	}
	
}
