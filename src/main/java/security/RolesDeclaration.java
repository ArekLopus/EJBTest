package security;

import javax.annotation.security.DeclareRoles;

@DeclareRoles({ "admin", "user", "foo" })
public class RolesDeclaration {}
