package com.integrador.grupoA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients

public class MsvcViajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcViajesApplication.class, args);
	}

}