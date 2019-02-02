package ejb.bean.singletons;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//	Chaining Singletons 
//-In some cases, when you have several singleton beans, explicit initialization ordering can be important.
//-Dependencies can exist between multiple singletons, and the @DependsOn is there to express it.
//-The @DependsOn's value attribute is one or more strings that specify the name of the target singleton SB.
//-If more than one dependent singleton bean is specified in @DependsOn, the order in which they are listed
// is not necessarily the order in which the EJB container will initialize the target singleton SBs.
//	@Singleton  								public class CountryCodeEJB {...} 
//	@DependsOn("CountryCodeEJB")	@Singleton  public class CacheEJB {...}

//-You can also use fully qualified names to refer to a singleton packaged within a different module in the same application.
//	@Singleton
//	@DependsOn("business.jar#CountryCodeEJB")
//	public class CacheEJB {...}
//-Note that this kind of reference introduces a code dependency on packaging details (in this case, the names of the module files).

//-You can combine dependencies with startup initialization. CacheEJB is eagerly initialized at startup
// (because it holds the @Startup), and therefore CountryCodeEJB and ZipCodeEJB will also be initialized at startup before CacheEJB.
//	@Startup
//	@Singleton  
//	@DependsOn("CountryCodeEJB", "ZipCodeEJB")  
//	public class CacheEJB {...}

@DependsOn("Singletons_Chaining_Dependent")
@Startup
@Singleton
public class Singletons_Chaining {
	
	@PostConstruct
	public void init() {
		System.out.println("--- Singletons_Chaining @PostConstruct (it depends on Singletons_Chaining_Dependent) ---");
		
	}
}
