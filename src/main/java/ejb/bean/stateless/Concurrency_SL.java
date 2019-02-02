package ejb.bean.stateless;

import javax.ejb.Stateless;

//-@ConcurrencyManagement and @Lock are only available on singleton session beans.

@Stateless
public class Concurrency_SL {

	public void testMethod() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Sleep finished: Thread -> " + Thread.currentThread().getName() + ", Object -> " + this);
	}
	
//	@Lock(LockType.READ)		//Container ->   @Lock is only permitted for singleton session beans]]
//	public void testMethodWithLockRead() {}
	
//	@Lock(LockType.WRITE)		//Container ->   @Lock is only permitted for singleton session beans]]
//	public void testMethodWithLockWrite() {}
	
	
	//For tests
	public String testMethodTests() {
		return "Thread -> " + Thread.currentThread().getName() + ", Object -> " + this;
	}
}
