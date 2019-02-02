package transactions.bmt;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;

//-The UserTransaction is instantiated by the EJB container and made available through
// dependency injection, JNDI lookup, or the SessionContext ( with the SessionContext.getUserTransaction() ).

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransBmtSL {

	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	@Resource
	UserTransaction ut1;
	
	@Resource
	SessionContext sc;
	
	@SuppressWarnings("unused")
	public void trans(PrintWriter out) {
		
		try {
			UserTransaction ut2 = sc.getUserTransaction();
			UserTransaction ut3 = InitialContext.doLookup("java:comp/UserTransaction");
			
			 
			out.println("Before User Transaction: " + tsr.getTransactionKey());
			ut3.begin();
			out.println("<br/><br/>Inside User Transaction: " + tsr.getTransactionKey());
			ut3.commit();
			out.println("<br/><br/>After Commit " + tsr.getTransactionKey());
			
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException | NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Asynchronous
	public void transAsync() {
		
		try {
			UserTransaction ut = sc.getUserTransaction();
			 
			System.out.println("Asynchronous method, Before User Transaction: " + tsr.getTransactionKey());
			ut.begin();
			System.out.println("Asynchronous method, Inside User Transaction: " + tsr.getTransactionKey());
			ut.commit();
			System.out.println("Asynchronous method, After Commit " + tsr.getTransactionKey());
			
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException | NotSupportedException e) {
			e.printStackTrace();
		}
	}
	
}
