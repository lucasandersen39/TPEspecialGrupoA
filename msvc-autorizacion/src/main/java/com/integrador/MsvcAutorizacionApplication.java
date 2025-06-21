package com.integrador;

import com.integrador.entites.Rol;
import com.integrador.entites.Usuario;
import com.integrador.enums.RolEnum;
import com.integrador.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class MsvcAutorizacionApplication {
    public static final String API_VERSION = "1";
    public static final String FIRST_PART = "/api/v" + API_VERSION;

    public static void main(String[] args) {
        SpringApplication.run(MsvcAutorizacionApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (usuarioService.findByUsername("admin").isEmpty()) {
                final Usuario defaultAdminUser = Usuario.builder().username("admin").password(passwordEncoder.encode("admin"))
                        .roles(new HashSet<>()).idUsuario(1L).build();
                defaultAdminUser.addRol(new Rol(RolEnum.ADMIN));
                usuarioService.save(defaultAdminUser);
            }
        };
    }
}