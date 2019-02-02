package asynchronous_method;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.servlet.AsyncContext;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransFromClientBean {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void syncTransaction(PrintWriter out) {
		System.out.println("syncTransaction(): " + tsr.getTransactionKey());
		out.println("<br/><br/>syncTransaction(): " + tsr.getTransactionKey());
	}

	@Asynchronous
	public void asyncTransaction(AsyncContext ac) {
		System.out.println("asyncTransaction(): " + tsr.getTransactionKey());
		
		try {
			PrintWriter out = ac.getResponse().getWriter();
			out.println("<br/><br/>asyncTransaction(): " + tsr.getTransactionKey());
			ac.complete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
