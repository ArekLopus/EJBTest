package ejb.bean.stateful;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(value=600, unit=TimeUnit.SECONDS)
public class StatefulAsyncMethodTestBean {
	
	@Asynchronous
	public Future<String> testAsyncFuture() {
		 String status = "StatefulTest's testAsync(), thread -> " + Thread.currentThread().getName();
		 return new AsyncResult<String>(status);
	}
	
	@Asynchronous
	public void testAsyncVoid() {
		 System.out.println("StatefulTest's testAsync(), thread -> " + Thread.currentThread().getName());
	}
	
}
