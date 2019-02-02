package ejb.bean.singletons;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;

@Singleton
public class SingletonAsyncMethodTestBean {
	
	@Asynchronous
	public Future<String> testAsyncFuture() {
		 String status = "SingletonAsyncMethodTestBean's testAsync(), thread -> " + Thread.currentThread().getName();
		 return new AsyncResult<String>(status);
	}
	
	@Asynchronous
	public void testAsyncVoid() {
		 System.out.println("SingletonAsyncMethodTestBean's testAsync(), thread -> " + Thread.currentThread().getName());
	}
	
}
