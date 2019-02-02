package jndi_names;

//-Since EJB 3.1, JNDI names have been specified so the code could be portable.
//-Each time a SB with its interfaces is deployed to the container, each bean / interface is auto bound to a portable JNDI name.
//		java:<scope>[/<app-name>]/<module-name>/<bean-name>[!<fully-qualified-interface-name>]
//-Each portion of the JNDI name has the following meaning: 
// • <scope> defines a series of standard namespaces that map to the various scopes of a Java EE app: 
//	  • global: The java:global prefix allows a component executing outside a Java EE application to access a global namespace. 
//	  • app: The java:app prefix allows a component executing within a Java EE app to access an application-specific namespace. 
//	  • module: The java:module prefix allows a component executing within a Java EE app to access a module-specific namespace. 
//	  • comp: The java:comp prefix is a private component-specific namespace and is not accessible by other components. 
// • <app-name> is only required if the session bean is packaged within an .ear or .war file.
//   If this is the case, the <app-name> defaults to the name of the ear or war file (without the .ear or .war file extension). 
// • <module-name> is the name of the module in which the SB is packed. An EJB module in a stand-alone jar file or a web module
//   in a war file. The <module-name> defaults to the base name of the archive with no file extension. 
// • <bean-name> is the name of the session bean. 
// • <fully-qualified-interface-name> is the fully qualified name of each defined business interface.
//   For the no-interface view, the name can be the fully qualified bean class name.


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
