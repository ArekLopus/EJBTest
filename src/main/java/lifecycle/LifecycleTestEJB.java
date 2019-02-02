package lifecycle;

import javax.enterprise.context.Dependent;
import javax.interceptor.Interceptors;

import interceptors.InterceptorsTestInterceptor;

@Dependent
@Interceptors(InterceptorsTestInterceptor.class)
public class LifecycleTestEJB {

	public LifecycleTestEJB() {
		System.out.println("LifecycleEJB's constructor");
	}
	
	
	public void callMe() {
		System.out.println("LifecycleEJB's callMe()");
	}
}
