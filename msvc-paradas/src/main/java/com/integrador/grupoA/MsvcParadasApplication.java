package com.integrador.grupoA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcParadasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcParadasApplication.class, args);
    }
}
