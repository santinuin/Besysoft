package com.besysoft.ejerciciounidad6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.besysoft.ejerciciounidad6.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Bootcamp Besysoft",
                "API Peliculas",
                "V1",
                "Terms of service",
                new Contact("Santiago Nuñez Ingas", "https://www.linkedin.com/in/santinuin/", "santiago.nunez.ingas@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
