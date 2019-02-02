package transactions;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class RollbackTransSL {

	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	@Resource
	SessionContext sc;
	
	public void trans(PrintWriter out) {
		
		out.println("Before sc.setRollbackOnly(): " + tsr.getTransactionKey());
		out.println("<br/>Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
		
		sc.setRollbackOnly();
		
		out.println("<br/><br/>After sc.setRollbackOnly(): " + tsr.getTransactionKey());
		out.println("<br/>Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
	}
	
	
}
