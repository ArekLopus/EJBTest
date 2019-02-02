package interceptors.around_invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Stateless
public class AroundInvokeEJB {
	
	public String aroundInvokeTest(String first, String second) {
		System.out.println("aroundInvokeTest() called");
		return "aroundInvokeTest() called";
	}
	
	@AroundInvoke
	private Object testAroundInvoke(InvocationContext ctx) throws Exception {
		System.out.println("----- @AroundInvoke interceptor method called -----");
		
		Constructor<?> constructor = ctx.getConstructor();
		Map<String, Object> contextData = ctx.getContextData();
		Method method = ctx.getMethod();
		Object[] parameters = ctx.getParameters();
		Object target = ctx.getTarget();
		
		System.out.println("Constructor: " + constructor);
		System.out.println("ContextData :" + contextData);
		System.out.println("Method :" + method);
		System.out.println("Parameters :" + Arrays.asList(parameters));
		System.out.println("target instanceof AroundInvokeTest: " + (target instanceof AroundInvokeEJB));
		
		return ctx.proceed();
	}
	
	@PostConstruct
	private void init() {
		System.out.println("AroundInvokeEJB's @PostConstruct");
	}
	@PreDestroy
	private void destroy() {
		System.out.println("AroundInvokeEJB's @PreDestroy");
	}
}
