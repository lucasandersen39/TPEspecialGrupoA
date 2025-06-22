package com.integrador.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.integrador.dto.LoginRequestDTO;
import com.integrador.entites.Usuario;
import com.integrador.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
        LoginRequestDTO loginData = null;
        try {
            loginData = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch (IOException e) {
            log.error("Error: " + e.getMessage());
            throw new RuntimeException("Error al leer datos de autenticación", e);
        }

        final UsernamePasswordAuthenticationToken autenticationToken =
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword());
        return super.getAuthenticationManager().authenticate(autenticationToken);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        final Usuario usuario = (Usuario) authResult.getPrincipal();
        final String token = jwtService.getToken(usuario);
        response.addHeader("authorization", token);

        final Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "autenticaci\u00F3n correcta");
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
    }
}