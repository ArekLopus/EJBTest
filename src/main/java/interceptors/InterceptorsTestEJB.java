package interceptors;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Interceptors(InterceptorsTestInterceptor.class)
@Stateless
public class InterceptorsTestEJB {
	
	public InterceptorsTestEJB() {
		System.out.println("InterceptorsTestBean - constructor called, object " + this);
	}
	
	public void testMethod() {
		System.out.println("InterceptorsTestBean's testMethod() called");
	}

}
