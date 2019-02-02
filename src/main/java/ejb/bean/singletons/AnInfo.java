package ejb.bean.singletons;

//		@Singleton
// • name parameter specifies the name of the bean and by default is the name of the class (ItemEJB in the example).
//   This parameter can be used to look up an EJB with JNDI, for example.
// • description parameter is a String that can be used to describe the EJB.
// • mappedName attribute is the global JNDI name assigned by the container. Note that this JNDI name is vendor specific
//   and is therefore not portable. mappedName has no relationship with the portable global JNDI name.

//		Startup Initialization 
//-Sometimes initializing a singleton can be time-consuming. Imagine if CacheEJB needs to access a DB to load its cache
// with 1000s of objects. The first client will have to wait for initialization to be completed.
//-To avoid such latency, you can ask the container to initialize a singleton bean at startup. 
//-If the @Startup appears on the bean class, the container initializes it during the app startup, not when a client invokes it.
//	@Singleton
//	@Startup
//	public class CacheEJB {...} 

//		Chaining Singletons 
//-In some cases, when you have several singleton beans, explicit initialization ordering can be important.
//-Dependencies can exist between multiple singletons, and the @DependsOn is there to express it.
//-The @DependsOn's value attr is one or more strings that specify the name of the target singleton SB.
//-If more than one dependent singleton bean is specified in @DependsOn, the order in which they are listed
// is not necessarily the order in which the EJB container will initialize the target singleton SBs.
//	@Singleton  
//	public class CountryCodeEJB {...} 
//
//	@DependsOn("CountryCodeEJB")  
//	@Singleton  public class CacheEJB {...}

//-You can also use fully qualified names to refer to a singleton packaged within a different module in the same application.
//	@Singleton
//	@DependsOn("business.jar#CountryCodeEJB")
//	public class CacheEJB {...}
//-Note that this kind of reference introduces a code dependency on packaging details (in this case, the names of the module files).

//-You can combine dependencies with startup initialization. CacheEJB is eagerly initialized at startup
//(because it holds the @Startup), and therefore CountryCodeEJB and ZipCodeEJB will also be initialized at startup before CacheEJB.
//	@Startup
//	@Singleton  
//	@DependsOn("CountryCodeEJB", "ZipCodeEJB")  
//	public class CacheEJB {...}


//		Managing Concurrent Access
//-There is only one instance of a singleton session bean shared by multiple clients.
// So concurrent access by clients is allowed and can be controlled with the @ConcurrencyManagement in two different ways.
// • Container-managed concurrency (CMC): The container controls concurrent access to the bean instance based on metadata
//   (@Lock( or the XML equivalent).
// • Bean-managed concurrency (BMC): The container allows full concurrent access and the bean is responsible for synchronization.
//   (sybchronized, volatile).

//-If no concurrency management is specified, the CMC is used by default. A singleton bean can use either CMC or BMC, but not both.

//-@AccessTimeout - a timeout can be specified to reject a request if the lock is not acquired within a certain time.. 
// • value > 0  -> indicates a timeout value in the units specified by the unit element.
// • value = -1 -> the client request will block indefinitely until forward progress can be made.
// • value = 0  -> concurrent access is not allowed. This will result in throwing a ConcurrentAccessException if a client invokes a method that is currently being used.

//	Container-Managed Concurrency
//-With CMC, the default demarcation, the container is responsible for controlling concurrent access to the singleton bean instance.
//-You can then use the @Lock to specify how the container must manage concurrency when a client invokes a method. 

//@Lock
//-Annotating a singleton class with @Lock specifies that all the business and timeout methods will use the specified lock type
// unless they explicitly set the lock type with a method-level @Lock.
//-If no @Lock is present on the singleton class, the default lock type, @Lock(LockType.WRITE), is applied to all business and timeout methods.
//-Annotate a method with @Lock(LockType.READ) if the method can be concurrently accessed, or shared, with many clients. 
//-Annotate with @Lock(LockType.WRITE) if the singleton SB should be locked to other clients while a client is calling that method.
//-Typically, the @Lock(LockType.WRITE) is used when clients are modifying the state of the singleton.

//	Bean-Managed Concurrency
//-With BMC demarcation, the container allows full concurrent access to the singleton bean instance.
//-You are responsible for guarding its state against synchronization errors due to concurrent access.
// You are allowed to use Java synchronization primitives such as synchronized and volatile.


// Handling Errors in a Singleton Session Bean
//-If a singleton session bean encounters an error when initialized by the EJB container,
// that singleton instance will be destroyed.
//-Unlike other EBs, once a singleton instance is initialized, it is not destroyed if the singleton's business or lifecycle
// methods cause system exceptions. This ensures that the same singleton instance is used throughout the app lifecycle.


//		Business Methods
//-The primary purpose of a session bean is to run business tasks for the client. The client invokes business methods on the obj
// reference it gets from DI or JNDI lookup. From the client's perspective, the business methods appear to run locally.

//-The signature of a business method must conform to these rules.
// • The method name must not begin with ejb..., to avoid conflicts with callback methods defined by the EJB architecture.
// • The access control modifier must be public.
// • If the bean allows remote access through a remote business interface, the arguments and return types must be legal RMI API types.
// • If the bean is a JAX-WS WS endpoint, the arguments, return types for the methods @ed @WebMethod must be leg. JAX-WS  types.
// • If the bean is a JAX-RS resource, the arguments and return types for the resource methods must be legal types for JAX-RS.
// • The modifier must not be static or final.
//-The throws clause can include exceptions that you define for your app.
//-To indicate a system-level problem, such as the inability to connect to a DB, a business method should throw a EJBException. 
// The container will not wrap application exceptions, such as BookException.
// Because EJBException is a subclass of RuntimeException, you dont need to include it in the throws clause of the business method.


//		Session Beans Life Cycle
//-The container is responsible for managing the life cycle of the bean. 
//-Life cycle means that a session bean goes through a predefined set of state transitions.
// Depending on the type of your bean (SL, SF , singleton), the life cycle will consist of different states.
//-Each time the container changes the lifecycle state, it can invoke methods that are annotated with callback annotations.
//-All SBs have 2 obvious phases in their life cycle: creation and destruction.
//-SF SBs also go through the passivation and activation phases
//-Similar to the callback methods used in entities, EJBs allow the container, during certain phases of its life,
// to automatically invoke annotated methods (@PostConstruct, @PreDestroy, etc.).
// These methods may initialize state information on the bean, look up resources using JNDI, or release database connections. 

//Stateless  and  Singleton
//-Stateless and singleton beans have in common the fact that they don’t maintain conversational state with a dedicated client.
//-Both bean types allow access by any client — stateless does this on a per-instance basis,
// while singleton provides concurrent access to a single instance.

//-Both share the same life cycle:
// 1. The life cycle of a SL or singleton SB starts when a client requests a reference to the bean (using DI or JNDI lookup). In the case of a singleton, it can also start when the container is bootstrapped (using the @Startup ). The container creates a new SB instance.
//    - JEE 7 Added @AroundConstruct callback called before the constructor.
// 2. If the newly created instance uses DI through annotations (@Inject, @Resource, @EJB, @PersistenceContext, etc.) or DDs, the container injects all the needed resources. 
// 3. If the instance has a method annotated with @PostContruct, the container invokes it. 
// 4. The bean instance processes the call invoked by the client and stays in ready mode to process more calls.
//    - SL beans stay in ready mode til the container frees some space in the pool.
//    - Singletons stay in ready mode until the container is shut down. 
// 5. The container doesnt need the instance anymore. invokes the method @ed @PreDestroy, if any, and ends the life of the bean inst.

//-SL and singleton beans share the same life cycle, but there are some differences in the way they are created and destroyed. 
//-When you deploy a SL SB, the container creates several instances and adds them into a pool. When a client calls a method
// on a SL SB, the container selects one instance from the pool, delegates the method invocation to that instance,
// and returns it to the pool. When the container no longer needs the instance
// (usually when the container wants to reduce the number of instances in the pool), it destroys it.
//-For singleton SBs, creation depends on whether they are instantiated eagerly (@Startup) or not, or whether they
// depend (@DependsOn) on another singleton that had been eagerly created.
//  If the answer is yes, the container will create an instance at deployment time.
//  If not, the container will create an instance when a client invokes a business method.
// Because singletons last for the duration of the application, the instance is destroyed when the container shuts down.

//-Lifecycle
// 1. @AroundConstruct 
// 2. constructor 
// 3. Dependency Injection 
// 4. @PostConstruct 
// 5. Business Methods invocations, @AroundInvoke 
// 6. @PreDestroy


//Callbacks
//-The change from one state to another can be intercepted by the container to invoke methods @ed by one of the annotations:
//@AroundConstruct	- (JEE 7) invokes logic prior to the constructor of the target class is invoked.
//@PostConstruct	- Marks a method to be invoked immediately after you create a bean instance and the container does DI.
//					  This annotation is often used to perform any initialization. 
//@PreDestroy		- Marks a method to be invoked immediately before the container destroys the bean instance. 
//					  @PreDestroy is often used to release resources that had been previously initialized. With SF beans, this happens after timeout or when a method annotated with @Remove has been completed. 

public class AnInfo {}
