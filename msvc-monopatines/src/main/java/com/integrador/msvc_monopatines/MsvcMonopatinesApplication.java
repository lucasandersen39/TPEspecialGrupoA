package com.integrador.msvc_monopatines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcMonopatinesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsvcMonopatinesApplication.class, args);
	}

}
