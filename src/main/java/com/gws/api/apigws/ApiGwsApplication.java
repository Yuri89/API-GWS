package com.gws.api.apigws;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API VSConnect", version = "1.0.0",description = "Swagger da API do VSConnect"))
public class ApiGwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGwsApplication.class, args);
	}

}
