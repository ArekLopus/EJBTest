package asynchronous_method;

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
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

import transactions.TransactionalCDIBean;

//	Transactions
//-The client’s transaction context does not propagate with an asynchronous method invocation.
//-From the Bean Provider’s point of view, there is never a transaction context flowing in from the client. This means, fe,
// that the semantics of the REQUIRED transaction attribute on an asynchronous method are exactly the same as REQUIRES_NEW.

@WebServlet(value="/asynchronousTrans", asyncSupported=true)
@Transactional
public class TransFromClient_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TransFromClientBean at;

	@Resource
	TransactionSynchronizationRegistry tsr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Asynchronous Method Does NOT use clients transaction (Starts a new one). </h3>");

		out.println("Servlet, Transaction: " + tsr.getTransactionKey());
		out.println("<br/>Servlet, Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
		
		AsyncContext ac = request.startAsync(request, response);
		
		at.syncTransaction(out);
		at.asyncTransaction(ac);
	}
}
