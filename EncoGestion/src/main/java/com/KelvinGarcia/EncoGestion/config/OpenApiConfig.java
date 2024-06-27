package com.KelvinGarcia.EncoGestion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenApiConfig {

    //http://localhost:8080/api/v1/swagger-ui/index.html

    @Value("${http://localhost:8080/api/v1}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("kelvingarcialopez27@gmail.com");
        contact.setName("Kelvin");
        contact.setUrl("http://www.garcia.com");

        License mitLicense = new License().name("MIT License").url("http://choosealicense.com/license/mit/");

        Info info = new Info()
                .title("EncoGestion")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints")
                .termsOfService("https://encogestion.github.io/")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
