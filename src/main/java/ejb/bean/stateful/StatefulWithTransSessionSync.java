package ejb.bean.stateful;

import javax.annotation.Resource;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.BeforeCompletion;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.TransactionSynchronizationRegistry;

//-A stateful SB instance is not required to commit a started transaction before a business method or interceptor method returns.

//-Only a stateful session bean with CONTAINER-managed transaction demarcation can receive session synchronization notifications.
//-There is no need for a session bean with bean-managed transaction demarcation to rely on the synchronization call backs
// because the bean is in control of the commit—the bean knows when the transaction is about to be committed and
// it knows the outcome of the transaction commit.

//-The SessionSynchronization interface allows a stateful SB instance to be notified by its container of transaction boundaries.
//-Other bean types must not implement the SessionSynchronization interface or use the session synchronization annotations.

@Stateful
public class StatefulWithTransSessionSync { //implements SessionSynchronization {
	//private static final long serialVersionUID = 1L;
	
	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void trans() {
		System.out.println("SF SB method called, Transaction: " + tsr.getTransactionKey());
	}

//	@Override
//	public void afterBegin() throws EJBException, RemoteException {
//		System.out.println("Transaction: AfterBegin");
//	}
//
//	@Override
//	public void beforeCompletion() throws EJBException, RemoteException {
//		System.out.println("Transaction: BeforeCompletion");
//		
//	}
//
//	@Override
//	public void afterCompletion(boolean committed) throws EJBException, RemoteException {
//		System.out.println("Transaction: AfterCompletion, committed: " + committed);
//	}
	
	
	@AfterBegin
	private void afterBegin() {
		System.out.println("Transaction: AfterBegin");
	}
	@BeforeCompletion
	private void beforeCompletion() {
		System.out.println("Transaction: BeforeCompletion");
	}
	@AfterCompletion
	private void afterCompletion(boolean committed) {
		System.out.println("Transaction: AfterCompletion, committed: " + committed);
	}
}
