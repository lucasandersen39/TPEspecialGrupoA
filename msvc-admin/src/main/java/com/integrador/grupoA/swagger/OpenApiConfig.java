package com.integrador.grupoA.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Administrador",
                version = "1.0",
                description = "Documentación del microservicio admin para el sistema de alquiler de monopatines"
        )
)
public class OpenApiConfig {

}

