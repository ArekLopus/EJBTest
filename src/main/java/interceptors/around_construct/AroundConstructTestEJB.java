package interceptors.around_construct;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Interceptors(AroundConstructInterceptor.class)
@Stateless
public class AroundConstructTestEJB {
	
	public AroundConstructTestEJB() {
		System.out.println("AroundConstructEJB - constructor called, object: " + this);
	}
	
	public void testMethod() {
		System.out.println("AroundConstructEJB's testMethod() called");
	}
	
	@PostConstruct
	private void init() {
		System.out.println("AroundConstructEJB's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundConstructEJB's @PreDestroy");
	}
}
