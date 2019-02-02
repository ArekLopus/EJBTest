package invoking_ejb;

//Invoking Enterprise Java Beans
//-The client of a SB can be any kind of component: a POJO, a graphical interface (Swing), a CDI Managed Bean, a Servlet,
// a JSF-backing bean, a web service (SOAP or REST), or another EJB (deployed in the same or a different container)

//-Simplicity also is applied to the client side. To invoke a method on a SB, a client doesnt directly instantiate the bean (using the new op).
// It needs a reference to the bean (or its interfaces). It can obtain it via DI (with the @EJB or @Inject) or via JNDI lookup.
//-DI allows a container to automatically inject a reference on an EJB at deployment time.
//-Unless specified, a client invokes a SB synchronously.


//	Invoking with @EJB Injection
//-@EJB is specifically intended for injecting session bean references into client code.
//DI is only possible within managed envs such as EJB containers, web containers, and application-client containers.
//		@EJB 
//		ItemEJB itemEJB;
//-If the session bean implements several interfaces, the client has to specify which one it wants a reference to.
//-The ItemEJB implements two interfaces and, thanks to the @LocalBean, also exposes a no-interface view. 
//-The client can invoke the EJB through its local, remote, or no interface:
//		@EJB  				@EJB  						@EJB 
//		ItemEJB itemEJB;	ItemLocal itemEJBLocal;		ItemRemote itemEJBRemote;
//-The @EJB API has several attributes; one of them is the JNDI name of the EJB you want to inject.
// This can be useful especially with remote EJBs living in a different server: 
//		@EJB(lookup = "java:global/classes/ItemEJB") 
//		ItemRemote itemEJBRemote; 


//	Invoking with CDI
//-Most of the time you can just replace @EJB by @Inject and your client code will work. Doing that you get all the CDI benefits.
//-So if we take the previous examples, following is how a client would get EJB injection with CDI:
//		@Inject  	  		@Inject 					@Inject 
//		ItemEJB itemEJB; 	ItemLocal itemEJBLocal; 	ItemRemote itemEJBRemote;
//-For remote EJBs, as you just saw, you might need to use a JNDI string to look it up.
// The @Inject cannot have a String parameter, so in this case, you need to produce the remote EJB to be able to inject it:
//		@Produces 
//		@EJB(lookup = "java:global/classes/ItemEJB")
//		ItemRemote itemEJBRemote;
//
//		@Inject 
//		ItemRemote itemEJBRemote;


//	Invoking with JNDI
//-SBs can also be looked up using JNDI. JNDI is mostly used for remote access when a client is not container managed and can't use DI. 
//-But JNDI can also be used by local clients, even if DI results in simpler code. Injection is made at deployment time.
// If there is a chance that the data will not be used, the bean can avoid the cost of resource injection by performing a JNDI lookup.
//
//-JNDI is an API for accessing different kinds of directory services, allowing clients to bind and look up objects via a name.
//-JNDI is defined in JSE and is independent of the underlying impl, which means that you can look up objects
// in a Lightweight Directory Access Protocol (LDAP) directory or a Domain Name System (DNS) using a standard API.

//-The alternative to the preceding code is to use the InitialContext of JNDI and look up a deployed EJB using its portable JNDI name that I defined earlier in the section “Portable JNDI Name.” The client code looks like the following: 
//	Context ctx = new InitialContext(); 	  or	  InitialContext.doLookup(...)
//	InterfaceRemote ir = (InterfaceRemote) ctx.lookup("java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceRemote"); 



//2018-12-04T12:50:56.351+0100|Info: Portable JNDI names for EJB ViewNoInterfaceTestBean:  
// java:global/EJBTest/ViewNoInterfaceTestBean!ejb.views.no_inteface_view.ViewNoInterfaceTestBean, 
// java:global/EJBTest/ViewNoInterfaceTestBean

//2018-12-04T12:50:57.119+0100|Info: Portable JNDI names for EJB ViewInterfacesTestBean: 
// java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceRemote,
// java:global/EJBTest/ViewInterfacesTestBean!ejb.views.InterfaceLocal,
// java:global/EJBTest/ViewInterfacesTestBean!ejb.views.interfaces.ViewInterfacesTestBean

//2018-12-04T12:50:57.394+0100|Info: Portable JNDI names for EJB ViewInterfacesAnnotatedTestBean: 
// java:global/EJBTest/ViewInterfacesAnnotatedTestBean!ejb.views.InterfaceRemoteWithAnnotation, 
// java:global/EJBTest/ViewInterfacesAnnotatedTestBean!ejb.views.InterfaceLocalWithAnnotation,
// java:global/EJBTest/ViewInterfacesAnnotatedTestBean!ejb.views.interfaces.annotated.ViewInterfacesAnnotatedTestBean

//2018-12-04T12:50:57.489+0100|Info: Portable JNDI names for EJB ViewLocalTestBean: 
// java:global/EJBTest/ViewLocalTestBean!ejb.views.InterfaceLocalBean,
// java:global/EJBTest/ViewLocalTestBean!ejb.views.InterfaceRemoteBean,
// java:global/EJBTest/ViewLocalTestBean!ejb.views.local_bean.ViewLocalTestBean



public class Info {}
