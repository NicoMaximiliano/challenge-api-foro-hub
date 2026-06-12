package com.nicode.challenge_api_foro_hub.configuration.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Challenge Foro Hub API", version = "1.0", description = "Documentación de la API para el sistema de Foro Hub"),
        // Esto aplica el candado de autenticación de forma GLOBAL a todos los
        // controladores de la página
        security = @SecurityRequirement(name = "Bearer Authentication"))
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {
    // No requiere métodos internos, las anotaciones de arriba configuran todo bajo
    // el capó.
}
