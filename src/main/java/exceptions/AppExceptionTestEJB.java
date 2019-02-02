package exceptions;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionSynchronizationRegistry;

import exceptions.application.AppExceptionException;
import exceptions.application.AppExceptionRuntime;

@Stateless
public class AppExceptionTestEJB {
	
	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	public static int counter = 0;
	
	public String appExcTest() throws AppExceptionException {
		counter++;
		if((counter % 2) == 0) {
			throw new AppExceptionException("MyExceptionTest" + ", trans: " + tsr.getTransactionKey() );
		}
		return "appExcTest() called, no Excepton" + ", trans: " + tsr.getTransactionKey();
	}
	
	public String appExcRollbackTest() throws AppExceptionException {
		counter++;
		if((counter % 2) == 0) {
			throw new AppExceptionException("MyExceptionTest" + ", trans: " + tsr.getTransactionKey() );
		}
		return "appExcTest() called, no Excepton" + ", trans: " + tsr.getTransactionKey();
	}
	
	public String appExcTestRuntime() {
		counter++;
		if((counter % 2) == 0) {
			throw new AppExceptionRuntime("MyExceptionRuntimeTest" + ", trans: " + tsr.getTransactionKey());
		}
		return "appExcTestRuntime() called, no Excepton" + ", trans: " + tsr.getTransactionKey();
	}
    
	public String excRuntime() {
		counter++;
		if((counter % 2) == 0) {
			throw new RuntimeException("RuntimeException" + ", trans: " + tsr.getTransactionKey());
		}
		return "excRuntime() called, no Excepton" + ", trans: " + tsr.getTransactionKey();
	}
	
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {

		Object proceed = null;
		
		try {
			proceed = ctx.proceed();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw e;
		} finally {

		}
		System.out.println("after method invocation" + ", trans: " + tsr.getTransactionKey());
		System.out.println("is rollback " + tsr.getRollbackOnly());
		
		
		return proceed;
	}
}