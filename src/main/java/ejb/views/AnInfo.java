package ejb.views;

//-You cannot mark the same interface with more than one annotation. 

//	@Remote
//-A remote business interface. Method parameters are passed by value and need to be serializable as part of the RMI protocol. 

//	@Local
//-A local business interface. Method parameters are passed by reference from the client to the bean.

//	@LocalBean
//-If the bean exposes at least one interface (local or remote) it automatically loses the no-interface view.
// It then needs to explicitly specify that it exposes a no-interface view by using the @LocalBean annotation on the bean class. 

//	No Interface View
//-The no-interface view is a variation of the local view that exposes
// all public business methods of the bean class locally without the use of a separate business interface.

//-If the bean’s business interface is not decorated with @Local or @Remote, and if the bean class does not specify
// the interface using @Local or @Remote, the business interface is by default a local interface.


//-Listing shows a local interface (ItemLocal) and a remote interface (ItemRemote) implemented by the stateless session bean. 
//	@Local 
//	public interface TestLocal {)
//	@Remote 
//	public interface TestRemote {}
//	@Stateless 
//	public class TestEJB implements TestLocal, TestRemote {}

//-Alternatively, you can specify the interface in the bean’s class.
//-In this case, you have to include the name of the interface in the @Local or @Remote. 
//-This is handy when you have legacy interfaces where you can’t add annotations and need to use them in your SB.
//	@Stateless
//	@Remote(TestRemote.class)
//	@Local(TestLocal.class)
//	@LocalBean
//	public class TestEJB implements TestLocal, TestRemote {}

//-Bean dont have to implement interfaces, it is only easier to develop when implemented 
//	//implements InterfaceRemote, InterfaceLocal {

public class AnInfo {}
