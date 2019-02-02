package ejb.bean.stateful.passivate;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.transaction.TransactionSynchronizationRegistry;

import transactions.TransactionalCDIBean;

@Stateful 
@StatefulTimeout(unit = TimeUnit.SECONDS, value = 180) 
public class SFPassivActiv {
	
	@Resource
	private SessionContext ctx;
	
	@Resource
	transient private TransactionSynchronizationRegistry tsr;		
	
	//After activate TransactionSynchronizationRegistry is null!!!
	public void trans() {
		System.out.println("SB SF, Transaction: " + tsr.getTransactionKey());
		System.out.println("Transaction Status: " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
		
		tsr.setRollbackOnly();
		System.out.println("Transaction Status after setRollbackOnly(): " + TransactionalCDIBean.getTransactionStatus(tsr.getTransactionStatus()));
	}
	
	public void checkTrans() {
		System.out.println("SB SF Testing");
		System.out.println("TransactionSynchronizationRegistry exists? -> " + tsr);
		System.out.println("ctx.getRollbackOnly() -> " + ctx.getRollbackOnly());
	}
	
	public void simpleCall() {
		System.out.println("SB SF Testing");
	}
	
	@PrePassivate
	private void prePasiv() {
		System.out.println("@PrePassivate method called");
	}
	@PostActivate
	private void postActv() {
		System.out.println("@PostActivate method called");
	}

	
}
