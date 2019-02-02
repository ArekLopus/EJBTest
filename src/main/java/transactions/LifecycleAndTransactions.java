package transactions;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateful
@SuppressWarnings("unused")
@Interceptors(InterceptorsTestInterceptor.class)
public class LifecycleAndTransactions {

	public void testMethod() {
		
	}
	
}
