package com.integrador.grupoA;

import com.integrador.grupoA.dto.ParadaRequestDTO;
import com.integrador.grupoA.entities.Parada;
import com.integrador.grupoA.repositories.ParadaRepository;
import com.integrador.grupoA.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MsvcParadasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcParadasApplication.class, args);
    }

    @Autowired
    private ParadaRepository paradaRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (paradaRepository.findAll().isEmpty()) {
                paradaRepository.save(new Parada("parada1", 2.32, 3.34));
                paradaRepository.save(new Parada("parada2", 10.5, 13.62));
                paradaRepository.save(new Parada("parada3", 102.25, 303.70));
            }
        };
    }
}
