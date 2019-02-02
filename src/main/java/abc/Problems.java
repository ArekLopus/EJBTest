package abc;



//	SF Beans - @Asynchronous method on Payara doesnt work (Both, returning void and Future). Works on WIldfly.
//java.lang.NullPointerException


//	Security - @RunAs
//-Runs on WF
//	RunAsTestEJB, Principal: bb 
//	User: bb
//	is caller in role 'admin' -> true
//	is caller in role 'user' -> false 
//
//	AuthorizationTestEJB, Principal: bb 
//	User: bb
//	is caller in role 'admin' -> false
//	is caller in role 'user' -> true
//-On Payara - exception
//	brpwser: javax.ejb.EJBAccessException
//	console: A system exception occurred during an invocation on EJB AuthorizationTestEJB, method:
//	public void security.authorization.AuthorizationTestEJB.testMethodUser(java.io.PrintWriter)
//	javax.ejb.AccessLocalException: Client not authorized for this invocation

//?? Info at Payaras start
//2019-01-16T18:46:07.139+0100|Warning: The annotation symbol inheritance is not supported.
//symbol: TYPE
//location: class security.authorization.RunAsTestEJB


public class Problems {}