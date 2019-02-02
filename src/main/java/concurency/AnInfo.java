package concurency;

//		Stateless Session Beans
//-These beans are inherently thread safe. 
//-It is because by default, the container makes sure that each new request is served by a new instance of the bean.

//		Stateful Session Beans
//-The EJB container ensures that only one thread can access a bean instance at a particular time.

//		Singleton Session Beans
//-A Singleton has just one instance in the JVM. In a Java EE environment, concurrent access is inevitable.
//-One needs to make sure that the concurrency (locking) strategies for Singleton beans are well thought through, depending upon the use case and requirements
//-Singleton bean concurrency can be divided into 2 major categories
// •  Container Managed (Default)
// •  Bean Managed

//-Container Managed Concurrency
// •  As the name suggests, the container applies sensible default configurations for the bean
// •  Can be controlled using annotations as well as XML (deployment descriptors)
// •  Explicitly declared using the @javax.ejb.ConcurrencyManagement annotation on the bean class itself
//     •  Default value is javax.ejb.ConcurrencyManagementType.CONTAINER
// •  Two possible locking strategies provided by the container – applicable on both bean class or its individual methods
//     •  @javax.ejb.Lock with a value of LockType.READ – allows concurrent access given no write locks
//     •  @javax.ejb.Lock with a value of LockType.WRITE (Default) – only a single thread can execute a bean method at a given point
// •  @javax.ejb.AccessTimeout can be specified on a bean class or method to ensure that a thread does not block
//    or hold a lock for an indefinite time span

//-Bean Managed Concurrency
// •  The name clearly indicates – the concurrency aspects of the bean are left to the developer.
//    Makes sense when finer concurrency control is required as compared to what’s been offered by the container
// •  Usage of appropriate Java concurrency constructs required e.g. synchronized, volatile etc


public class AnInfo {}
