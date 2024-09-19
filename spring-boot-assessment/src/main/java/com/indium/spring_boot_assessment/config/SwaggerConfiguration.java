package com.indium.spring_boot_assessment.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration{

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IPL Match API")
                        .version("1.0")
                        .description("API for managing IPL match data, including players, scores, and teams."));
    }
}
