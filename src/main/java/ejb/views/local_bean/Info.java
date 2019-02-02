package ejb.views.local_bean;

//		@LocalBean
//-If the bean exposes at least one interface (local or remote) it automatically loses the no-interface view.
// It then needs to explicitly specify that it exposes a no-interface view by using the @LocalBean annotation on the bean class. 


//-if no @LocalBean on ViewLocalBean Exception (but @Inject InterfaceLocal il; works)
//	Exception during lifecycle processing
//	org.glassfish.deployment.common.DeploymentException: CDI deployment failure:WELD-001408:
//	Unsatisfied dependencies for type ViewLocalBean with qualifiers @Default
//	at injection point [BackedAnnotatedField] @Inject ejb.views.Servlet.lb

//-Cant inject Remote interface: @Inject InterfaceRemote ir;
//	Exception during lifecycle processing
//  org.glassfish.deployment.common.DeploymentException: CDI deployment failure:WELD-001408:
//  Unsatisfied dependencies for type InterfaceRemote with qualifiers @Default
//  at injection point [BackedAnnotatedField] @Inject ejb.views.Servlet.ir

public class Info {}
