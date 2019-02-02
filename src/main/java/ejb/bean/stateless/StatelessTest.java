package ejb.bean.stateless;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

//-The @Stateless  marks the ItemEJB POJO as a SL SB, thus turning a simple Java class into a container-aware component.
// • name parameter specifies the name of the bean and by default is the name of the class (ItemEJB in the example).
//   This parameter can be used to look up an EJB with JNDI, for example.
// • description parameter is a String that can be used to describe the EJB.
// • mappedName attribute is the global JNDI name assigned by the container. Note that this JNDI name is vendor specific and is
//   therefore not portable. mappedName has no relationship with the portable global JNDI name, described earlier.

// • method-level transaction management and security,
// • asynchronous methods,
// • concurrency management,
// • instance-level passivation for stateful session beans,
// • instance-pooling for stateless session beans,
// • remote or web service invocation,
// • timers,

@Stateless
public class StatelessTest {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public String testMethod() {
		return "StatelessTest's testMethod(), object -> " + this;
	}
	
	public String testTransaction() {
		return "StatelessTest's testTransaction(), transaction -> " + tsr.getTransactionKey();
	}
	
	@Asynchronous
	public Future<String> testAsync() {
		 String status = "StatelessTest's testAsync(), thread -> " + Thread.currentThread().getName();
		 return new AsyncResult<String>(status);
	}
	
	
	@PostConstruct
	private void init() {
		System.out.println("StatelessTest's @PostConstruct " + this);
	}
	
	@PreDestroy
	private void dest() {
		System.out.println("StatelessTest's @PreDestroy");
	}

}
