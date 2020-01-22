package de.novatec.showcase.supplier.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

@ApplicationPath(value = "")
@SecuritySchemes({
	@SecurityScheme(securitySchemeName = "supplierDomainHttp",type = SecuritySchemeType.HTTP, scheme = "basic")
})
@OpenAPIDefinition(info = @Info(title = "The supplier domains api.", version ="1.0"), security = {@SecurityRequirement(name = "supplierDomainHttp")})
public class SupplierApplication extends Application {

}
