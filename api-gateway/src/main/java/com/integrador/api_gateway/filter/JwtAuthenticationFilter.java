package com.integrador.api_gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

   @Value("${jwt.secret}")
   private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        System.out.println("🧪 Filtro JWT ejecutado - PATH: " + path);

        // ✅ Rutas públicas que no requieren autenticación
        if (path.startsWith("/api/auth")) {
            System.out.println("✅ Ruta pública: " + path);
            return chain.filter(exchange);
        }

        // 🔐 Validar encabezado Authorization
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("⛔ Falta el token o formato inválido");
            return this.onError(exchange, "Falta el token de autenticación o el formato es inválido.", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7); // Quitar "Bearer "

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("📜 Contenido de claims: " + claims.toString());

            // Extraer datos del token
            @SuppressWarnings("unchecked")
            List<String> roles = claims.get("authorities", List.class);
            String role = (roles != null && !roles.isEmpty()) ? roles.get(0) : null;

            if (role == null) {
                return this.onError(exchange, "El token no contiene un rol válido.", HttpStatus.UNAUTHORIZED);
            }

            String username = claims.getSubject();

            System.out.println("🔐 Token válido - Usuario: " + username + " | Rol: " + role);
            System.out.println("📤 Propagando headers:");
            System.out.println("    - Authorization: " + authHeader);
            System.out.println("    - X-User: " + username);
            System.out.println("    - X-Role: " + role);

            // Reenviar datos al microservicio destino
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User", username)
                    .header("X-Role",  role)
                    .header(HttpHeaders.AUTHORIZATION, authHeader) // importante para microservicios
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        }catch(ExpiredJwtException e){
            System.out.println("⛔ Token expirado: " + e.getMessage());
            return this.onError(exchange, "El token ha expirado .", HttpStatus.UNAUTHORIZED);
        }catch(JwtException e){
            System.out.println("⛔ Token inválido: " + e.getMessage());
            return this.onError(exchange, "El token es invalido .", HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            System.out.println("⛔ Error inesperado en la validación del token: " + e.getMessage());
            return this.onError(exchange, "Error en la validación del token.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorMessage = "{\"error\":\"" + err + "\"}";
        DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
    @Override
    public int getOrder() {
        return -1; // Se ejecuta antes que otros filtros
    }


}
