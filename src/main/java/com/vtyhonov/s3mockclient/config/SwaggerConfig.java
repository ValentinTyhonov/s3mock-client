package com.vtyhonov.s3mockclient.config;

import java.util.Collections;

import com.vtyhonov.s3mockclient.config.properties.ApplicationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ApplicationProperties applicationProperties;

    @Autowired
    public SwaggerConfig(final ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vtyhonov.s3mockclient.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "S3mock RESTfull API Service",
                applicationProperties.getDescription(),
                applicationProperties.getVersion(),
                "",
                new Contact(
                    "",
                    "https://github.com/ValentinTyhonov/s3mock-client",
                    "valentin.tyhonov@gmail.com"
                ),
                "", "", Collections.emptyList()
        );
    }
}
