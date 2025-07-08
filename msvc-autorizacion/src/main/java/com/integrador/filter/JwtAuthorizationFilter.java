package com.integrador.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.entites.Usuario;
import com.integrador.services.JwtService;
import com.integrador.services.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String token = getTokenFromRequest(request);

		if (token != null) {
			try {
				final Claims claims = jwtService.extractAllClaims(token);
				final String username = claims.getSubject();
				final Object authoritiesClaim = claims.get("authorities");

				if (username != null && authoritiesClaim != null) {
					final List<String> authorities = (List<String>) authoritiesClaim;

					final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username,
							null,
							authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
					);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}

			} catch (JwtException e) {
				SecurityContextHolder.clearContext();
				response.setStatus(HttpStatus.FORBIDDEN.value()); // Status 403 Forbidden
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);

				final Map<String, Object> errorDetails = new HashMap<>();
				errorDetails.put("status", HttpStatus.FORBIDDEN.value());
				errorDetails.put("error", "Acceso Denegado.");
				errorDetails.put("message", "El token proporcionado es inválido o ha expirado.");
				errorDetails.put("detail", e.getMessage());
				errorDetails.put("timestamp", new Date().getTime());

				new ObjectMapper().writeValue(response.getWriter(), errorDetails);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(final HttpServletRequest request) {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		return StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null;
	}
}