package com.stockmanagement.stock_management_backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI backendOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Stock Management API")
                .description("API docs for Stock Management Backend")
                .version("1.0"));
    }
}

