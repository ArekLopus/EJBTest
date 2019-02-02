package asynchronous_method;

import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

//-An asynchronous method with return type void must not declare any application exceptions. 

//-Note that the Future<V> object returned from the bean class method (including any instance of AsyncResult<V>) is 
// only used as a way to pass the result value to the container. This object is not given directly to the caller,
// since by definition the caller already has a container-generated Future<V>object that was returned from the original invocation.

@Stateless
public class AsyncTestBean {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Asynchronous
	public Future<String> asyncMethodFuture() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<String>("@Asynchronous method Future, thread: " + Thread.currentThread().getName());
	}
	
	@Asynchronous
	public void asyncMethodVoid() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("@Asynchronous method void, thread: " + Thread.currentThread().getName());
	}

}
