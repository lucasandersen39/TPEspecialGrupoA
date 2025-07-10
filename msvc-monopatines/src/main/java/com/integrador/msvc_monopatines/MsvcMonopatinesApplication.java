package com.integrador.msvc_monopatines;

import com.integrador.msvc_monopatines.domain.Monopatin;
import com.integrador.msvc_monopatines.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class MsvcMonopatinesApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsvcMonopatinesApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(MonopatinRepository monopatinRepository) {
		return args -> {
			if (!monopatinRepository.existsById("mono1")) {
				Monopatin mono =  new Monopatin();
				mono.setIdMonopatin("mono1");
				mono.setIdParada(1L);
				mono.setEstado(0);
				mono.setKmRecorridos(10.0);
				mono.setTiempoUsado(20.5);
				monopatinRepository.save(mono);
			}

			if (!monopatinRepository.existsById("mono2")) {
				Monopatin mono2 =  new Monopatin();
				mono2.setIdMonopatin("mono2");
				mono2.setIdParada(2L);
				mono2.setEstado(0);
				mono2.setKmRecorridos(100.0);
				mono2.setTiempoUsado(10.5);
				monopatinRepository.save(mono2);
			}

			if (!monopatinRepository.existsById("mono3")) {
				Monopatin mono3 =  new Monopatin();
				mono3.setIdMonopatin("mono3");
				mono3.setIdParada(3L);
				mono3.setEstado(0);
				mono3.setKmRecorridos(150.0);
				mono3.setTiempoUsado(0.5);
				monopatinRepository.save(mono3);
			}

			if (!monopatinRepository.existsById("mono4")) {
				Monopatin mono4 =  new Monopatin();
				mono4.setIdMonopatin("mono4");
				mono4.setIdParada(1L);
				mono4.setEstado(1);
				mono4.setKmRecorridos(50.0);
				mono4.setTiempoUsado(50.5);
				monopatinRepository.save(mono4);
			}

			if (!monopatinRepository.existsById("mono5")) {
				Monopatin mono5 =  new Monopatin();
				mono5.setIdMonopatin("mono5");
				mono5.setIdParada(1L);
				mono5.setEstado(2);
				mono5.setKmRecorridos(17.8);
				mono5.setTiempoUsado(45.6);
				monopatinRepository.save(mono5);
			}


		};
	}

}
