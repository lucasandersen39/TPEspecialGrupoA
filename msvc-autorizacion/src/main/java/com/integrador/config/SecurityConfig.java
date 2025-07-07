package com.integrador.config;

import com.integrador.MsvcAutorizacionApplication;
import com.integrador.filter.AuthenticationFilter;
import com.integrador.filter.JwtAuthorizationFilter;
import com.integrador.services.JwtService;
import com.integrador.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private JwtAuthorizationFilter authorizationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtService);
		final AuthenticationManager authenticationManager = authenticationManager(http);
		authenticationFilter.setAuthenticationManager(authenticationManager);
		authenticationFilter.setFilterProcessesUrl("/api/auth/login");

		return http.authenticationManager(authenticationManager).csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilter(authenticationFilter)
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(final HttpSecurity http) throws Exception {
		final AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationManagerBuilder.authenticationProvider(authenticationProvider).build();
	}
}