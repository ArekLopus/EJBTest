package ejb.bean.singletons;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.Singleton;

//-!Fails sometimes if method is only Thread.sleep()!
//-@AccessTimeout - a timeout can be specified to reject a request if the lock is not acquired within a certain time.
//-This annotation may be applied to a stateful session bean or to a singleton session bean that uses container managed concurrency.
// • value > 0  -> indicates a timeout value in the units specified by the unit element.
// • value = -1 -> the client request will block indefinitely until forward progress can be made.
// • value = 0  -> concurrent access is not allowed. This will result in throwing a ConcurrentAccessException if a client invokes a method that is currently being used.

//@AccessTimeout(value=0, unit=TimeUnit.SECONDS)
//	javax.ejb.ConcurrentAccessException: Couldn't acquire a lock within 0 SECONDS

//@AccessTimeout(value=1, unit=TimeUnit.SECONDS)
//	javax.ejb.ConcurrentAccessTimeoutException: Couldn't acquire a lock within 1 SECONDS

@Singleton
@AccessTimeout(value=1000, unit=TimeUnit.MILLISECONDS)
public class Concurrency_AccessTimeout {
	
	private BigInteger bi = BigInteger.ZERO;
	
	public void testMethod() {
		
		long start = System.currentTimeMillis();
		
		for(long i=0; i<30000000; i++) {
			bi = bi.add(new BigInteger(""+i));
		}
		
		long end = System.currentTimeMillis() - start;
		
		System.out.println("Time (ms) " + end);
		System.out.println("Done");
	}
	
//	public void testMethod() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
//	}
	
}
