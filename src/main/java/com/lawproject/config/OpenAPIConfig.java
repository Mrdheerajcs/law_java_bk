package com.lawproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Law Project API")
                        .version("1.0")
                        .description("Law Project APIs - Event and User Management")
                        .contact(new Contact()
                                .name("Support")
                                .email("support@lawproject.com")));
    }
}