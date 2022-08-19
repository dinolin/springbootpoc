package com.example.config;

import org.springframework.context.annotation.Bean;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("My Spring Boot API Document")
                .version("Ver. 1.0.0")
                .description("The document will list APIs we practice before.");
        String securitySchemeName = "JWT Authentication";
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(securitySchemeName);
        Components components = new Components()
                .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
        
        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
    
    
}
