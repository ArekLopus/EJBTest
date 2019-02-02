package ejb.bean.stateful;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateful
@StatefulTimeout(value=600, unit=TimeUnit.SECONDS)
public class StatefulTestBean {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public String testMethod() {
		return "StatefulTest's testMethod(), object -> " + this;
	}
	
	public String testTransaction() {
		return "StatefulTest's testTransaction(), transaction -> " + tsr.getTransactionKey();
	}
	
	@PostConstruct
	public void init() {
		System.out.println("StatefulTest's @PostConstruct " + this);
	}
	@PreDestroy
	public void dest() {
		System.out.println("StatefulTest's @PreDestroy");
	}
	
	
}
