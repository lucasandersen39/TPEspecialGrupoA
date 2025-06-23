package com.integrador.grupoA;

import com.integrador.grupoA.services.UsuarioService;
import com.integrador.grupoA.services.dto.usuario.usuarioRequestDTO.UsuarioRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MsvcUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcUsuariosApplication.class, args);
	}

	/*
	@Autowired
	private UsuarioService usuarioService;

	@Bean
	CommandLineRunner init() {
		return args -> {
			if (usuarioService.buscarPorId(1L).isEmpty()) {
				UsuarioRequestDTO defaultAdminUser = new UsuarioRequestDTO(
						"admin", "admin", "admin@admin.com", "0800-admin", "admin"
				);
				usuarioService.crearUsuario(defaultAdminUser);
			}
		};
	}*/

}
