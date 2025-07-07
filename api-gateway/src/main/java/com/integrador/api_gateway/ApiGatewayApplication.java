package com.integrador.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		System.out.println("INICIANDO EL API GATEWAY");
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
