package com.integrador.grupoA;

import com.integrador.grupoA.Domain.Viaje;
import com.integrador.grupoA.Repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
@EnableFeignClients

public class MsvcViajesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcViajesApplication.class, args);

    }

    @Autowired
    private ViajeRepository viajeRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (viajeRepository.findAll().isEmpty()) {
                viajeRepository.save(new Viaje(
                                null,
                                12345L,
                                "mono1",
                                LocalDateTime.of(2024, 9, 15, 10, 30),
                                LocalDateTime.of(2024, 9, 15, 11, 30),
                                10.5,
                                25.75,
                                15.0
                        )
                );
                viajeRepository.save(new Viaje(
                        null,
                        12346L,
                        "mono2",
                        LocalDateTime.of(2023, 9, 16, 9, 0), // fechaInicio
                        LocalDateTime.of(2023, 9, 16, 10, 0), // fechaFin
                        8.5, // kmRecorridos
                        20.00, // costoTotal
                        10.0 // tiempoPausado
                ));

                viajeRepository.save(new Viaje(
                        null,
                        12347L,
                        "mono3",
                        LocalDateTime.of(2024, 9, 17, 8, 45), // fechaInicio
                        LocalDateTime.of(2024, 9, 17, 9, 45), // fechaFin
                        12.0, // kmRecorridos
                        30.00, // costoTotal
                        5.0 // tiempoPausado
                ));

                viajeRepository.save(new Viaje(
                        null,
                        12348L,
                        "mono4",
                        LocalDateTime.of(2021, 9, 18, 15, 0), // fechaInicio
                        LocalDateTime.of(2021, 9, 18, 16, 15), // fechaFin
                        5.0, // kmRecorridos
                        15.00, // costoTotal
                        3.0 // tiempoPausado
                ));

                viajeRepository.save(new Viaje(
                        null,
                        12349L,
                        "mono5",
                        LocalDateTime.of(2025, 9, 19, 11, 30), // fechaInicio
                        LocalDateTime.of(2025, 9, 19, 12, 0), // fechaFin
                        7.5, // kmRecorridos
                        18.50, // costoTotal
                        7.0 // tiempoPausado
                ));

                viajeRepository.save(new Viaje(
                        null,
                        12350L,
                        "mono2",
                        LocalDateTime.of(2023, 9, 20, 14, 20), // fechaInicio
                        LocalDateTime.of(2023, 9, 20, 15, 20), // fechaFin
                        9.0, // kmRecorridos
                        22.50, // costoTotal
                        12.0 // tiempoPausado
                ));

            }
        };
    }

}