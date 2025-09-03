package com.neoapp.clienteapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().
        info(new Info().title("NeoApp - API Pessoa Física").description("API REST - Cadastro e gerenciamento de Pessoa Física")
        .version("v1.0")
        .contact(new Contact().name("NeoApp").email("clientesapi@neoapp.com"))).externalDocs(new ExternalDocumentation().description("Doc Swagger")
        .url("/swagger-ui.html"));
    }

}
