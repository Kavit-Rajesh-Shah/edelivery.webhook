package io.edelivery.webhook.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Optional;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Value("${edelivery.http.auth-token-header-name}")
    private String principalRequestHeader;

	@Bean
	public Docket api() {
	  return new Docket(DocumentationType.SWAGGER_2)//
	      .select()//
	      .apis(RequestHandlerSelectors.basePackage("io.edelivery.webhook"))//
	      .build()//
	      .apiInfo(apiInfo())//
	      .useDefaultResponseMessages(false)//
	      .genericModelSubstitutes(Optional.class);
	}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("eDelivery Webhook Listener")
                .description("\"eDelivery Webhook Api Gateway\"").version("1.0.0").build();
    }
 }