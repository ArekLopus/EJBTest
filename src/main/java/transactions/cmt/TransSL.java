package transactions.cmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.AsyncContext;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransSL {

	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	//Default
	public void trans() {
		System.out.println("SL SB, Synchronous method, Transaction: " + tsr.getTransactionKey());
	}
	public void trans(PrintWriter out) {
		out.println("<br/><br/>SL SB, Synchronous method, Transaction: " + tsr.getTransactionKey());
	}
	
	
	@Asynchronous
	public void transAsync() {
		String name = Thread.currentThread().getName();
		System.out.println("SL SB, Asynchronous method, thread: " + name);
		System.out.println("SL SB, Asynchronous method, Transaction: " + tsr.getTransactionKey());
	}
	@Asynchronous
	public void transAsync(PrintWriter out) {
		printAsyncInfo(out);
	}
	@Asynchronous
	public void transAsync(AsyncContext actx) {
		try {
			PrintWriter out = actx.getResponse().getWriter();
			printAsyncInfo(out);
			actx.complete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void printAsyncInfo(PrintWriter out) {
		String name = Thread.currentThread().getName();
		out.println("<br/><br/><br/>SL SB, Async method, thread: " + name);
		out.println("<br/>SL SB, Async method, Transaction: " + tsr.getTransactionKey());
	}
}
