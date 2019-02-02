package ejb.bean.singletons;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//	Startup Initialization 
//-Sometimes initializing a singleton can be time-consuming. Imagine if CacheEJB needs to access a DB to load its cache
// with 1000s of objects. The first client will have to wait for initialization to be completed.
//-To avoid such latency, you can ask the container to initialize a singleton bean at startup. 
//-If the @Startup appears on the bean class, the container initializes it during the app startup, not when a client invokes it.

@Startup
@Singleton
public class Singleton_Startup_Initialization {
	
	@PostConstruct
	public void init() {
		System.out.println("--- Singleton Startup Initialization @PostConstruct ---");
	}
	
}
