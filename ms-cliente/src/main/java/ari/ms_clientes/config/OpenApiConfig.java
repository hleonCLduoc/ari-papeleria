package ari.ms_clientes.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info (
                title = "API de clientes",
                version = "1.0.0",
                description = "API REST para gestionar clientes usando Spring Boot y Swagger",
                contact = @Contact (
                        name = "AriPapelería",
                        email = "contacto@aripapeleria.cl"
                )
        )
)

public class OpenApiConfig {
}
