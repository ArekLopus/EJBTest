package ejb.bean.stateful;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateful
@StatefulTimeout(value=60, unit=TimeUnit.SECONDS)		//After 1 min -> StatefulTest's @PreDestroy
public class StatefulRemove {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public String testMethod() {
		return "StatefulTest's testMethod(), object -> " + this;
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("StatefulTest's @PostConstruct " + this);
	}
	@PreDestroy
	public void dest() {
		System.out.println("StatefulTest's @PreDestroy");
	}
	
	@Remove
	public void remove() {
		System.out.println("StatefulTest's @Remove method called");
	}
}
