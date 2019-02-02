package transactions;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionSynchronizationRegistry;

@Interceptor
public class InterceptorsTestInterceptor {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		System.out.println("@AroundInvoke, trans: " + tsr.getTransactionKey());
		return ctx.proceed();
	}
	
	@AroundConstruct
	private Object testAroundConstruct(InvocationContext ctx) throws Exception {
		System.out.println("@AroundConstruct, trans: " + tsr.getTransactionKey());
		return ctx.proceed();
	}
	
	@PostConstruct
	private Object init(InvocationContext ctx) throws Exception {
		System.out.println("@PostConstruct, trans: " + tsr.getTransactionKey());
		return ctx.proceed();
	}
	@PreDestroy
	private Object destroy(InvocationContext ctx) throws Exception {
		System.out.println("@PreDestroy, trans: " + tsr.getTransactionKey());
		return ctx.proceed();
	}
	
}
