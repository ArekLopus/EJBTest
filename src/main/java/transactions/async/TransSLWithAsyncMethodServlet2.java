package transactions.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;

import transactions.TransactionalCDIBean;
import transactions.cmt.TransSL;

// ■ Note: Client transaction context does not propagate with asynchronous method invocation (@Asynchronous) 

@WebServlet(value="/transAsyncMethod", asyncSupported=true)
public class TransSLWithAsyncMethodServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TransSL tsl;
	@Resource
	UserTransaction ut;
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Asynchronous Method Does NOT use clients transaction (Starts a new one). </h3>");

		try {
			out.println("Servlet - Before User Transaction, Transaction: " + tsr.getTransactionKey());
			out.println("<br/>Servlet - Before User Transaction, Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
			
			ut.begin();
			out.println("<br/><br/>Servlet - Transaction Begins, Transaction: " + tsr.getTransactionKey());
			out.println("<br/>Servlet - Transaction Begins, Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
			
			AsyncContext actx = request.startAsync(request, response);
			
			tsl.trans(out);
			tsl.transAsync(actx);
			
			ut.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
		
	}
}
