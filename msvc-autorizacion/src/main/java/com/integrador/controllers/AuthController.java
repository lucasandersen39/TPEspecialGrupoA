package com.integrador.controllers;

import com.integrador.MsvcAutorizacionApplication;
import com.integrador.dto.RegisterRequestDTO;
import com.integrador.dto.RegisterResponseDTO;
import com.integrador.entites.Usuario;
import com.integrador.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService service;

	@PostMapping(value = "register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
		return ResponseEntity.ok(service.register(request));
	}
}