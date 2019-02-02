package ejb.bean.singletons;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class Singletons_Chaining_Dependent {
	
	@PostConstruct
	public void init() {
		System.out.println("--- Singletons_Chaining_Dependent @PostConstruct ---");
	}
	
}
