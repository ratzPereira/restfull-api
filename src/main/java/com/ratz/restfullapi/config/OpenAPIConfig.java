package com.ratz.restfullapi.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Restfull API")
            .version("v1")
            .description("")
            .termsOfService("")
            .license(new License()
                .name("Apache 2.0")
                .url("")));
  }
}
