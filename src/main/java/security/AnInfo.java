package security;

//-Security annotations (@RolesAllowed, @DenyAll, etc.) are part of the Common Annotations 1.2 spec (JSR 250)
// and come from the javax.annotation.security package.

//-For security, you have to bear in mind that the business tier doesn’t authenticate users; it authorizes roles to access methods.

//		Authorization
//-The primary purpose of the EJB security model is to control access to business code.
//-In JEE authentication is handled by the web tier (or a client app); the principal and its roles are then passed to the EJB tier,
// and the EJB checks whether the authenticated user is allowed to access a method based on its role.

//-Authorization can be done either in a declarative or a programmatic manner. 
// • With declarative authorization, access control is made by the EJB container.
// • With programmatic authorization, access control is made in the code using the JAAS API.

//-Declarative security is done through a relatively small number of annotations and allows you to cover most cases
// an enterprise app is likely to need.
//-Again, you can switch to programmatic security and manipulate the JAAS API.


//	Declarative Authorization
//-The declarative authorization can be defined in the bean using annotations or in the XML DD.
//-Declarative authorization involves declaring roles, assigning permission to methods (or bean),
// or changing temporarily a security identity.

//Annotation		Bean	Meth	Description 
//@PermitAll		X		X		The given method (or the entire bean) is accessible by everyone (all roles are permitted). 
//@DenyAll			X		X		Indicates that no role is permitted to execute the specified method (or bean),(all roles are denied). This can be useful if you want to deny access to a method in a certain env (e.g., the method launchNuclearWar() should only be allowed in production but not in a test env). 
//@RolesAllowed		X		X		Indicates that a list of roles is allowed to execute the given method (or the entire bean). 
//@DeclareRoles		X				Defines roles for security checking. 
//@RunAs			X				Temporarily assigns a new role to a principal.

//	@RolesAllowed({"user", "employee", "admin"}) 
//	public class testJB {}
//-When you deploy the EJB, the container will auto declare the user, employee, and admin roles by inspecting the @RolesAllowed.
//-But if you want to declare roles in the security domain for the entire app (not just a single EJB) use the @DeclareRoles.
// This annotation, which only applies at the class level, takes an array of roles and declares them in the security domain.

//-The @DeclareRoles declares roles for the entire app.
//	@DeclareRoles({"user", "employee", "admin"}) 
//	public class testJB {}

//-You can declare security roles using @RolesAllowed, @DeclareRoles or a combination of both.
// If both annotations are used, the aggregation of the roles in @DeclareRoles and @RolesAllowed are declared.

//-The @PermitAll and @DenyAll are applied for any role.
//-You can use the @PermitAll to mark an EJB, or a method, to be invoked by any role.
//-The @DenyAll forbids any role to have access to a method.

//-@RunAs, is handy if you need to temporarily assign a new role to the existing principal.
// You might need to do this, fe, if you’re invoking another EJB within your method, but the other EJB requires a different role.


//	Programmatic Authorization
//-Declarative authorization covers most security cases needed by an application.
// But sometimes you need a finer grain of authorizing access (allowing a block of code instead of the entire method,
// permitting or denying access to an individual instead of a role, etc.).
//-You can use programmatic authorization to selectively permit or block access to a role or a principal.
// That’s because you have direct access to the JAAS java.security.Principal interface, as well as the EJB context
// to check the principal’s role in the code. 

//-The SessionContext interface defines the following methods related to security:
// • isCallerInRole(): This method returns a boolean and tests whether the caller has a given security role. 
// • getCallerPrincipal(): This method returns the java.security.Principal that identifies the caller.

public class AnInfo {}
